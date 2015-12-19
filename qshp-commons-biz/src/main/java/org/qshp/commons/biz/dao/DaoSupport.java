/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.biz.dao;

import java.util.List;
import java.util.Map;

import org.qshp.commons.biz.mapper.MapperInterface;

/**
 * 
 * @author QinYong
 * 
 */
public abstract class DaoSupport implements DaoInterface {

	public abstract MapperInterface getMapper();

	/**
	 * 添加一条数据到数据库中，如果需要处理业务逻辑，子类中重写该方法
	 */
	@Override
	public <T> long insert(T t) {
		return this.getMapper().insert(t);
	}

	/**
	 * 根据ID修改一条数据，如果需要添加业务逻辑，子类中重写该方法
	 */
	@Override
	public <T> int update(T t) {
		return this.getMapper().update(t);
	}

	/**
	 * 根据ID修改一条数据，如果需要添加业务逻辑，子类中重写该方法
	 * 
	 * @author QinYong Oct 18, 2013 4:28:20 PM
	 * @param id
	 * @return
	 */
	@Override
	public int deleteById(long id) {
		return this.getMapper().deleteById(id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getById(long id) {
		return (T) getMapper().getById(id);
	}

	@Override
	public <T> List<T> query(Map<String, Object> map) {
		return getMapper().query(map);
	}

	@Override
	public long queryCount(Map<String, Object> map) {
		return getMapper().queryCount(map);
	}
}
