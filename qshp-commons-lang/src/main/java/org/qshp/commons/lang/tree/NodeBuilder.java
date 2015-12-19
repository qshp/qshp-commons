/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the
 * confidential and proprietary information of 360buy.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with 360buy.com.
 */

package org.qshp.commons.lang.tree;

/**
 * 
 * Node接口类，数据类需要实现该接口
 * 
 * @author QinYong
 * 
 */
public interface NodeBuilder {

	public String getNodeId();

	public void setNodeId(String nodeId);

	public String getParentNodeId();

	public void setParentNodeId(String parentNodeId);
}
