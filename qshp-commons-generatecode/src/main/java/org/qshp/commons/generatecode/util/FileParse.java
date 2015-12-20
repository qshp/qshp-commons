package org.qshp.commons.generatecode.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.qshp.commons.generatecode.javaproject.ProjectException;

import java.io.*;
import java.net.URISyntaxException;

/**
 * Created by muyu on 15/11/5.
 */
public class FileParse {

    final static String PRE = "${";

    final static String SUF = "}";

    private  Configuration config;

    public FileParse(Configuration config){
       this.config =  config;
    }


    /**
     *
     * @param replaceConfig
     * @param template
     * @param target
     * @return
     * @throws IOException
     */
    public static String replaceContent(Configuration replaceConfig,File template,File target) throws IOException{
        String targetContent;
        BufferedReader reader = new BufferedReader(new FileReader(template));
        FileParse parse = new FileParse(replaceConfig);
        targetContent = parse.parseTemplate(reader).toString();
        FileOutputStream output = new FileOutputStream(target);
        output.write(targetContent.getBytes());
        return targetContent;
    }

    /**
     * 替换占位符字符串
     * @param reader
     * @return
     */
    public StringBuffer parseTemplate(BufferedReader reader) {

        if(reader == null || config == null){
            throw new NullPointerException("BufferedReader or Configuration object is null !");
        }

        StringBuffer content = new StringBuffer();
        boolean flag = true;
        int begin = 0;
        int end = 0;
        int pointer = 0;
        String key;
        try {
            String str = reader.readLine();
            String value;
            int temBegin;
            while (flag) {
                if(str == null){
                    break;
                }
                begin = str.indexOf(PRE, begin);

                if (begin > -1 && (end = str.indexOf(SUF, begin)) > -1) {

                    //提取占位符中字符串
                    key = str.substring(begin + PRE.length(), end);
                    //提取的字符中是否还包含PRE，进行二次提取
                    temBegin = key.lastIndexOf(PRE);
                    if(temBegin > -1){
                        temBegin += PRE.length();
                        key = key.substring(temBegin);
                        begin += temBegin;
                    }
                    //根据从占位符中提取字符串获取配置文件对应的value
                    value = config.getString(key);
                    if( value == null){ //配置文件key不存在，则不替换，保留原值
                        content.append(str.substring(pointer,end + SUF.length()));
                    }else{
                        content.append(str.substring(pointer, begin));
                        content.append(value);
                    }
                    begin = end;
                    pointer = begin + SUF.length();

                } else {

                    if(end > 0 && end != str.length()){ // SUF标示不是本行最后字符，所以需把SUF后所有字符都抽取出来
                        content.append(str.substring(end + SUF.length(),str.length()));
                    }else{
                        content.append(str.substring(end,str.length()));
                    }
                    content.append("\r\n");

                    flag = (str = reader.readLine()) == null ? false : true;

                    // 复位
                    begin = 0;
                    end = 0;
                    pointer = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
