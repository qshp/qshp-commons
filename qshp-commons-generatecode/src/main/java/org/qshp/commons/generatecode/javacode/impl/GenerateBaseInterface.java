/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.generatecode.javacode.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.qshp.commons.generatecode.javacode.ClassStructure;
import org.qshp.commons.generatecode.javacode.GenerateClass;
import org.qshp.commons.lang.logging.Log;
import org.qshp.commons.lang.logging.LogFactory;

/**
 * @author QinYong
 * 
 */
public abstract class GenerateBaseInterface implements GenerateClass {

	static Log log = LogFactory.getLog(GenerateManagerInterface.class);
	
	public static final String PACKAGE_FLAG = "\\$\\{package\\}";
	
	public static final String CLASS_FLAG = "\\$\\{className\\}";

	public abstract String getTemplatePath();
	
	public abstract String getClassNameSuffix();
	
	public abstract String getHierarchyName();
	
	@Override
	public void generateClass(ClassStructure structure) {
		try {
			String templateContent = FileUtils.readFileToString(new File(
					ClassLoader.getSystemResource(getTemplatePath()).getPath()),
					ENCODING);
			String className = structure.getClassName(getClassNameSuffix());
			templateContent = replaceFlag(templateContent, structure.getPackage(),
					className);
			File javaFilePath = new File(structure.getClassFullPath(getHierarchyName(),className));
			if (!javaFilePath.getParentFile().exists()) {
				javaFilePath.getParentFile().mkdirs();
			}
			FileUtils
					.writeStringToFile(javaFilePath, templateContent, ENCODING);
		System.out.println("success, filePath=" + javaFilePath);
		} catch (IOException e) {
			log.error("auto generate java code error ! "+structure, e);
		}
	}

	protected String replaceFlag(String templateContent, String packageDir,
			String className) {
		return templateContent.replaceAll(PACKAGE_FLAG, packageDir).replaceAll(
				CLASS_FLAG, className);
	}

}
