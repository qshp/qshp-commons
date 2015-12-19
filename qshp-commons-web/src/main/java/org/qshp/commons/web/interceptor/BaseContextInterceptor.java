/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import org.qshp.commons.web.BaseContext;
import org.qshp.commons.web.WebSupport;

/**
 * 
 * @author QinYong
 * 
 */
public class BaseContextInterceptor extends WebSupport implements
		HandlerInterceptor {

	private final Logger logger = Logger.getLogger(this.getClass());

	protected static final String XHR_OBJECT_NAME = "XMLHttpRequest";
	protected static final String HEADER_REQUEST_WITH = "x-requested-with";
	protected static final String HEADER_FORWARDED_FOR = "X-Forwarded-For";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		BaseContext pandoraContext = getPandoraContext();
		// 判断是否 ajax 访问
		pandoraContext.setAjax(this.isAjax(request));
		// 取IP
		pandoraContext.setRemoteAddr(this.getRemoteAddr(request));

		return true;
	}

	/**
	 * 返回信息
	 * 
	 * @author QinYong
	 * @param response
	 * @param result
	 */

	protected void responseResult(HttpServletResponse response, String result) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(result);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * @author QinYong Oct 14, 2013 4:04:03 PM
	 * @param request
	 * @return
	 */
	protected boolean isAjax(HttpServletRequest request) {
		return XHR_OBJECT_NAME.equals(request.getHeader(HEADER_REQUEST_WITH));
	}

	/**
	 * 取得 IP
	 * 
	 * @author QinYong Oct 14, 2013 3:54:51 PM
	 * @param request
	 * @return
	 */
	protected String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("X-Real-IP");

		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip))
			ip = request.getRemoteAddr();

		if (StringUtils.isNotBlank(ip) && ip.indexOf(",") != -1)
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();

		return ip;
	}

	/**
	 * @author QinYong Oct 12, 2013 3:41:44 PM
	 * @return
	 */
	protected boolean clearAndReturn() {
		BaseContext.remove();
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
