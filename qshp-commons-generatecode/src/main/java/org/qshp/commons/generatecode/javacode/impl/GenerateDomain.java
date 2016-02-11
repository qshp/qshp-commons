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
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.qshp.commons.generatecode.javacode.ClassStructure;
import org.qshp.commons.generatecode.javacode.ColumnInfo;
import org.qshp.commons.generatecode.javacode.GenerateClass;
import org.qshp.commons.generatecode.conn.DBUtils;
import org.qshp.commons.lang.logging.Log;
import org.qshp.commons.lang.logging.LogFactory;

/**
 * @author QinYong
 * 
 */
public class GenerateDomain implements GenerateClass {

	static Log log = LogFactory.getLog(GenerateDomain.class);
	public static final String PACKAGE_FLAG = "\\$\\{package\\}";
	public static final String DEPEND_PACKAGE_FLAG = "\\$\\{dependPackages\\}";
	public static final String CLASS_FLAG = "\\$\\{className\\}";
	public static final String FIELD_FLAG = "\\$\\{fields\\}";
	public static final String METHOD_FLAG = "\\$\\{methods\\}";
	public static final String TEMPLATE_PATH = "template/DomainTemplate.txt";
	public static final String ENCODING = "UTF-8";
	public static final String PROJECT_HIERARCHY_NAME ="domain";
//	public static final String PROJECT_SOURCE_PACKAGE = File.separator+"src"+File.separator+"main"+File.separator+"java";
	@Override
	public void generateClass(ClassStructure structure) {
		try {
			String sql = "show full COLUMNS from " + structure.getTableName();
			List<ColumnInfo> columns = getColumInfo(sql);
			String templateContent = FileUtils.readFileToString(new File(
					ClassLoader.getSystemResource(TEMPLATE_PATH).getPath()),
					ENCODING);
			String className = structure.getClassName(null);
			templateContent = replaceFlag(templateContent, structure.getPackage(),className, columns);
			File javaFilePath = new File(structure.getClassFullPath(PROJECT_HIERARCHY_NAME,className));
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
//		String className =  structure.getTableName().split("_")[1];
		String[] classNameArr =  structure.getTableName().split("_");
		StringBuffer className = new StringBuffer();
		for(String name : classNameArr){
			className.append(name.substring(0, 1).toUpperCase()
					+ name.substring(1));
		}
		return className.toString();
	}
	
	private String replaceFlag(String templateContent, String packageDir,
			String className, List<ColumnInfo> columns) {
		StringBuffer packageInfo = new StringBuffer();
		StringBuffer fieldInfo = new StringBuffer();
		StringBuffer methodInfo = new StringBuffer();
		ClassHandle handle = new ClassHandle();
		for (ColumnInfo columnInfo : columns) {
			handle.handleClass(columnInfo, packageInfo, fieldInfo, methodInfo);
		}
		return templateContent.replaceAll(PACKAGE_FLAG, packageDir)
				.replaceAll(DEPEND_PACKAGE_FLAG, packageInfo.toString())
				.replaceAll(CLASS_FLAG, className)
				.replaceAll(FIELD_FLAG, fieldInfo.toString())
				.replaceAll(METHOD_FLAG, methodInfo.toString());
	}

	@SuppressWarnings("unchecked")
	private List<ColumnInfo> getColumInfo(String sql) {
		DBUtils.HandleResult.RESULT_TYPE.put(sql, ColumnInfo.class);
		DBUtils db = DBUtils.getInstance();
		List<ColumnInfo> columns = (List<ColumnInfo>) db.query(sql);
		db.closeConn();
		return columns;
	}

	static class ClassHandle {

		static String PACKAGE_PREFIX = "import ";

		static String PACKAGE_SUFFIX = ";\r\n\r\n";

		static String FIELD_PREFIX = "\tprivate ";

		static String FIELD_SUFFIX = PACKAGE_SUFFIX;

		public void handleClass(ColumnInfo columnInfo,
				StringBuffer packageBuff, StringBuffer fieldBuff,
				StringBuffer methodBuff) {

			String methodField = getJavaField(columnInfo.getField());
			String field = methodField;
			if(methodField.length() > 1){
				field = methodField.substring(0,1).toLowerCase() +
						methodField.substring(1,methodField.length());
			}



			String type = columnInfo.getType();
			String javaType;
			String javaPackagePath = null;
			if (type.startsWith("bigint")) {
				javaType = "long";
			} else if (type.startsWith("int")) {
				javaType = "int";
			} else if (type.startsWith("smallint")) {
				javaType = "int";
			} else if (type.startsWith("tinyint")) {
				javaType = "short";
			} else if (type.startsWith("timestamp") ||
					type.startsWith("datetime") ||
					type.startsWith("date") ) {
				javaType = "Date";
				javaPackagePath = "java.util.Date";
			} else {
				javaType = "String";
			}

			fieldBuff.append(FIELD_PREFIX).append(javaType).append(" ")
					.append(field).append(FIELD_SUFFIX);

			// import package
			if (javaPackagePath != null) {
				String importPackage = PACKAGE_PREFIX + javaPackagePath
						+ PACKAGE_SUFFIX;
				if (packageBuff.indexOf(importPackage) == -1) {
					packageBuff.append(importPackage);
				}
			}

			// setMethod
			methodBuff.append("\tpublic void ").append("set").append(methodField)
					.append("(").append(javaType).append(" ").append(field)
					.append(") {\r\n");
			methodBuff.append("\t\tthis.").append(field).append(" = ")
					.append(field).append(";\r\n\t}\t\r\n\r\n");

			// getMethod
			methodBuff.append("\tpublic ").append(javaType).append(" get")
					.append(methodField).append("() {\r\n");
			methodBuff.append("\t\treturn ").append(field)
					.append(";\r\n\t}\r\n\r\n");

		}
		private String getJavaField(String columnField){
			String[] fieldNames = columnField.split("_");
			StringBuffer fieldBuff = new StringBuffer(columnField.length());
			for(String fieldName :fieldNames){
				fieldBuff.append(fieldName.substring(0,1).toUpperCase())
						.append(fieldName.substring(1,fieldName.length()));
			}
			return fieldBuff.toString();

		}
	}



	public static void main(String[] args) {
		ClassHandle domain = new GenerateDomain.ClassHandle();
		String str = "et_ui_kk";
		System.out.println(domain.getJavaField(str));
	}

}
