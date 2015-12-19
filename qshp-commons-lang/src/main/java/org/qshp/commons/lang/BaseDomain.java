/*
 * Copyright 1998-2012 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.lang;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class BaseDomain {

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
