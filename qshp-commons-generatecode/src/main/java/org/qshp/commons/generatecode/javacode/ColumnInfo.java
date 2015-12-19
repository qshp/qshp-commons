/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.generatecode.javacode;

/**
 * @author QinYong
 *
 *show full COLUMNS from table
 */
public class ColumnInfo {

	private String Field;
	
	private String Type;
	
	private String Collation;
	
	private String Null;
	
	private String Key;
	
	private String Default;
	
	private String Extra;
	
	private String Privileges;
	
	private String Comment;

	public String getField() {
		return Field;
	}

	public void setField(String field) {
		Field = field;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getCollation() {
		return Collation;
	}

	public void setCollation(String collation) {
		Collation = collation;
	}

	public String getNull() {
		return Null;
	}

	public void setNull(String null1) {
		Null = null1;
	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	public String getDefault() {
		return Default;
	}

	public void setDefault(String default1) {
		Default = default1;
	}

	public String getExtra() {
		return Extra;
	}

	public void setExtra(String extra) {
		Extra = extra;
	}

	public String getPrivileges() {
		return Privileges;
	}

	public void setPrivileges(String privileges) {
		Privileges = privileges;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}
	
	
}
