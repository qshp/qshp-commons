package org.qshp.commons.generatecode.javaproject;

/**
 * Created by muyu on 15/11/27.
 */
public class ProjectException extends RuntimeException{

    public ProjectException(String msg){
        super(msg);
    }

    public ProjectException(String msg,Throwable e){
        super(msg,e);
    }
}
