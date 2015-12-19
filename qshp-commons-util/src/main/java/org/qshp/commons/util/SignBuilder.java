/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.qshp.commons.util.codec.Md5Utils;

public class SignBuilder {

	private SignBuilder(){}

	/**
	 * 根据数组取签名
	 * 
	 * @author QinYong
	 * @param params
	 * @param key
	 * @param charset
	 * @return
	 */
	public static String buildSign(Map<String, Object> params, String key,
			String charset) {
		// 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串再与安全校验码直接连接起来，最后取签名
		return Md5Utils.encrypt(createLinkString(params) + key, charset);
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @author QinYong
	 * @param params
	 * @return
	 */
	public static String createLinkString(Map<String, Object> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		StringBuffer sb = new StringBuffer();

		for (String key : keys) {
			if (key.equals("") || key == null
					|| key.equalsIgnoreCase("signature"))
				continue;

			if (sb.length() > 0) // 拼接时，不包括最后一个&字符
				sb.append("&");

			sb.append(key);
			sb.append("=");

			Object param = params.get(key);
			if (param instanceof String[])
				sb.append(arrayToString((String[]) param));
			else if (Object[].class.isAssignableFrom(param.getClass())) {
				Object[] objectArray = (Object[]) param;
				String[] objectString = new String[objectArray.length];
				for (int i = 0; i < objectArray.length; i++) {
					objectString[i] = String.valueOf(objectArray[i]);
				}
				sb.append(arrayToString(objectString));
			} else {
				sb.append(null2blank(param == null ? "" : param.toString()));
			}
		}

		return sb.toString();
	}

	/**
	 * 字符串转成数组
	 * 
	 * @author QinYong
	 * @param input
	 * @return
	 */
	private static String arrayToString(String[] input) {
		StringBuffer sb = new StringBuffer();
		Arrays.sort(input);
		for (int i = 0; i < input.length; i++) {
			if (sb.length() > 0)
				sb.append(",");
			sb.append(null2blank(input[i]));
		}
		return sb.toString();
	}

	/**
	 * @author QinYong
	 * @param input
	 * @return
	 */
	private static String null2blank(String input) {
		return input == null ? "" : input;
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("identity", "user1identity");
		map.put("apiName", "myTestApi2");
		// map.put("pa", "1");
		// map.put("pb", "2");
		map.put("abcd", new Integer[] { 1, 3, 4 });

		System.out.println(SignBuilder.buildSign(map, "user1key", "GBK"));
	}
}
