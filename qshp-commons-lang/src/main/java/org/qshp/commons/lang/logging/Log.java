/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.lang.logging;

/**
 * @author QinYong
 * 
 */
public interface Log {

	public boolean isDebugEnabled();

	public boolean isTraceEnabled();

	public void error(String s, Throwable e);

	public void error(String s);

	public void debug(String s);

	public void trace(String s);

	public void warn(String s);
	
	public void info(String s);
}
