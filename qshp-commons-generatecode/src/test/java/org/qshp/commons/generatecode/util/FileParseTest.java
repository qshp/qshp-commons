package org.qshp.commons.generatecode.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by muyu on 15/12/20.
 */
public class FileParseTest {
    public static void main(String[] args) {
//        1231${AAAAA}3${BBBB}
//        DDD
//        sdsdsdds1${{CCCCC}3sdaaaf${DDDDD}SADSF${EEEE}LL;
        String configPath = "config.properties";
        String xmlFilePath = "/Users/muyu/dev/git/qshp-commons/qshp-commons-generatecode/src/main/" +
                "resources/project-template/demo-web/src/main/resources/spring/spring-config-datasource.xml";
        try {
            Configuration config = new PropertiesConfiguration(configPath);
            FileParse fileParse = new FileParse(config);
            BufferedReader reader = new BufferedReader(new FileReader(xmlFilePath));

            StringBuffer content = fileParse.parseTemplate(reader);
            System.out.println(content);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }


    }
}
