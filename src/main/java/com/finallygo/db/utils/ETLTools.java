/**
 * 
 */
package com.finallygo.db.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author user
 *
 */
public class ETLTools {
	
	private Connection fromConn;
	private Connection toConn;
	private boolean connAutoClose=false;
	private int batchSize=10000;
	
	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public boolean isConnAutoClose() {
		return connAutoClose;
	}

	public void setConnAutoClose(boolean connAutoClose) {
		this.connAutoClose = connAutoClose;
	}

	public ETLTools(Connection fromConn, Connection toConn,boolean connAutoClose) {
		super();
		this.fromConn = fromConn;
		this.toConn = toConn;
		this.connAutoClose=connAutoClose;
	}

	public void doEtl(String insertSql,String selectSql){
		PreparedStatement fromPs=null,toPs=null;
		try {
			fromPs=fromConn.prepareStatement(selectSql);
			toPs=toConn.prepareStatement(insertSql);
			
			ResultSet rs=fromPs.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int number=0;
			while(rs.next()){
				for(int i=0;i<rsmd.getColumnCount();i++){
					Object value=rs.getObject(i+1);
					if(value==null){
						toPs.setNull(i+1, java.sql.Types.NULL);
						continue;
					}
					toPs.setObject(i+1, value);
				}
				toPs.addBatch();
				number++;
				if(number>this.getBatchSize()){
					toPs.executeBatch();
					number=0;
					continue;
				}
			}
			toPs.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(connAutoClose){
					if(fromPs!=null){
						fromPs.close();
					}
					if(toPs!=null){
						toPs.close();
					}
					if(fromConn!=null){
						fromConn.close();
					}
					if(toConn!=null){
						toConn.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public boolean closeConn(Connection fromConn,Connection toConn){
		try {
			if(fromConn!=null){
				fromConn.close();
			}
			if(toConn!=null){
				toConn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
