/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.lang.page;

/**
 * 
 * @author QinYong
 * 
 */
public interface PageBuilder {

	/**
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(long totalCount);

	/**
	 * 
	 * @param page
	 */
	public void setPage(long page);

	/**
	 * 
	 * @param pageSize
	 */
	public void setPageSize(long pageSize);

	/**
	 * 取得记录总数
	 * 
	 * @return 返回 long 型的记录总数
	 */
	public long getTotalCount();

	/**
	 * 取得记录分页后的总页数
	 * 
	 * @return
	 */
	public long getTotalPage();

	/**
	 * 取得当前页
	 * 
	 * @return
	 */
	public long getPage();

	/**
	 * 取得每页显示记录条数
	 * 
	 * @return
	 */
	public long getPageSize();

	/**
	 * 取得记录起始位置，在 SQL 中调用 从0开始
	 * 
	 * @return
	 */
	public long getStartIndex();

	/**
	 * 取得记录的结束位置，要 SQL 中调用
	 * 
	 * @return
	 */
	public long getEndIndex();

	/**
	 * 取得 mysql limit 字符串
	 * 
	 * @return
	 */
	public String getPosition();

}
