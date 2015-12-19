/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.biz.manager;

import java.util.List;

import org.qshp.commons.lang.DTO;

/**
 * 
 * @author QinYong
 * 
 */
public interface ManagerInterface {

	/**
	 * 插入数据
	 * 
	 * @param o
	 * @return
	 */
	public <T> long add(T t);

	/**
	 * 更新数据
	 * 
	 * @param o
	 * @return
	 */
	public <T> int modify(T t);

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public int delOne(DTO<?> dto);

	/**
	 * 删除数据
	 * 
	 * @param dto
	 * @return
	 */
	public int delAll(DTO<?> dto);

	/**
	 * 执行查询
	 * 
	 * @author QinYong Sep 24, 2013 2:44:04 PM
	 * @param dto
	 * @return
	 */
	public <T> List<T> query(DTO<T> dto);

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
