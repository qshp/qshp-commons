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

    public final String JAVA_PACKAGE_ROOT = "src/main/java";

    public final String TEST_PACKAGE_ROOT = "src/test/java";

    final String PROJECT_SEPARATOR = "-";

    private Configuration config;

    private String projectRootDir;

    private String groupId;

    private String artifactId;

    static Set<String> projectType = new HashSet<String>();

    static {
        projectType.add("web");
        projectType.add("service");
        projectType.add("manager");
        projectType.add("dao");
        projectType.add("domain");
    }

    /**
     *
     * @param config
     * @param projectRootDir
     * @param groupId
     * @param artifactId
     */
    public MutilProject(Configuration config, String projectRootDir, String groupId,String artifactId) {
        this.config = config;
        this.groupId = groupId;
        this.artifactId = artifactId;
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
            String projectDir = getProjectDir();
            String projectPackage =  getGroupId().replace(".",File.separator);

            if (!parentFlies.isEmpty()) {
                generateParentProject(parentFlies,projectDir);
            }

            if (!childFiles.isEmpty()) {
                generateChildProject(childFiles,projectDir,projectPackage);
            }


        } catch (URISyntaxException e) {
            logger.info("", e);
        }
    }

    /**
     * 生成子工程
     * @param templateFlies
     * @param projectDir
     */
    private void generateParentProject(List<File> templateFlies,String projectDir) {

        for (File templateFile : templateFlies) {
            if (templateFile.isDirectory()) {
                generateDir(projectDir,templateFile);
            } else {
                File targetFile = getTargetFile(projectDir,templateFile);
                generateFile(templateFile, targetFile);
            }
        }
    }

    /**
     * 生成子工程
     * @param templateFlies
     * @param projectDir
     * @param projectPackage
     */
    private void generateChildProject(List<File> templateFlies,String projectDir,String projectPackage) {
        String templateProjectName;
        String targetProjectName;
        for (File templateFile : templateFlies) {
            templateProjectName = templateFile.getName();
            targetProjectName = getArtifactId() + PROJECT_SEPARATOR + templateProjectName.split(PROJECT_SEPARATOR)[1];
            //生成非Java目录、文件
            generateDirForChild(projectDir,templateFile, templateProjectName, targetProjectName);
            // 生成Java package
            mkdirJavaPackage(projectDir,targetProjectName,projectPackage);
        }


    }

    private void generateDir(String projectDir,File templateDir) {
        Collection<File> templateFiles = FileUtils.listFiles(templateDir, null, true);
        for (File templateFile : templateFiles) {
            File targetFile = getTargetFile(projectDir,templateFile);
            generateFile(templateFile, targetFile);
        }
    }

    private void generateDirForChild(String projectDir,File templateDir,
                                     String templateProjectName,
                                     String targetProjectName) {
        Collection<File> templateFiles = FileUtils.listFiles(templateDir, null, true);
        for (File templateFile : templateFiles) {
            File targetFileName = getTargetFile(projectDir,templateFile);
            targetFileName = new File(targetFileName.getPath().replace(
                    templateProjectName, targetProjectName));
            generateFile(templateFile, targetFileName);
        }
    }

    /**
     * 生成java包目录
     * @param targetProjectName
     */
    private void mkdirJavaPackage(String projectDir,String targetProjectName,String projectPackage){

        org.qshp.commons.util.io.FileUtils.mkdirs(projectDir+ targetProjectName
                + File.separator + JAVA_PACKAGE_ROOT + File.separator + projectPackage);
        org.qshp.commons.util.io.FileUtils.mkdirs(getProjectDir()+ targetProjectName
                + File.separator + TEST_PACKAGE_ROOT + File.separator + projectPackage);

    }

    private File getTargetFile(String projectDir,File templateFile) {
        String templateFilePath = templateFile.getPath();
        String targetFilePath = projectDir + templateFilePath.substring(templateFilePath.indexOf(
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

    private String getProjectDir(){
        return getProjectRootDir() + File.separator
                + getArtifactId() + File.separator;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public String getProjectRootDir() {
        return projectRootDir;
    }

    public void setProjectRootDir(String projectRootDir) {
        this.projectRootDir = projectRootDir;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
}
