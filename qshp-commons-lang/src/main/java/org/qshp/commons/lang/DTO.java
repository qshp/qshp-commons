/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.lang;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.qshp.commons.lang.page.PageBuilder;
import org.qshp.commons.lang.sort.SortBase;

/**
 * Data transfer object
 * 
 * @author QinYong
 * 
 */
public class DTO<T> extends BaseDomain {

	/**
	 * 成功标记
	 */
	private boolean success = false;

	/**
	 * id
	 */
	protected long id;

	/**
	 * ids
	 */
	protected long[] ids;

	/**
	 * 实体对象
	 */
	protected T object;

	/**
	 * 实例对象列表
	 */
	protected List<T> list;

	/**
	 * 参数列表
	 */
	protected Map<String, Object> paramsMap;

	/**
	 * 返回结果
	 */
	protected Map<String, Object> resultMap;

	/**
	 * 分页
	 */
	protected PageBuilder page;

	/**
	 * 排序
	 */
	protected SortBase sort;

	public DTO() {

	}

	public DTO(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public DTO<T> setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public long getId() {
		return id;
	}

	public DTO<T> setId(long id) {
		this.id = id;
		return this;
	}

	public long[] getIds() {
		return ids;
	}

	public DTO<T> setIds(long[] ids) {
		this.ids = ids;
		return this;
	}

	/**
	 * 取实体对象
	 * 
	 * @return object
	 */
	public T getObject() {
		return object;
	}

	public DTO<T> setObject(T object) {
		this.object = object;
		return this;
	}

	/**
	 * 取实体对象列表
	 * 
	 * @return 实体对象的List
	 */
	public List<T> getList() {
		return list;
	}

	public DTO<T> setList(List<T> list) {
		this.list = list;
		return this;
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public DTO<T> setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
		return this;
	}

	public DTO<T> putParam(String key, Object value) {
		if (this.paramsMap == null)
			this.paramsMap = new LinkedHashMap<String, Object>();
		this.paramsMap.put(key, value);
		return this;
	}

	public Object getParam(String key) {
		return this.paramsMap == null ? null : this.paramsMap.get(key);
	}

	public void removeParam(String key) {
		if (this.paramsMap != null)
			this.paramsMap.remove(key);
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public DTO<T> setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
		return this;
	}

	public DTO<T> putResult(String key, Object value) {
		if (this.resultMap == null)
			this.resultMap = new LinkedHashMap<String, Object>();
		this.resultMap.put(key, value);
		return this;
	}

	public Object getResult(String key) {
		return this.resultMap == null ? null : this.resultMap.get(key);
	}

	public void removeResult(String key) {
		if (this.resultMap != null)
			this.resultMap.remove(key);
	}

	public PageBuilder getPage() {
		return page;
	}

	public DTO<T> setPage(PageBuilder page) {
		this.page = page;
		return this;
	}

	public long getTotalCount() {
		return getPage() == null ? 0 : getPage().getTotalCount();
	}

	public SortBase getSort() {
		return sort;
	}

	public DTO<T> setSort(SortBase sort) {
		this.sort = sort;
		return this;
	}
}
