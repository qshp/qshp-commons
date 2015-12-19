/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 构造Map
 * 
 * @author QinYong
 * 
 */
public class QshpMapBuilder {

	private Map<Object, Object> map = new LinkedHashMap<Object, Object>();

	private QshpMapBuilder() {

	}

	@SuppressWarnings("unchecked")
	private QshpMapBuilder(Map<?, ?> map) {
		this.map = (Map<Object, Object>) map;
	}

	public static QshpMapBuilder newInstance() {
		return new QshpMapBuilder();
	}

	public static QshpMapBuilder newInstance(Map<?, ?> map) {
		return new QshpMapBuilder(map);
	}

	/**
	 * 将 value 放到 map
	 * 
	 * @author QinYong 
	 * @param key
	 * @param value
	 * @return
	 */
	public QshpMapBuilder put(Object key, Object value) {
		this.map.put(key, value);
		return this;
	}

	/**
	 * 将 map 对象添加到 map
	 * 
	 * @author QinYong
	 * @param map
	 * @return
	 */
	public QshpMapBuilder putMap(Map<?, ?> map) {
		this.map.putAll(map);
		return this;
	}

	/**
	 * 将 list 数组添加到 map
	 * 
	 * @author QinYong
	 * @param key
	 * @param list
	 * @return
	 */
	public QshpMapBuilder pubArray(Object key, List<Object> list) {
		this.map.put(key, list);
		return this;
	}

	public Map<?, ?> build() {
		return map;
	}
}
