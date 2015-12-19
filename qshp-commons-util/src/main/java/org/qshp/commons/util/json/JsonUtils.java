/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.util.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * @author QinYong 2013-12-17 上午10:33:51
 *
 */
public class JsonUtils {

	private static ObjectMapper objectMapper = null;
	private static JsonFactory jsonFactory = null;

	private static ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper;
	}

	private static JsonFactory getJsonFactory() {
		if (jsonFactory == null) {
			jsonFactory = getObjectMapper().getJsonFactory();
		}
		return jsonFactory;
	}

	/**
	 * 判断是否是标准的json格式
	 * @author QinYong 2013-12-17 上午10:35:22
	 * @param json
	 * @return
	 */
	public static boolean isValidJson(final String json) {
		boolean valid = false;
		try {
			final JsonParser parser = getJsonFactory().createJsonParser(json);
			while (parser.nextToken() != null) {
			}
			valid = true;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}

	/**
	 * json转换为指定类型对象
	 * @author QinYong 2013-12-17 上午10:36:52
	 * @param json
	 * @param classType
	 * @return
	 */
	public static <T> T jsonToObject(String json, Class<T> classType) {
		T result = null;
		try {
			result = getObjectMapper().readValue(json, classType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * InputStream类型的json转换为指定类型对象
	 * @author QinYong 2013-12-17 上午10:42:08
	 * @param jsonStream
	 * @param classType
	 * @return
	 */
	public static <T> T inputStreamToObject(InputStream jsonStream, Class<T> classType) {
		T result = null;
		try {
			result = getObjectMapper().readValue(jsonStream, classType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * json转换为map
	 * @author QinYong 2013-12-17 上午10:41:00
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String json) {
		Map<String, Object> result = null;
		if(!isValidJson(json) || json == null){
			json = "{}";
		}
		result = jsonToObject(json, Map.class);
		return result;
	}

	/**
	 * json转换为list
	 * @author QinYong 2013-12-17 上午10:50:37
	 * @param json
	 * @return
	 */
	public static List<?> jsonToAnyList(String json){
		List<?> result = null;
		if(!isValidJson(json) || json == null || json.trim().equals("")){
			json = "[]";
		}
		result = jsonToObject(json, List.class);
		return result;
	}

	/**
	 * json转换为map对象的list
	 * @author QinYong 2013-12-17 上午10:50:50
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> jsonToMapList(String json){
		List<Map<String, Object>> result = null;
		if(!isValidJson(json) || json == null || json.trim().equals("")){
			json = "[]";
		}
		result = jsonToObject(json, List.class);
		return result;
	}

	/**
	 * 对象转换为json
	 * @author QinYong 2013-12-19 下午3:46:41
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object){
		String result = null;
		if(object != null){
			try {
				result = getObjectMapper().writeValueAsString(object);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * map转换为json
	 * @author QinYong 2013-12-19 下午3:46:52
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, Object> map){
		String result = null;
		result = objectToJson(map);
		return result;
	}
	
	/**
	 * list转换为json
	 * @author QinYong 2013-12-19 下午3:47:30
	 * @param list
	 * @return
	 */
	public static String listToJson(List<?> list){
		String result = null;
		result = objectToJson(list);
		return result;
	}
	
}
