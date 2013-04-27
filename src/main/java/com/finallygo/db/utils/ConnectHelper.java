package com.finallygo.db.utils;

import java.sql.Connection;
import java.util.ResourceBundle;

import com.finallygo.db.conn.ConnectionFactory;
import com.finallygo.db.conn.IConnectionManager;

public class ConnectHelper {
	public static final ResourceBundle rb=IConnectionManager.rb;
	
	public static ThreadLocal connThread=new ThreadLocal();
	public static Connection getConn(){
		return ConnectionFactory.getConnManage().getConnection();
	}
	public static Connection getConnByThreadLocal(){
		Connection conn=(Connection) connThread.get();
		if(conn==null){
			conn=getConn();
			connThread.set(conn);
		}
		return conn;
	}
}
