/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.lang.exception;

/**
 * @author QinYong
 *
 */
public abstract class AbstractException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public AbstractException(){
		super();
	}
	
	public AbstractException(String message){
		super(message);
	}

	public AbstractException(Throwable cause){
		super(cause);
	}
	
	public AbstractException(String message,Throwable cause){
		super(message,cause);
	}
}
