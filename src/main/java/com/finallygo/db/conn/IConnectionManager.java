package com.finallygo.db.conn;

import java.sql.Connection;
import java.util.ResourceBundle;

public interface IConnectionManager {
	public static ResourceBundle rb = ResourceBundle.getBundle("connection");
	
	Connection getConnection();
}
