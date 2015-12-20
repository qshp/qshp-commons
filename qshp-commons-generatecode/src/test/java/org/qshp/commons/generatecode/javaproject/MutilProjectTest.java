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
        String projectRootDir = config.getString("project.dir");
        String groupId = config.getString("group.id");
        String artifactId = config.getString("artifact.id");
        MutilProject project = new MutilProject(config,projectRootDir,
                groupId,artifactId);
        project.buildProject();
    }

}
