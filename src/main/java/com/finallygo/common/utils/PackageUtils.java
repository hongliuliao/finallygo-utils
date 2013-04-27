package com.finallygo.common.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PackageUtils {
	/**
	 * 根据包名找到该包名下的所有类
	 * @param packageName 包名
	 * @return 指定包名下的所有类
	 */
	public static Class[] getAllClass(String packageName){
		
		List classes = new ArrayList();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace('.', '/');//将包名中的"."替换为文件路径分隔符"/"
		
		URL resource = classLoader.getResource(path);//根据路径找到相应的URL对象
		if(resource==null){
			throw new RuntimeException("找不到包名为:"+packageName+"的包");
		}
		try {
			String classPath=resource.toURI().getPath();
			File directory = new File(classPath); 
			File[] files=directory.listFiles();//得到该文件夹下的所有文件
			for(int i=0;i<files.length;i++){
				if (files[i].getName().endsWith(".class")) { 
					Class clazz=Class.forName(packageName + '.' + files[i].getName().substring(0, files[i].getName().length() - 6));
					classes.add(clazz); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Class[] classesA = new Class[classes.size()]; 
		classes.toArray(classesA); 
		return classesA; 
	}
}
