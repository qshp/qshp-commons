/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.web.result;

import java.util.List;

/**
 * @author QinYong
 * 
 */
public class Result implements Cloneable {

	private String code;

	/**
	 * success or error or msg
	 */
	private String type;
	private String title;
	private String subject;
	private String suggestTitle;
	private List<String> suggest;
	private String auto; // 自动跳转 0:不自动跳转；1:自动跳转
	private String index;
	private String url;

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public String getSubject() {
		return subject;
	}

	public List<String> getSuggest() {
		return suggest;
	}

	public String getAuto() {
		return auto;
	}

	public String getIndex() {
		return index;
	}

	public String getUrl() {
		return url;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSuggestTitle() {
		return suggestTitle;
	}

	public void setSuggestTitle(String suggestTitle) {
		this.suggestTitle = suggestTitle;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setSuggest(List<String> suggest) {
		this.suggest = suggest;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Result clone() {
		Result result = null;
		try {
			result = (Result) super.clone();
		} catch (CloneNotSupportedException e) {
			// nothing to do
		}
		return result;
	}
}
