package com.finallygo.test.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.finallygo.build.ext.utils.BuildExtGrid;
import com.finallygo.common.utils.CommonUtils;
import com.finallygo.db.utils.ETLTools;
import com.finallygo.test.pojo.AClrCoAllResultRateId;
import com.finallygo.test.pojo.User;

public class MainTest {
	public static String testField="aaa";
	public static Map map=new HashMap();
	static{
		map.put("test",new AClrCoAllResultRateId());
	}
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void testEtl() throws Exception{
		Connection fromConn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","liaohl","liaohl");
		Connection toConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","123456");
		ETLTools tools=new ETLTools(fromConn,toConn,true);
		tools.doEtl("insert into count_info values(?,?,?,?,?,?,?,?,?,?,?,?)", " select * from count_info t ");
	}
	public static void testBuildExtGrid(){
		BuildExtGrid builder=new BuildExtGrid(User.class,"queryList");
//		BuildExtGrid builder=(BuildExtGrid) MethodTimeProxy.createProxy(BuildExtGrid.class, new Class[]{Class.class,String.class}, new Object[]{User.class,"queryList"});
		String str=builder.createGrid();
		System.out.println(str);
	}
//	public static void testExecStaticJavaCode(){
//		String javaCode="com.finallygo.test.main.MainTest;map.get(\"test\").setJobNo(\"gho\")";
//		StaticFieldModifyUtils.execStaticJavaCode(javaCode);
////		StaticFieldModifyUtils.modifyField("com.finallygo.test.main.MainTest", "testField", "bgh");
//		System.out.println(MainTest.map.get("test"));
//	}
	
	
	public static void testList(){
		List list=new ArrayList();
		list.add("aaa");
		list.add("bbb");
		List list3=new ArrayList();
		list3.addAll(list);
//		List list2=list;
		boolean b=list3.remove("vv");
		System.out.println(b);
	}
	public static void testCopyBean(){
		User user1=new User();
		user1.setUserId(new Integer(1));
		user1.setUserName("tom");
		User user2=new User();
//		user2.setUserId(new Integer(2));
		user2.setUserName("jerry");
		user2.setAge(new Integer(23));
		try {
//			PropertyUtils.copyProperties(user2, user1);
			CommonUtils.copyProperties(user2, user1);
			System.out.println(user2.getAge());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		testBuildExtGrid();
//		testEtl();
//		testExecStaticJavaCode();
//		testTransaction();
//		testList();
//		testCopyBean();
	}
}
