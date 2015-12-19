/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.lang.enums;

/**
 * 
 * @author QinYong
 * 
 */
public enum Bool {
	FALSE(0, false, "否"), TRUE(1, true, "是");

	private int value;
	private boolean bool;
	private String text;

	private Bool(int value, boolean bool, String text) {
		this.value = value;
		this.bool = bool;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String getText() {
		return text;
	}

	public boolean isTrue() {
		return bool;
	}

	public static Bool get(int value) {
		for (Bool p : Bool.values()) {
			if (p.getValue() == value)
				return p;
		}
		throw new IllegalArgumentException("unknown value:" + value);
	}
}
