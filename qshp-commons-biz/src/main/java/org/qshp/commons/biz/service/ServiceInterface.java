/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.biz.service;

import java.util.List;

import org.qshp.commons.lang.DTO;

/**
 * 
 * @author QinYong
 * 
 */
public interface ServiceInterface {

	/**
	 * 插入数据
	 * 
	 * @param t
	 * @return
	 */
	public <T> DTO<T> add(T t);

	/**
	 * 更新数据
	 * 
	 * @param t
	 * @return
	 */
	public <T> DTO<T> modify(T t);

	/**
	 * 删除数据
	 * 
	 * @param dto
	 * @return
	 */
	public void delOne(DTO<?> dto);

	/**
	 * 删除多条数据
	 * 
	 * @param dto
	 */
	public void delAll(DTO<?> dto);

	/**
	 * 执行查询
	 * 
	 * @author QinYong Sep 24, 2013 2:41:37 PM
	 * @param dto
	 * @return
	 */
	public <T> List<T> query(DTO<T> dto);
	
	/**
	 * 分页查询
	 * 
	 * @author QinYong
	 * @param dto
	 * @return List<T>
	 */
	public <T> DTO<T> queryPaging(DTO<T> dto);

	/**
	 * 查询总数
	 * 
	 * @param dto
	 * @return
	 */
	public long queryCount(DTO<?> dto);

	/**
	 * 根据ID取得详情
	 * 
	 * @param id
	 * @return
	 */
	public <T> T getById(long id);

}
