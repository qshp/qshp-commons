/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.generatecode.javacode.impl;


/**
 * @author QinYong
 *
 */
public class GenerateManagerInterface extends GenerateBaseInterface{
	public static final String TEMPLATE_PATH = "template/ManagerInterfaceTemplate.txt";

	@Override
	public String getTemplatePath() {
		return TEMPLATE_PATH;
	}

	@Override
	public String getClassNameSuffix() {
		return "Manager";
	}

	@Override
	public String getHierarchyName() {
		
		return "manager";
	}
	
}
