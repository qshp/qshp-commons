/*
 *Copyright 2013-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

/**
 */
package org.qshp.commons.web.passport.cookie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author QinYong
 * @desc
 */
public class CookieUtils {

	private Map<String, QshpCookie> cookieMap;

	public String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					if (cookieMap.containsKey(cookieName)) {
						QshpCookie qshpCookie = cookieMap.get(cookieName);
						return qshpCookie.getValue(cookie.getValue());
					} else {
						cookie.getValue();
					}
				}
			}
		}
		return null;
	}
	
	public void setCookie(HttpServletResponse response,String cookieName,String value){
		if(cookieMap != null && cookieName != null){
			Cookie cookie = cookieMap.get(cookieName).newCookie(value);
			response.addCookie(cookie);
		}else{
			  throw new RuntimeException("Cookie " + cookieName + " is undefined!");
		}
	}
	
	 /**
     * 删除cookie，不管有没有定义都能删除
     * 
     * @param servletResponse
     * @param name
     */
    public void deleteCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie;
        if (cookieMap != null && cookieMap.containsKey(cookieName)) {
        	QshpCookie qshpCookie = cookieMap.get(cookieName);
            cookie = qshpCookie.newCookie(null);
        } else {
            cookie = new Cookie(cookieName, null);
        }
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

	

	/**
	 * 
	 * @param qshpCookieList
	 */
	public void setQshpCookies(List<QshpCookie> qshpCookieList) {
		if (qshpCookieList != null) {
			HashMap<String, QshpCookie> qshpCookieMap = new HashMap<String, QshpCookie>(
					qshpCookieList.size());
			for (QshpCookie qshpCookie : qshpCookieList) {
				qshpCookieMap.put(qshpCookie.getCookieName(), qshpCookie);
			}
			cookieMap = qshpCookieMap;
		}
	}
}
