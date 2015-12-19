/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.biz.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 
 * @author QinYong
 * 
 */
public abstract class TransactionManagerSupport extends ManagerSupport {

	@Autowired
	protected DataSourceTransactionManager transactionManager;

	/**
	 * 取得事务模板
	 * 
	 * @author QinYong
	 * @return
	 */
	public TransactionTemplate getTransactionTemplate() {
		return new TransactionTemplate(transactionManager);
	}

	/**
	 * del重名，在事务回调中使用 this 和 supper 不能正确调用方法，用于中转
	 * 
	 * @author QinYong
	 * @param id
	 * @return
	 */
	protected int delForInside(long id) {
		return super.del(id);
	}

	/**
	 * del重名，在事务回调中使用 this 和 supper 不能正确调用方法，用于中转
	 * 
	 * @author QinYong
	 * @param ids
	 * @return
	 */
	protected int delForInside(long[] ids) {
		return super.del(ids);
	}

	@Override
	protected int del(final long[] ids) {
		return getTransactionTemplate().execute(
				new TransactionCallback<Integer>() {

					@Override
					public Integer doInTransaction(TransactionStatus arg0) {
						return delForInside(ids);
					}
				}).intValue();
	}
}
