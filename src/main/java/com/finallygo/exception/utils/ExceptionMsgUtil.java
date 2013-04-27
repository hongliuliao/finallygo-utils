package com.finallygo.exception.utils;

public class ExceptionMsgUtil {
	public static StringBuffer getStackTraceMessage(Exception e){
		StringBuffer exceptMsg=new StringBuffer();
		forGetStackTrace(e,exceptMsg);
		return exceptMsg;
	}
	public static void forGetStackTrace(Throwable e,StringBuffer exceptMsg){
		StackTraceElement[] trace = e.getStackTrace();
		for (int i=0; i < trace.length; i++){
			exceptMsg.append(trace[i].getClassName()+"的"+trace[i].getMethodName()+"方法发生异常!(行数为:"+trace[i].getLineNumber()+")\r\n");
		}
		exceptMsg.append("\t异常信息为:"+e.getMessage()+"\r\n");
		Throwable ourCause = e.getCause();
		if(ourCause!=null){
			forGetStackTrace(ourCause,exceptMsg);
		}
	}
}
