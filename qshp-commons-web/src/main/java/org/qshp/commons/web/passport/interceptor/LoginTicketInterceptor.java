/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.web.passport.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.qshp.commons.lang.logging.Log;
import org.qshp.commons.lang.logging.LogFactory;
import org.qshp.commons.web.passport.LoginContext;
import org.qshp.commons.web.passport.cookie.CookieUtils;
import org.qshp.commons.web.passport.ticket.AuthenticationTicket;
import org.qshp.commons.web.passport.ticket.AuthenticationTicketUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author QinYong 登陆拦截器
 */
public class LoginTicketInterceptor implements HandlerInterceptor {

	private static Log log = LogFactory.getLog(AuthenticationTicketUtils.class);

	private CookieUtils cookieUtils;

	private String loginCookieName;

	private String loginCookieKey;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object o) throws Exception {

		AuthenticationTicket ticket = parseTicket(request);
		if (ticket != null) {
			setLoginContext(ticket);
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object o, ModelAndView mv)
			throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse response, Object o, Exception e)
			throws Exception {

	}

	private void setLoginContext(AuthenticationTicket ticket) {
		LoginContext context = new LoginContext();
		context.setUserName(ticket.getUserName());
		context.setCreateTime(ticket.getIssueDate() != null ? ticket.getIssueDate().getTime():0l);
		context.setExpires(ticket.getExpires() != null ? ticket.getExpires().getTime() :0l);
		context.setUserData(ticket.getUserData());
		LoginContext.setLoginContext(context);
	}

	private AuthenticationTicket parseTicket(HttpServletRequest request) {
		String cookieValue = cookieUtils.getCookieValue(request,
				loginCookieName);
		if (StringUtils.isNotBlank(cookieValue)) {
			AuthenticationTicket ticket = AuthenticationTicketUtils
					.parseTicket(cookieValue, loginCookieKey);
			if (ticket != null && StringUtils.isNotBlank(ticket.getUserName())) {
				return ticket;
			} else {
				log.debug("ticket is null");
			}
		}
		return null;
	}

	public void setCookieUtils(CookieUtils cookieUtils) {
		this.cookieUtils = cookieUtils;
	}

	public void setLoginCookieName(String loginCookieName) {
		this.loginCookieName = loginCookieName;
	}

	public void setLoginCookieKey(String loginCookieKey) {
		this.loginCookieKey = loginCookieKey;
	}
	
}
