/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.web.passport;

import org.qshp.commons.lang.exception.AbstractException;

/**
 * @author QinYong
 *
 */
public class FieldNullException extends AbstractException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FieldNullException(String fieldName){
		super(fieldName + " is null");
	}
	
	public FieldNullException(String fieldName,Throwable e){
		super(fieldName + " is null", e);
	}
}
