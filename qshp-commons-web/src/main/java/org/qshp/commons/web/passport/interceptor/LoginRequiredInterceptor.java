/*
 *Copyright 2013-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

/**
 */
package org.qshp.commons.web.passport.interceptor;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.qshp.commons.web.passport.LoginContext;
import org.qshp.commons.web.passport.cookie.CookieUtils;
import org.qshp.commons.web.url.UrlBuilder.Builder;
import org.qshp.commons.web.url.UrlBuilders;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author QinYong
 */
public class LoginRequiredInterceptor extends HandlerInterceptorAdapter {

	protected UrlBuilders urlBuilders;

	protected String homeUrl = "homeUrl";

	protected String loginUrl = "loginUrl";
	
	protected CookieUtils cookieUtils;
	
	protected String loginCookieName;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if (!isLogin(request)) {
			response.sendRedirect(getLoginUrl(request));
			return false;
		}else if(isTimeoutOrUpdate(response)){
			response.sendRedirect(getLoginUrl(request));
			return false;
		}
		return true;
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LoginContext.removeLoginContext();
	}

	@SuppressWarnings("unchecked")
	protected String getLoginUrl(HttpServletRequest request)
			throws MalformedURLException {
		
		Builder currentUrlBuilder = urlBuilders.get(homeUrl).forPath(
				request.getRequestURI());
		
		currentUrlBuilder.put(request.getParameterMap());

		Builder loginUrlBuilder = urlBuilders.get(loginUrl).forPath(null);

		loginUrlBuilder.put("ReturnUrl", currentUrlBuilder.build());

		return loginUrlBuilder.build();

	}
	
	protected boolean isTimeoutOrUpdate(HttpServletResponse response){
		LoginContext loginContext = LoginContext.getLoginContext();
		long expires = loginContext.getExpires();
		long curr = System.currentTimeMillis();
		if(expires < curr){
			return true;
		}
		long create = curr;
		expires = create + LoginContext.DEFAULT_EXPIRES;
		loginContext.setExpires(expires);
		loginContext.setCreateTime(create);
		// update timeout 
		cookieUtils.setCookie(response, loginCookieName, loginContext.toCookieValue());
		return false;
	}
	
	protected boolean isLogin(HttpServletRequest request) {
		
		LoginContext loginContext = LoginContext.getLoginContext();
		if (loginContext != null && loginContext.isLogin()) {
			return true;
		}
		return false;
	}

	public void setUrlBuilders(UrlBuilders urlBuilders) {
		this.urlBuilders = urlBuilders;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public void setCookieUtils(CookieUtils cookieUtils) {
		this.cookieUtils = cookieUtils;
	}

	public void setLoginCookieName(String loginCookieName) {
		this.loginCookieName = loginCookieName;
	}

}
