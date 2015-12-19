package org.qshp.commons.generatecode.javaproject;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by muyu on 15/12/19.
 */
public class MutilProjectTest {

    public static void main(String[] args) throws ConfigurationException {
        Configuration config = new PropertiesConfiguration("config.properties");
        String projectName = "test-project";
        String projectRootDir = config.getString("project.dir");
        MutilProject project = new MutilProject(config,projectRootDir,projectName);
        project.buildProject();
    }

}
