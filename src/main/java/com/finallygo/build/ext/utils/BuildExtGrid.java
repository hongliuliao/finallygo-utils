/**
 * 
 */
package com.finallygo.build.ext.utils;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


import com.finallygo.build.helputils.StringBufferEx;
import com.finallygo.build.pojo.MyTable;
import com.finallygo.build.pojo.WebField;
import com.finallygo.common.utils.CommonUtils;


/**
 * <pre>
 * Title:生成EXT表单的js
 * Description:功能描述
 * </pre>
 * @author liaohongliu liaohl@yuchengtech.com
 * @version 1.0   Jul 27, 2009
 * 
 * <pre>
 * 修改记录
 * 	  修改后版本:      修改人:     修改时间:       修改内容
 * </pre>
 */
public class BuildExtGrid {
	private MyTable table;
	private String divId;
	private StringBuffer sb=new StringBuffer();

	public BuildExtGrid(Class clazz,String divId) {
		this.table=this.buildMytableFromPojo(clazz);
		this.table.setClazz(clazz);
		this.divId=divId;
	}

	public void init(){
//		fieldMap=CommonUtils.get(clazz, fieldMap);
//		fields=getFieldsByString(namesString);
	}
	/**
	 * 生成表的列模型
	 * @param names 属性名
	 */
	public void buildColumnModel(String[] names){
		try {
//			Class clazz=Class.forName(className);
			
			sb.append("var "+table.getClassSimpleName()+"Cm  = new Ext.grid.ColumnModel([\n");
			sb.append("	new Ext.grid.CheckboxSelectionModel({singleSelect: true}),\n");
			
			for(int i=0;i<names.length;i++){
				WebField webField=(WebField) table.getWebMap().get(names[i]);
				String simpleName=CommonUtils.getSimpleName(names[i]);
				if(webField.getWebType().equals("datefield")){
					if(webField.getFieldSimpleName().equals("updateDt")){
						sb.append("	{header: '"+simpleName+"',  sortable: true,renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s'), dataIndex: '"+webField.getFieldSimpleName()+"'}");
					}else{
						sb.append("	{header: '"+simpleName+"',  sortable: true,renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: '"+webField.getFieldSimpleName()+"'}");
					}
				}else if(webField.getRenderer()!=null){
					sb.append("	{header: '"+simpleName+"',  sortable: true, dataIndex: '"+webField.getFieldSimpleName()+"',renderer:"+webField.getRenderer()+"}");
				}else{
					sb.append("	{header: '"+simpleName+"',  sortable: true, dataIndex: '"+webField.getFieldSimpleName()+"'}");
				}
				
				if(i!=names.length-1){
					sb.append(",\n");
				}else{
					sb.append("\n\n");
				}
			}
			sb.append("]);\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建记录类型
	 * @param names 属性名
	 */
	public void buildRecordType(String[] names){
		try {
			sb.append("var "+table.getClassSimpleName()+"RecordType = Ext.data.Record.create([\n");
			
			for(int i=0;i<names.length;i++){
				WebField webField=(WebField) table.getWebMap().get(names[i]);
				sb.append("	{name: '"+webField.getFieldSimpleName()+"'}");
				if(i!=names.length-1){
					sb.append(",\n");
				}else{
					sb.append("\n\n");
				}
			}
			sb.append(" ]);\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建数据集
	 * @param findMethod
	 */
	public void buildDataSource(String findMethod){
		sb.append("var "+table.getClassSimpleName()+"Ds=new Ext.data.Store({\n");
		sb.append("	proxy: new Ext.data.DWRProxy(J"+table.getClassSimpleName()+".getList, true),\n");
		sb.append("	reader: new Ext.data.ListRangeReader({\n");
		sb.append("		totalProperty: 'totalSize'\n");
		sb.append("	}, "+table.getClassSimpleName()+"RecordType)\n");
		sb.append("});\n");
	}
	/**
	 * 创建表格
	 * @param tableId 表id
	 */
	public void buildGrid(){
		//sb.append("grid=new MyGrid('queryList',cm,ds,tbar);\n");
		StringBufferEx sbe=new StringBufferEx();
		sbe.appendLn("var "+table.getClassSimpleName()+"CenterDiv=Ext.get('"+divId+"');");
		sbe.appendLn("var "+table.getClassSimpleName()+"Grid=new Ext.grid.GridPanel({");
		sbe.appendLn("	renderTo: '"+divId+"',");
		sbe.appendLn("	ds: "+table.getClassSimpleName()+"Ds,");
		sbe.appendLn("	cm: "+table.getClassSimpleName()+"Cm,");
		sbe.appendLn("	sm:new Ext.grid.CheckboxSelectionModel({singleSelect:true }),");
		sbe.appendLn("	height:"+table.getClassSimpleName()+"CenterDiv.getComputedHeight(),");
		sbe.appendLn("	width:"+table.getClassSimpleName()+"CenterDiv.getComputedWidth(),");
		sbe.appendLn("	frame:true,");
		sbe.appendLn("	collapsible:true,");
		sbe.appendLn("	bbar:new Ext.PagingToolbar({");
		sbe.appendLn("		pageSize: 50,");
		sbe.appendLn("		store: "+table.getClassSimpleName()+"Ds,");
		sbe.appendLn("		displayInfo: true,");
		sbe.appendLn("		displayMsg: '显示第 {0} 条到 {1} 条记录，一共 {2} 条',");
		sbe.appendLn("		emptyMsg: '没有记录'");
		sbe.appendLn("	})");
		sbe.appendLn("});");
		sb.append(sbe);
		sb.append(table.getClassSimpleName()+"Grid.render();\n");
//		sb.append("grid.addListener('rowdblclick', doEdit, this);\n");
		sb.append(table.getClassSimpleName()+"Ds.on('beforeload', function() {");
		sb.append(table.getClassSimpleName()+"Ds.baseParams = {className:'"+table.getClassName()+"'};});\n");
		sb.append(table.getClassSimpleName()+"Ds.load({params:{start:0,limit:50,className:'"+table.getClassName()+"'}});");
	}
	/**
	 * 创建表格
	 * @param tableId 表id 
	 * @return 生成的js
	 */
	public String createGrid(){
		String[] names=table.getFieldNames();
		buildColumnModel(names);
		buildRecordType(names);
		buildDataSource(table.getFindMethod());
		buildGrid();
//		log.info("创建表格结束");
		return sb.toString();
	}
	public MyTable buildMytableFromPojo(Class clazz){
		MyTable table=new MyTable();
		Map webMap=new LinkedHashMap();
		Map fieldMap=CommonUtils.getFieldNameMap(clazz);
		for(Iterator i=fieldMap.keySet().iterator();i.hasNext();){
			String fieldName=(String) i.next();
			Class fieldType=(Class) fieldMap.get(fieldName);
			WebField webField=new WebField();
			webField.setFieldName(fieldName);
			webField.setFieldType(fieldType);
			if(fieldType!=java.util.Date.class){
				webField.setWebType("textfield");
			}else{
				webField.setWebType("datefield");
			}
			
			webMap.put(fieldName, webField);
		}
		table.setWebMap(webMap);
		return table;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("中".getBytes("utf-8").length);
		System.out.println("中".getBytes("gbk").length);
		System.out.println("中".getBytes("iso8859-1").length);
	}

}
