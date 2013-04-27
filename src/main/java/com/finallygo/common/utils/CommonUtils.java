/**
 * 
 */
package com.finallygo.common.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;



/**
 * @author user
 *
 */
public class CommonUtils {

	public static List constType=new ArrayList();
	static{
		constType.add(Long.class);
		constType.add(Integer.class);
		constType.add(Double.class);
		constType.add(Float.class);
		constType.add(java.util.Date.class);
		constType.add(java.sql.Date.class);
		constType.add(String.class);
		constType.add(Boolean.class);
		constType.add(Timestamp.class);
		constType.add(BigDecimal.class);
		constType.add(long.class);
		constType.add(int.class);
		constType.add(double.class);
		constType.add(boolean.class);
		constType.add(float.class);
	}
	public static void setProperty(Object obj,String name,Object value){
		try {
			BeanUtils.setProperty(obj, name, value);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static String getPropertyString(Object obj,String name){
		try {
			return BeanUtils.getProperty(obj, name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	public static Object getProperty(Object obj,String name){
		try {
			return PropertyUtils.getProperty(obj, name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	/**
	 * 通过反射创建对象,目前只支持深度为2级的操作,也就是说如果对象中还有对象也会被创建不过只支持深度为2级的
	 * @param clazz 要得到的对象的类型
	 * @return 创建好的对象
	 */
	public static Object getNewObject(Class clazz){
		try {
			Object obj=clazz.newInstance();
			Field[] fields=clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				if(!constType.contains(fields[i].getType())){
					Object subObj=fields[i].getType().newInstance();
					CommonUtils.setProperty(obj, fields[i].getName(), subObj);
				}
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object convert(Object obj,Class type){
		return ConvertUtils.convert(obj, type);
	}
	/**
	 * 将map中的值填充到对象中
	 * @param map 此map为dwr将查询条件(json格式)转换成的map
	 * @param clazz 要填充的对象的类
	 * @return 填充好的对象
	 */
	public static Object map2ObjectForQuery(Map map,Class clazz){
		Object obj=CommonUtils.getNewObject(clazz);
		Map fieldNameMap=getFieldNameMap(clazz);
		Iterator i=fieldNameMap.keySet().iterator();
		while(i.hasNext()){
			String fieldName= i.next().toString();
			Object objValue=map.get(getSimpleName(fieldName));
			if(objValue==null){
				continue;
			}
			String value= objValue.toString();
			Class type=(Class) fieldNameMap.get(fieldName);
			CommonUtils.setProperty(obj, fieldName, getValueFromString(value, type));
		}
		return obj;
	}
	/**
	 * 将map中的值填充到对象中
	 * @param map 此map为HttpRequest中的ParameterMap
	 * @param clazz 要填充的对象的类
	 * @return 填充好的对象
	 */
	public static Object map2ObjectForRequest(Map map,Class clazz){
		Object obj=CommonUtils.getNewObject(clazz);
		Map fieldNameMap=getFieldNameMap(clazz);
		Iterator i=fieldNameMap.keySet().iterator();
		while(i.hasNext()){
			String fieldName=(String) i.next();
			String[] values=((String[]) map.get(getSimpleName(fieldName)));
			if(values!=null){
				Class type= (Class) fieldNameMap.get(fieldName);
				if(CommonUtils.isValidString(values[0])){
					CommonUtils.setProperty(obj, fieldName, getValueFromString(values[0], type));	
				}
			}
		}
		return obj;
	}
	/**
	 * 将Strng类型的值转换为相应的类型
	 * @param value String类型的值
	 * @param toType 转换的类型
	 * @return 转换类型后的值
	 */
	public static Object getValueFromString(String value,Class toType){
		if(toType==Double.class){
			return new Double(value);
		}else if(toType==Integer.class){
			return new Integer(value);
		}else if(toType==Long.class){
			return new Long(value);
		}else if(toType==java.util.Date.class){
			return java.sql.Date.valueOf(value);
		}else{
			return value;
		}
	}
	/**
	 * 判断字符串的有效性
	 * @param str 要判断的字符串
	 * @return 是否有效,true:有效
	 */
	public static boolean isValidString(String str){
		return str!=null&&str.trim().length()>0;
	}
	/**
	 * 得到字段名集合
	 * @param clazz 要得到的对象所属的类
	 * @return 属性名集合,比如id.name,address等属性名
	 */
	public static List getFieldNameList(Class clazz){
		return forFieldNameList(clazz, "");
	}
	/**
	 * 为了递归的得到属性列表而写的方法
	 * @param clazz 要得到的对象所属的类
	 * @param pre 要添加的前缀
	 * @return 属性名集合,比如id.name,address等属性名
	 */
	private static List forFieldNameList(Class clazz,String pre){
		List fieldList=new ArrayList();
		Field[] fields=clazz.getDeclaredFields();
		String prex="";
		if(isValidString(pre)){
			prex=pre+".";
		}
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if(constType.contains(field.getType())){
				fieldList.add(prex+field.getName());
			}else{
				Class cla=field.getType();
				fieldList.addAll(forFieldNameList(cla,field.getName()));
			}
		}
		return fieldList;
	}
	/**
	 * 得到类的属性集合
	 * @param clazz 指定的类
	 * @return map中的key为属性名（如果有多级用"."隔开），value为属性类型
	 */
	public static Map getFieldNameMap(Class clazz){
		return forFieldNameMap(clazz, "");
	}
	private static Map forFieldNameMap(Class clazz,String pre){
		Map fieldNameMap=new LinkedHashMap();
		Field[] fields=clazz.getDeclaredFields();
		String prex="";
		if(isValidString(pre)){
			prex=pre+".";
		}
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if(constType.contains(field.getType())){
				fieldNameMap.put(prex+field.getName(),field.getType());
			}else{
				Class cla=field.getType();
				fieldNameMap.putAll(forFieldNameMap(cla,field.getName()));
			}
		}
		return fieldNameMap;
	}
	/**
	 * 得到多个小数点格式的字符串的最后一个
	 * @param name 字符串
	 * @return 截取后的字符串
	 */
	public static String getSimpleName(String name){
		if(name.lastIndexOf(".")!=-1){
			String temp=name.substring(name.lastIndexOf(".")+1);
			return temp;
		}else{
			return name;
		}
	}
	/**
	 * 得到多个小数点格式的字符串的最开始一个
	 * @param name 字符串
	 * @return 截取后的字符串
	 */
	public static String getFirstName(String name){
		if(name.indexOf(".")!=-1){
			String temp=name.substring(0,name.indexOf("."));
			return temp;
		}else{
			return name;
		}
	}
	/**
	 * 将查询条件封装的对象转换为map
	 * @param obj
	 * @return map的key为属性名,value为属性值
	 */
	public static Map Object2Map(Object obj){
		return getFieldValueEx(obj,new LinkedHashMap());
	}
	/**
	 * 递归的获得属性对应的值(不同的是层次信息丢失,而且对象不会作为map的主键)
	 * @param obj 要获得值的对象
	 * @param valuesMap 为了递归的实现,建议使用new HashMap();
	 * @return map中key为属性名,value为值
	 */
	private static Map getFieldValueEx(Object obj,Map valuesMap){
		Class clazz=obj.getClass();
		try {
			Field[] fields=clazz.getDeclaredFields();
			for(int i=0;i<fields.length;i++){
				if(constType.contains(fields[i].getType())){
					Object temp=CommonUtils.getPropertyString(obj, fields[i].getName());
					if(temp!=null){
						valuesMap.put(fields[i].getName(), temp);
					}
				}else{
					Object oo=CommonUtils.getPropertyString(obj, fields[i].getName());
					if(oo!=null){
						getFieldValueEx(oo, valuesMap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valuesMap;
	}
	/**
	 * 根据字段名来获得字段,支持两级
	 * @param clazz 指定要获得字段的类
	 * @param name 字段名,多级用点号隔开
	 * @return 找到的字段,如果没有就为空
	 */
	public static Field getFieldByString(Class clazz,String name){
		try {
			if(name.indexOf(".")==-1){
				return clazz.getDeclaredField(name);
			}
			Field field=clazz.getDeclaredField(name.substring(0,name.indexOf(".")));
			Class cla=field.getType();
			return cla.getDeclaredField(name.substring(name.indexOf(".")+1));
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 让首字母大写
	 * @param str 要大写的单词
	 * @return 首字母大写后的单词
	 */
	public static String getFirstUpper(String str){
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}
	/**
	 * 对象类型一样的时候可以复制值,主要用在对象执行更新方法之前,和
	 * PropertyUtils的方法不一样的地方是如果fromObj的属性是空的就不赋值
	 * @param fromObj 一般是从页面传过来需要修改的数据封装的对象
	 * @param toObj 一般是从数据库中查询出来的对象
	 */
	public static void copyProperties(Object fromObj,Object toObj){
		List fieldNameList=CommonUtils.getFieldNameList(fromObj.getClass());
		for(int i=0;i<fieldNameList.size();i++){
			String fieldName=(String) fieldNameList.get(i);
			Object value=CommonUtils.getProperty(fromObj, fieldName);
			if(value!=null){//如果值不为空,就赋到另一个对象上
				CommonUtils.setProperty(toObj, fieldName, value);
			}
		}
	}
}
