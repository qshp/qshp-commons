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
public abstract class GenerateBaseImplementClass implements
		GenerateClass {

	static Log log = LogFactory.getLog(GenerateBaseImplementClass.class);

	public static final String PACKAGE_FLAG = "\\$\\{package\\}";

	public static final String DEPEND_PACKAGE_FLAG = "\\$\\{dependPackages\\}";

	public static final String CLASS_FLAG = "\\$\\{className\\}";

	public static final String MANAGER_INTERFACE_FLAG = "\\$\\{interfaceClass\\}";
	
	public static final String AUTOWIRED_DEPEND_FLAG = "\\$\\{autowiredDepend\\}";
	
	public static final String AUTOWIRED_CLASS_INSTANCE_FLAG = "\\$\\{autowiredClassInstance\\}";

	public abstract String getTemplatePath();

	public abstract String getHierarchyName();
	
	public abstract String getClassNameSuffix();
	
	@Override
	public void generateClass(
			ClassStructure structure){
		// className
		String className = structure.getClassName(getClassNameSuffix());
		try {
			String templateContent = FileUtils.readFileToString(
					new File(ClassLoader.getSystemResource(getTemplatePath())
							.getPath()), ENCODING);
			templateContent = replaceFlag(templateContent, structure);
			File javaFilePath = new File(structure.getClassFullPath(getHierarchyName(), className));
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
	
	public String getClassName(ClassStructure structure) {
		String className =  structure.getTableName().split("_")[1];
		return className.substring(0, 1).toUpperCase()
					+ className.substring(1)+(getClassNameSuffix() != null ? getClassNameSuffix() :"");
	}

	static String PACKAGE_PREFIX = "import ";

	static String SUFFIX = ";\r\n";
	

	protected String replaceFlag(String templateContent, ClassStructure structur) {

		StringBuffer dependPackage = new StringBuffer();

		// import Interface package
		dependPackage.append(PACKAGE_PREFIX)
				.append(structur.getInterfacePackageDir()).append(".")
				.append(structur.getInterfaceClassName())
				.append(SUFFIX);

	
		
		
		//Autowired		
		StringBuffer autowiredDepend = new StringBuffer();
		String autowiredClassInstance = null;
		if(structur.getAutowiredClassName() != null){
			// import Autowired depend
			dependPackage.append(PACKAGE_PREFIX)
					.append(structur.getAutowiredPackageDir()).append(".")
					.append(structur.getAutowiredClassName())
					.append(SUFFIX);
			
			// autowiredClassInstance
			autowiredClassInstance = structur.getAutowiredClassName().length() > 1 ? structur
					.getAutowiredClassName().substring(0, 1).toLowerCase()
					+ structur.getAutowiredClassName().substring(1)
					: structur.getAutowiredClassName().toLowerCase();
					
			autowiredDepend
			.append(structur.getAutowiredClassName())
			.append(" ")
			.append(autowiredClassInstance)
			.append(SUFFIX);
		}
		
		
		return templateContent
				.replaceAll(PACKAGE_FLAG, structur.getPackage())
				.replaceAll(DEPEND_PACKAGE_FLAG, dependPackage.toString())
				.replaceAll(CLASS_FLAG, structur.getClassName(getClassNameSuffix()))
				.replaceAll(MANAGER_INTERFACE_FLAG,
						structur.getInterfaceClassName())
			    .replaceAll(AUTOWIRED_DEPEND_FLAG, autowiredDepend.toString())
			    .replaceAll(AUTOWIRED_CLASS_INSTANCE_FLAG, autowiredClassInstance);
	}

}
