package com.finallygo.jdbcTemplate.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JdbcTemplateUtils {
	public static List getJdbcTemplateListFirstCol(List list){
		List returnList=new ArrayList();
		String colName="";
		if(list.size()>0){
			Map map=(Map) list.get(0);
			Iterator i=map.keySet().iterator();
			if(i.hasNext()){
				colName=(String) i.next();
			}
		}
		for(int i=0;i<list.size();i++){
			Map map=(Map) list.get(i);
			returnList.add(map.get(colName));
		}
		return returnList;
	}
}
