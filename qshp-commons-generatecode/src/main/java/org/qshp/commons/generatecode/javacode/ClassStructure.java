/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.generatecode.javacode;

import java.io.File;

import org.qshp.commons.util.json.JsonUtils;

/**
 * @author QinYong
 *
 */
public class ClassStructure {

	public static final String HIERARCHY_SEPARATOR = "-";

	public static final String JAVA_PACKAGE = "src/main/java";

	private String projectBaseDir;

	private String projectName;

	private String tableName;

	private String basePackage;
	
	private String domainClassName;

	private String interfaceClassName;

	private String interfacePackageDir;

	private String autowiredClassName;

	private String autowiredPackageDir;



	public String getProjectBaseDir() {
		return projectBaseDir;
	}


	public void setProjectBaseDir(String projectBaseDir) {
		this.projectBaseDir = projectBaseDir;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getClassName(String classNameSuffix) {
		String[] classNameArr =  getTableName().split("_");
		StringBuffer className = new StringBuffer();
		for(int i = 0,j = classNameArr.length; i < j; i++){
			className.append(classNameArr[i].substring(0, 1).toUpperCase());
			className.append(classNameArr[i].substring(1));
			if(i == j-1){
				className.append(classNameSuffix != null ? classNameSuffix :"");
			}

		}
		return className.toString();
	}


	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getPackage() {
		return basePackage+"."+projectName;
	}

	public String getDomainClassName() {
		return domainClassName;
	}

	public void setDomainClassName(String domainClassName) {
		this.domainClassName = domainClassName;
	}
	
	public String getInterfaceClassName() {
		return interfaceClassName;
	}

	public void setInterfaceClassName(String interfaceClassName) {
		this.interfaceClassName = interfaceClassName;
	}

	public String getInterfacePackageDir() {
		return interfacePackageDir;
	}

	public void setInterfacePackageDir(String interfacePackageDir) {
		this.interfacePackageDir = interfacePackageDir;
	}

	public String getAutowiredClassName() {
		return autowiredClassName;
	}

	public void setAutowiredClassName(String autowiredClassName) {
		this.autowiredClassName = autowiredClassName;
	}

	public String getAutowiredPackageDir() {
		return autowiredPackageDir;
	}

	public void setAutowiredPackageDir(String autowiredPackageDir) {
		this.autowiredPackageDir = autowiredPackageDir;
	}
	
	public String getClassFullPath(String hierarchyName,String className) {
		StringBuffer classFullPath = new StringBuffer(50);
		classFullPath.append(projectBaseDir).append(File.separator);
		classFullPath.append(projectName).append(HIERARCHY_SEPARATOR).append(hierarchyName).append(File.separator);
		classFullPath.append(JAVA_PACKAGE).append(File.separator);
		classFullPath.append(basePackage.replaceAll("\\.", File.separator)).append(File.separator);
		classFullPath.append(className).append(".java");
		return classFullPath.toString();
	}
	
	public String toString(){
		return JsonUtils.objectToJson(this);
	}

}
