/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.generatecode.conn;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.qshp.commons.generatecode.javacode.ColumnInfo;

/**
 * @author QinYong
 * 
 */
public class DBUtils {

	private static String username = "root";

	private static String password = "root";

	private static String url = "jdbc:mysql://localhost:3306/flowers?useUnicode=true&amp;characterEncoding=UTF8";

	private static String driver = "com.mysql.jdbc.Driver";

	private Connection conn;
	
	private HandleResult handle;

	private DBUtils() {
		init();
	}

	private void init() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			handle = new HandleResult();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DBUtils getInstance() {
		return new DBUtils();
	}
	
	

	public List<?> query(String sql) {
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			return handle.handleResult(rs, HandleResult.RESULT_TYPE.get(sql));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static class HandleResult {
		public static Map<String, Class<?>> RESULT_TYPE = new HashMap<String, Class<?>>();
		public List<?> handleResult(ResultSet rs, Class<?> clazz)
				throws SQLException, ReflectiveOperationException,
				SecurityException {
			final ResultSetMetaData metaData = (ResultSetMetaData)rs.getMetaData();
			String[] columnNames = parseColumnName(metaData);
			List<Object> list = new ArrayList<Object>();
			Field field;
			Method method;
			Object obj;
			while (rs.next()) {
				obj = clazz.newInstance();
				for (String columnName : columnNames) {
					field = clazz.getDeclaredField(columnName);
					method = clazz.getMethod("set" + columnName,
							field.getType());
					method.invoke(obj,
							rs.getObject(columnName));
				}
				list.add(obj);
			}
			return list;
		}

		private String[] parseColumnName(ResultSetMetaData metaData)
				throws SQLException {
			final int columnCount = metaData.getColumnCount();
			String[] columnNames = new String[columnCount];
			for (int i = 0; i < columnCount; i++) {
				columnNames[i] = metaData.getColumnLabel(i+1);
			}
			return columnNames;
		}
	}

	public void rollback() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConn() {
		try {
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		DBUtils db = DBUtils.getInstance();
		String sql = "show full COLUMNS from t_user";
		DBUtils.HandleResult.RESULT_TYPE.put(sql, ColumnInfo.class);
		List<ColumnInfo> list = (List<ColumnInfo>) db.query(sql);
		for(ColumnInfo column: list){
			System.out.println(column);
		}
		db.closeConn();

	}
}
