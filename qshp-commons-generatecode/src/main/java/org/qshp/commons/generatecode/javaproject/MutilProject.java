package org.qshp.commons.generatecode.javaproject;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.qshp.commons.generatecode.util.FileParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by muyu on 15/12/13.
 */
public class MutilProject implements ProjectFactory {
    final Logger logger = LoggerFactory.getLogger(MutilProject.class);

    public final String TEMPLATE_DIR = "project-template";

    final String PROJECT_SEPARATOR = "-";

    private Configuration config;

    private String projectName;

    private String projectRootDir;

    static Set<String> projectType = new HashSet<String>();

    static {
        projectType.add("web");
        projectType.add("service");
        projectType.add("manager");
        projectType.add("dao");
        projectType.add("domain");
    }

    public MutilProject(Configuration config, String projectRootDir, String projectName) {
        this.config = config;
        this.projectName = projectName;
        this.projectRootDir = projectRootDir;
    }

    @Override
    public String templateDir() {
        return TEMPLATE_DIR;
    }


    @Override
    public void buildProject() {
        try {
            File tempateDir = new File(ClassLoader.getSystemResource(templateDir()).toURI());

            File[] templateFiles = tempateDir.listFiles();
            if (ArrayUtils.isEmpty(templateFiles)) {
                return;
            }

            List<File> parentFlies = new LinkedList<File>();
            List<File> childFiles = new LinkedList<File>();
            String[] splitArr;
            for (int i = 0; i < templateFiles.length; i++) {
                splitArr = templateFiles[i].getName().split(PROJECT_SEPARATOR);
                if (splitArr.length == 2) {
                    if (projectType.contains(splitArr[1])) {
                        childFiles.add(templateFiles[i]);
                        continue;
                    }
                }
                parentFlies.add(templateFiles[i]);
            }

            if (!parentFlies.isEmpty()) {
                generateParentProject(parentFlies);
            }

            if (!childFiles.isEmpty()) {
                generateChildProject(childFiles);
            }


        } catch (URISyntaxException e) {
            logger.info("", e);
        }
    }

    /**
     * 生成父工程
     */
    private void generateParentProject(List<File> templateFlies) {

        for (File templateFile : templateFlies) {
            if (templateFile.isDirectory()) {
                generateDir(templateFile);
            } else {
                File targetFile = getTargetFile(templateFile);
                generateFile(templateFile, targetFile);
            }
        }
    }

    /**
     * 生成子工程
     *
     * @param templateFlies
     */
    private void generateChildProject(List<File> templateFlies) {
        String templateProjectName;
        String targetProjectName;
        for (File templateFile : templateFlies) {
            templateProjectName = templateFile.getName();
            targetProjectName = getProjectName() + PROJECT_SEPARATOR + templateProjectName.split(PROJECT_SEPARATOR)[1];
            generateDirForChild(templateFile, templateProjectName, targetProjectName);
        }
        // 生成package
    }

    private void generateDir(File templateDir) {
        Collection<File> templateFiles = FileUtils.listFiles(templateDir, null, true);
        for (File templateFile : templateFiles) {
            File targetFile = getTargetFile(templateFile);
            generateFile(templateFile, targetFile);
        }
    }

    private void generateDirForChild(File templateDir,
                                     String templateProjectName,
                                     String targetProjectName) {
        Collection<File> templateFiles = FileUtils.listFiles(templateDir, null, true);
        for (File templateFile : templateFiles) {
            File targetFileName = getTargetFile(templateFile);
            targetFileName = new File(targetFileName.getPath().replace(
                    templateProjectName, targetProjectName));
            generateFile(templateFile, targetFileName);
        }
    }

    private File getTargetFile(File templateFile) {
        String templateFilePath = templateFile.getPath();
        String targetFilePath = getProjectRootDir() + File.separator
                + templateFilePath.substring(templateFilePath.indexOf(
                templateDir()) + templateDir().length() + 1);
        return new File(targetFilePath);
    }

    public String generateFile(File template, File target) {
        String fileContent;

        FileParse parse = new FileParse(config);
        try (BufferedReader reader = new BufferedReader(new FileReader(template))){
            fileContent = parse.parseTemplate(reader).toString();
        } catch (IOException e) {
            throw new ProjectException("template file not found !");
        }

        File parentFile = target.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try (FileOutputStream output = new FileOutputStream(target)){

            output.write(fileContent.getBytes());
        } catch (IOException e) {
            throw new ProjectException("write target file error !");
        }
        return fileContent;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectRootDir() {
        return projectRootDir;
    }

    public void setProjectRootDir(String projectRootDir) {
        this.projectRootDir = projectRootDir;
    }

}
