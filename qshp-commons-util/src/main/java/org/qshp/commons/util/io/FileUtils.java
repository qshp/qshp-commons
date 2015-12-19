package org.qshp.commons.util.io;

import java.io.*;

/**
 * Created by muyu on 15/11/28.
 */
public class FileUtils {

    private FileUtils(){

    }

    public static File mkdirs(String path){
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        return f;
    }
}
