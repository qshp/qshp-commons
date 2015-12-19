/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.web.passport;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

/**
 * @author QinYong 登录用户信息上下文
 */
public class LoginContext {

	private static ThreadLocal<LoginContext> holder = new ThreadLocal<LoginContext>();

	// 默认过期设置 30分钟 30 * 60 * 1000
	public static int DEFAULT_EXPIRES = 1800000;
	
	private String userName;
	
	private String userData;

	private long expires;

	private long createTime;

	public static void setLoginContext(LoginContext context) {
		holder.set(context);
	}

	public static void removeLoginContext() {
		holder.remove();
	}

	public static LoginContext getLoginContext() {
		return holder.get();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public boolean isLogin() {
		return StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(userData);
	}

	public String toCookieValue() {
		StringBuilder b = new StringBuilder();
		if (userName != null) {
			try {
				b.append(",userName=").append(
						URLEncoder.encode(userName, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		if (userData != null) {
			b.append(",userdata=").append(userData);
		}
		
		if (expires != 0) {
			b.append(",expires=").append(expires);
		}
		
		if (createTime != 0) {
			b.append(",createTime=").append(createTime);
		}
		
		return b.length() > 0 ? b.substring(1) : "";
	}
}
