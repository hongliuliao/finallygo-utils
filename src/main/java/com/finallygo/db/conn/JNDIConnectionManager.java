package com.finallygo.db.conn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 * 提供JNDI数据源的读取
 * 
 * @author sean
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JNDIConnectionManager implements IConnectionManager {
	private static DataSource ds;
	static{
		String dsName=rb.getString("dsName");
		try {
			Context context=new InitialContext();
			ds=(DataSource)context.lookup(dsName);
		} catch (NamingException e) {
			throw new RuntimeException("寻找名为"+dsName+"的数据源出现异常!",e);
		}
	}
	public synchronized Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("从数据源中获取连接出现异常!",e);
		}
	}
}
