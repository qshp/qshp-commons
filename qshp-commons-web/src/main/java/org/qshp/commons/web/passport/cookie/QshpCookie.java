/*
 *Copyright 2013-2013 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

/**
 */
package org.qshp.commons.web.passport.cookie;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;

/**
 * @author QinYong
 * @desc
 */
public class QshpCookie {
	
	/**
	 * Default path that cookies will be visible to: "/", i.e. the entire server.
	 */
	public static final String DEFAULT_COOKIE_PATH = "/";

	private CookieCryptTools cryptTools;

	/**
	 * cookie名称
	 */
	private String cookieName;

	/**
	 * cookie域
	 */
	private String cookieDomain;

	/**
	 * cookie路径
	 */
	private String cookiePath = DEFAULT_COOKIE_PATH;
	
	/**
	 * 过期时间
	 */
	private int cookieMaxAge;
	
	private boolean cookieSecure = false;

	private boolean cookieHttpOnly = false;

	/**
	 * cookie是否加密
	 */
	private boolean encrypt;

	/**
	 * 加密解密私钥
	 */
	private String key;
	
	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	public String getCookieDomain() {
		return cookieDomain;
	}

	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}

	public String getCookiePath() {
		return cookiePath;
	}

	public void setCookiePath(String cookiePath) {
		this.cookiePath = cookiePath;
	}

	public boolean isCookieSecure() {
		return cookieSecure;
	}

	public void setCookieSecure(boolean cookieSecure) {
		this.cookieSecure = cookieSecure;
	}

	public boolean isCookieHttpOnly() {
		return cookieHttpOnly;
	}

	public void setCookieHttpOnly(boolean cookieHttpOnly) {
		this.cookieHttpOnly = cookieHttpOnly;
	}

	public CookieCryptTools getCryptTools() {
		return cryptTools;
	}

	public int getCookieMaxAge() {
		return cookieMaxAge;
	}

	public void setCookieMaxAge(int cookieMaxAge) {
		this.cookieMaxAge = cookieMaxAge;
	}

	public boolean isEncrypt() {
		return encrypt;
	}

	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public void setCryptTools(CookieCryptTools cryptTools) {
		this.cryptTools = cryptTools;
	}

	public Cookie newCookie(String cookieValue) {
		
		Cookie cookie = createCookie(cookieValue);
		int maxAge = getCookieMaxAge();
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		if (isCookieSecure()) {
			cookie.setSecure(true);
		}
		return cookie;
	}
	
	public String getValue(String value) {
		if (StringUtils.isNotEmpty(value)) {
			return isEncrypt() ? cryptTools.decrypt(value, getKey()) : value;
		} else {
			return value;
		}
	}
	
	protected Cookie createCookie(String value) {
		String newValue;
		if (StringUtils.isNotEmpty(value)) {
			newValue = isEncrypt() ? cryptTools.encrypt(value, getKey())
					: value;
		} else {
			newValue = value;
		}
		Cookie cookie = new Cookie(getCookieName(), newValue);
		if (getCookieDomain() != null) {
			cookie.setDomain(getCookieDomain());
		}
		cookie.setPath(getCookiePath());
		return cookie;
	}


}
