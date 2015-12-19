/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import org.qshp.commons.web.result.Result;
import org.qshp.commons.web.result.ResultConfig;

/**
 * 
 * @author QinYong
 * 
 */
public abstract class WebSupport {

	private final Logger logger = Logger.getLogger(this.getClass());

	protected final static String SUCCESS = "success";
	protected final static String ERROR_CODE = "errorCode";
	protected final static String ERROR_TEXT = "errorText";
	protected final static String ERROR_MULTIPLE = "multipleError";
	protected final static String ERROR_MULTIPLE_CODE = "multipleErrorCode";
	protected final static String ERROR_MULTIPLE_TEXT = "multipleErrorText";

	protected final static String CHARSETNAME = "UTF-8";

	@Autowired
	private ResultConfig resultConfig;
	@Autowired
	private MessageSource messageSource;

	protected String m(String code) {
		Result result = getResult(ResultConfig.M, code);
		return result == null ? "" : getValue(result.getSubject());
	}

	protected String e(String code) {
		Result result = getResult(ResultConfig.E, code);
		return result == null ? "" : getValue(result.getSubject());
	}

	protected String s(String code) {
		Result result = getResult(ResultConfig.S, code);
		return result == null ? "" : getValue(result.getSubject());
	}

	/**
	 * 取得配置文件中的Result
	 * 
	 * @param type
	 * @param code
	 * @return
	 */
	protected Result getResult(String type, String code) {
		return resultConfig.getResult(type, code);
	}

	/**
	 * 从 properties 中取值
	 * 
	 * @param code
	 * @return
	 */
	protected String getValue(String code) {
		return getValue(code, null);
	}

	/**
	 * 从 properties 中取值
	 * 
	 * @param code
	 * @param arguments
	 * @return
	 */
	protected String getValue(String code, Object[] arguments) {
		String rv = null;
		try {
			rv = messageSource.getMessage(code, arguments, null);
		} catch (NoSuchMessageException e) {
			logger.error(e);
		}
		return rv == null ? code : rv;
	}

	/**
	 * 向 MAP 中添加错误信息，同时转换错误代码为说明
	 * 
	 * @param map
	 * @param key
	 * @param value
	 * @return
	 */
	protected Map<?, ?> putError(Map<Object, Object> map, String value) {
		if (map != null && value != null) {
			putValue(map, ERROR_CODE, value);
			putValue(map, ERROR_TEXT, e(value));
		}
		return map;
	}

	/**
	 * 向 MAP 中添加错误信息，同时转换错误代码为说明
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	protected Map<?, ?> putError(String value) {
		return putError(new HashMap<Object, Object>(), value);
	}

	/**
	 * 向 MAP 中添加信息
	 * 
	 * @param map
	 * @param key
	 * @param value
	 * @return
	 */
	protected Map<?, ?> putValue(Map<Object, Object> map, String key,
			Object value) {
		if (map != null && key != null) {
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 向 MAP 中添加信息
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	protected Map<?, ?> putValue(String key, Object value) {
		return putValue(new HashMap<Object, Object>(), key, value);
	}

	/**
	 * @author QinYong Oct 14, 2013 4:03:49 PM
	 * @return
	 */
	protected BaseContext getPandoraContext() {
		BaseContext pandoraContext = BaseContext.getPandoraContext();
		if (pandoraContext == null) {
			pandoraContext = new BaseContext();
			BaseContext.setPandoraContext(pandoraContext);
		}
		return pandoraContext;
	}
}
