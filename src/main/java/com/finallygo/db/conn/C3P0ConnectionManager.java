package com.finallygo.db.conn;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0ConnectionManager implements IConnectionManager {
	
	private static final ComboPooledDataSource cpds=new ComboPooledDataSource();
	public static final String driverClass=rb.getString("driver");
	public static final String jdbcUrl=rb.getString("url");
	public static final String userName=rb.getString("user");
	public static final String password=rb.getString("password");
	static{
		try {
			cpds.setDriverClass(driverClass);
			cpds.setJdbcUrl(jdbcUrl);
			cpds.setUser(userName);
			cpds.setPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized Connection getConnection() {
		try {
			return cpds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("在c3p0连接池中获取连接异常!",e);
		}
	}

}
