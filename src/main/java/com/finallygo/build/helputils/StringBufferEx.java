package com.finallygo.build.helputils;

public class StringBufferEx {
	private StringBuffer sb=new StringBuffer();
	public StringBufferEx() {
		// TODO 自动生成构造函数存根
	}
	public StringBuffer appendLn(String str){
		return sb.append(str+"\n");
	}
	public String toString() {
		return sb.toString();
	}
}
