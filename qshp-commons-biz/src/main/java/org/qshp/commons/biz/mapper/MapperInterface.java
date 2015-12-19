/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.biz.mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author QinYong
 * 
 */
public interface MapperInterface {

	/**
	 * 插入记录
	 * 
	 * @author QinYong
	 * @param t
	 * @return
	 */
	public <T> int insert(T t);

	/**
	 * 根据 id 更新记录
	 * 
	 * @author QinYong
	 * @param t
	 * @return
	 */
	public <T> int update(T t);

	/**
	 * 根据 id 删除单条记录
	 * 
	 * @author QinYong
	 * @param id
	 * @return
	 */
	public int deleteById(long id);

	/**
	 * 根据 id 取单条记录
	 * 
	 * @author QinYong
	 * @param id
	 * @return
	 */
	public <T> T getById(long id);

	/**
	 * 查询总数
	 * 
	 * @author QinYong
	 * @param map
	 * @return
	 */
	public long queryCount(Map<String, Object> map);

	/**
	 * 查询列表
	 * 
	 * @author QinYong
	 * @param map
	 * @return
	 */
	public <T> List<T> query(Map<String, Object> map);
}
