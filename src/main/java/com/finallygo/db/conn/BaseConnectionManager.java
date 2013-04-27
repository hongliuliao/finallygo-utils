package com.finallygo.db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseConnectionManager implements IConnectionManager {

	public static final String driverClass=rb.getString("driver");
	public static final String jdbcUrl=rb.getString("url");
	public static final String userName=rb.getString("user");
	public static final String password=rb.getString("password");
	
	static{
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("加载数据库驱动异常!",e);
		}
	}
	public synchronized Connection getConnection() {
		try {
			return DriverManager.getConnection(jdbcUrl, userName, password);
		} catch (SQLException e) {
			throw new RuntimeException("获取数据库连接异常!",e);
		}
	}

}
