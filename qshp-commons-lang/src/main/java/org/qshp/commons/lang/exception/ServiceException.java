
/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.lang.exception;

import java.util.Map;

/**

 * @author QinYong
 *
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> cause;

	public ServiceException(String message,Map<String,Object> cause){
		super(message);
		this.cause = cause;
	}

	public Map<String,Object> getCause_() {
		return cause;
	}
}
