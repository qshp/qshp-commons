/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.web;

/**
 * 
 * @author QinYong
 * 
 */
public class BaseContext {

	private final static ThreadLocal<BaseContext> holder = new ThreadLocal<BaseContext>();

	private boolean ajax = false;
	private String remoteAddr;

	public boolean isAjax() {
		return ajax;
	}

	public void setAjax(boolean ajax) {
		this.ajax = ajax;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public static void setPandoraContext(BaseContext pandoraContext) {
		holder.set(pandoraContext);
	}

	public static BaseContext getPandoraContext() {
		return holder.get();
	}

	public static void remove() {
		holder.remove();
	}

}
