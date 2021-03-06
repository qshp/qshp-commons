/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.generatecode;

import org.qshp.commons.generatecode.javacode.ClassStructure;
import org.qshp.commons.generatecode.javacode.GenerateClass;
import org.qshp.commons.generatecode.javacode.impl.GenerateDomain;
import org.qshp.commons.generatecode.javacode.impl.GenerateManagerInterface;
import org.qshp.commons.generatecode.javacode.impl.GenerateMapper;
import org.qshp.commons.generatecode.javacode.impl.GenerateServiceInterface;
import org.qshp.commons.generatecode.javacode.impl.GenereateManagerImplementClass;
import org.qshp.commons.generatecode.javacode.impl.GenereateServiceImplementClass;

/**
 * @author QinYong
 * 
 */
public class GenerateJavaCodeTest {

	public static String projectDir = "/Users/muyu/dev/git/muyu";

	public static String projectName = "muyu";

	public static String tableName = "performance_info";

	public static String groupId = "com.muyu";

	public static String packageSuffix = "perform";

	public static void main(String[] args) {

		System.out.println("start ......");
		ClassStructure structure = new ClassStructure();
		structure.setTableName(tableName);
		structure.setProjectBaseDir(projectDir);
		structure.setProjectName(projectName);
		structure.setDomainClassName(structure.getClassName(null));
		
		// domain
		GenerateClass code = new GenerateDomain();
		structure.setBasePackage(groupId+".domain."+packageSuffix);
		code.generateClass(structure);

//		// mapper
//		code = new GenerateMapper();
//		structure.setBasePackage(groupId+".dao."+packageSuffix);
//		code.generateClass(structure);
////
////		// set Autowired
//		structure.setAutowiredClassName(structure.getClassName("Mapper"));
//		structure.setAutowiredPackageDir(structure.getBasePackage());
////
////		// manager
//		code = new GenerateManagerInterface();
//		structure.setBasePackage(groupId+".manager."+packageSuffix);
//		code.generateClass(structure);
////
////		// set interface
//		structure.setInterfaceClassName(structure.getClassName("Manager"));
//		structure.setInterfacePackageDir(structure.getBasePackage());
////
////		// manager impl
//		GenerateClass implementClass = new GenereateManagerImplementClass();
//		structure.setBasePackage(groupId+".manager."+packageSuffix+".impl");
//		implementClass.generateClass(structure);
////
////		// set Autowired
//		structure.setAutowiredClassName(structure.getClassName("Manager"));
//		structure.setAutowiredPackageDir(structure.getBasePackage());
////
////		// service
//		code = new GenerateServiceInterface();
//		structure.setBasePackage(groupId+".service."+packageSuffix);
//		code.generateClass(structure);
////
////		// set interface
//		structure.setInterfaceClassName(structure.getClassName("Service"));
//		structure.setInterfacePackageDir(structure.getBasePackage());
////
////		// service impl
//		implementClass = new GenereateServiceImplementClass();
//		structure.setBasePackage(groupId + ".service."+packageSuffix+".impl");
//				implementClass.generateClass(structure);

		System.out.println("end ......");
	}
}
