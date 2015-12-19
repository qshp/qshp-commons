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
public enum Status {

	NONE(0, "未启用"), AVAILABLE(1, "可用"), DISABLE(2, "不可用");

	private int value;
	private String text;

	private Status(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String getText() {
		return text;
	}

	public static Status valueOf(int value) {
		for (Status p : Status.values()) {
			if (p.getValue() == value)
				return p;
		}
		throw new IllegalArgumentException("unknown value:" + value);
	}

}
