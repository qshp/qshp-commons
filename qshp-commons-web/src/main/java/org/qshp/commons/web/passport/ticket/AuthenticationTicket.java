/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.web.passport.ticket;

import java.util.Date;

import org.qshp.commons.web.passport.FieldNullException;

/**
 * @author QinYong
 * 
 */
public class AuthenticationTicket {

	// 默认过期设置 30分钟 30 * 60 * 1000
	public static int  DEFAULT_EXPIRES = 1800000;
	
	/**
	 * 版本
	 */
	private int version;

	/**
	 * 登陆用户名
	 */
	private String userName;

	/**
	 * 登陆用户信息
	 */
	private String userData;

	/**
	 * 登陆路径
	 */
	private String appPath;

	/**
	 * 过期时间
	 */
	private Date expires;

	/**
	 * 登陆时间
	 */
	private Date issueDate;

	public AuthenticationTicket(String userName, String userData,
			String appPath, Date expires, Date issueDate, int version) throws FieldNullException{
		if(userName == null){
			throw new FieldNullException("userName");
		}else{
			this.userName = userName;
		}
		
		if(userData == null){
			this.userData = "";
		}else{
			this.userData = userData;
		}
		
		if(appPath == null){
			this.appPath = "/";
		}else{
			this.appPath = appPath;
		}
		
		if(expires == null){
			this.expires = new Date();
		}else{
			this.expires = new Date(System.currentTimeMillis() + DEFAULT_EXPIRES);
		}
		
		if(issueDate == null){
			this.issueDate = new Date();
		}else{
			this.issueDate = issueDate;
		}
	
		if(version > 0){
			this.version = version;
		}
		
	}

	public int getVersion() {
		return version;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserData() {
		return userData;
	}

	public String getAppPath() {
		return appPath;
	}

	/**
	 * 是否过期
	 * @return true 过期；false 未过期
	 */
	public boolean isExpires() {
		return (new Date()).after(expires);
	}

	public Date getExpires() {
		return expires;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("userName=").append(userName);
		b.append(",");
		b.append("userData=").append(userData);
		b.append(",");
		b.append("expires=").append(expires.getTime());
		b.append(",");
		b.append("issueData=").append(issueDate.getTime());
		b.append(",");
		b.append("appPath=").append(appPath);
		b.append(",");
		b.append("version=").append(version);
		return b.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AuthenticationTicket ticket = (AuthenticationTicket) o;
		if (version != ticket.version) {
			return false;
		}
		if (appPath != null ? !appPath.equals(ticket.appPath)
				: ticket.appPath != null) {
			return false;
		}
		if (expires != null ? !expires.equals(ticket.expires)
				: ticket.expires != null) {
			return false;
		}
		if (issueDate != null ? !issueDate.equals(ticket.issueDate)
				: ticket.issueDate != null) {
			return false;
		}
		if (userName != null ? !userName.equals(ticket.userName)
				: ticket.userName != null) {
			return false;
		}
		if (userData != null ? !userData.equals(ticket.userData)
				: ticket.userData != null) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = version;
		result = 31 * result + (userName != null ? userName.hashCode() : 0);
		result = 31 * result + (userData != null ? userData.hashCode() : 0);
		result = 31 * result + (appPath != null ? appPath.hashCode() : 0);
		result = 31 * result + (expires != null ? expires.hashCode() : 0);
		result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
		return result;
	}
}
