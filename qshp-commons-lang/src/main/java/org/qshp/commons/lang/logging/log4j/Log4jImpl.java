/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.lang.logging.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.qshp.commons.lang.logging.Log;

/**
 * @author QinYong
 * 
 */
public class Log4jImpl implements Log {
	private static final String FQCN = Log4jImpl.class.getName();

	private Logger log;

	public Log4jImpl(String clazz) {
		log = Logger.getLogger(clazz);
	}

	public Log4jImpl(Class<?> clazz) {
		log = Logger.getLogger(clazz);
	}

	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	public boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}

	public void error(String s, Throwable e) {
		log.log(FQCN, Level.ERROR, s, e);
	}

	public void error(String s) {
		log.log(FQCN, Level.ERROR, s, null);
	}

	public void debug(String s) {
		log.log(FQCN, Level.DEBUG, s, null);
	}

	public void trace(String s) {
		log.log(FQCN, Level.TRACE, s, null);
	}

	public void warn(String s) {
		log.log(FQCN, Level.WARN, s, null);
	}
	
	public void info(String s) {
		log.log(FQCN, Level.INFO, s, null);
	}
}
