/*
 * Copyright 1998-2014 jcloud.com All right reserved. This software is the
 * confidential and proprietary information of jcloud.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with jcloud.com.
 */

/**
 * 
 */
package org.qshp.commons.biz.manager;

import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author QinYong
 */
public interface TransactionManagerInterface extends ManagerInterface {

	/**
	 * 获取事务模版
	 * @author QinYong
	 * @return TransactionTemplate
	 */
	public TransactionTemplate getTransactionTemplate();
}
