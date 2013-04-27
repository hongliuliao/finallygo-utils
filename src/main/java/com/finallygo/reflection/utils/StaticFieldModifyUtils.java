package com.finallygo.reflection.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.finallygo.common.utils.CommonUtils;
import com.finallygo.reflection.pojo.utils.MethodValues;

public class StaticFieldModifyUtils {
	public static boolean modifyField(String className,String name,String value){
		try {
			Class clazz=Class.forName(className);
			Field field=clazz.getDeclaredField(name);
			Object objValue=CommonUtils.getValueFromString(value, field.getType());
			field.set(clazz, objValue);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 分析输入的字符串
	 * 我现在是这么预期这个字符串的:"类名;属性名+[方法]+设置值的方法",各个字段用"."隔开
	 * @param inputStr 
	 * @return map
	 */
	private static Map analyseInputString(String inputStr){
		Map map=new HashMap();
		
		String className=inputStr.split(";")[0];
		String[] inputStrs=inputStr.split(";")[1].split("\\.");
		String fieldName=inputStrs[0];
		//
//		String lastStr=inputStrs[inputStrs.length-1];//得到最后一个输入的字符串
		List methodValuesList=new ArrayList(); 
		for(int i=1;i<inputStrs.length;i++){
			MethodValues methodValues=new MethodValues();
			String str=inputStrs[i];
			String method=str.substring(0, str.indexOf("("));
			String[] values=str.substring(str.indexOf("(")+1,str.indexOf(")")).split(",");
			methodValues.setMethod(method);
			methodValues.setValues(values);
			methodValuesList.add(methodValues);
		}
		map.put("className", className);
		map.put("fieldName", fieldName);
		map.put("methodValuesList", methodValuesList);
		return map;
	} 
	private static boolean explainInputStr(Map map){
		try {
			String className=(String) map.get("className");
			String fieldName=(String) map.get("fieldName");
			List list=(List) map.get("methodValuesList");
			Class clazz=Class.forName(className);
			Field field=clazz.getDeclaredField(fieldName);
			Object obj=field.get(clazz);
			
			int i=0;
			forExecMehod(obj,list,i);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean execStaticJavaCode(String javaCode){
		Map map=analyseInputString(javaCode);
		return explainInputStr(map);
	}
	private static void forExecMehod(Object obj,List list,int i){
		if(i<list.size()){
			Object oo=executeMethod(obj, (MethodValues) list.get(i));
			i++;
			forExecMehod(oo,list,i);
		}
	}
	private static Object executeMethod(Object obj,MethodValues methodValues){
		try {
			Class[] parameterTypes=getClassArray(methodValues);
			Object[] objs=getObjectArray(methodValues, parameterTypes);
//			Method method=obj.getClass().getDeclaredMethod(methodValues.getMethod(), parameterTypes);
			Method method=getMethod(obj.getClass(), methodValues.getMethod(), parameterTypes);
			return method.invoke(obj, objs);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	private static Method getMethod(Class clazz,String name,Class[] parameterTypes){
		try {
			try {
				return clazz.getDeclaredMethod(name, parameterTypes);
			} catch (Exception e) {
				Class[] classes=new Class[parameterTypes.length];
				for(int i=0;i<classes.length;i++){
					classes[i]=Object.class;
				}
				return clazz.getDeclaredMethod(name, classes);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	private static Class[] getClassArray(MethodValues methodValues){
		String[] values=methodValues.getValues();
		Class[] classes=new Class[values.length];
		for(int i=0;i<values.length;i++){
			if(values[i].indexOf("\"")!=-1){
				classes[i]=String.class;
			}else{
				classes[i]=Integer.class;
			}
		}
		return classes;
	}
	private static Object[] getObjectArray(MethodValues methodValues,Class[] classes){
		String[] values=methodValues.getValues();
		Object[] objs=new Object[values.length];
		for (int i = 0; i < values.length; i++) {
			objs[i]=CommonUtils.getValueFromString(values[i].replaceAll("\"", ""), classes[i]);
		}
		return objs;
	}

}
