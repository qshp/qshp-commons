/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the
 * confidential and proprietary information of 360buy.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with 360buy.com.
 */

package org.qshp.commons.lang.tree;

import java.io.Serializable;

/**
 * Node 基础类
 * 
 * @author QinYong
 * 
 */
public class NodeBase implements Serializable {

	private static final long serialVersionUID = -5838334158312443998L;

	private String nodeId;
	private String parentNodeId;

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}
}
