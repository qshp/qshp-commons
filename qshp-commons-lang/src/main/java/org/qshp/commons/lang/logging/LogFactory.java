/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.lang.logging;

import org.qshp.commons.lang.logging.log4j.Log4jImpl;

/**
 * @author QinYong
 *
 */
public class LogFactory {

	private LogFactory(){

	}
	
	public static Log getLog(String clazz){
		return new Log4jImpl(clazz);
	}
	
	public static Log getLog(Class<?> clazz){
		return new Log4jImpl(clazz);
	}
}
