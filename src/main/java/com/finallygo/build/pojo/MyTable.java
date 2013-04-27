/**
 * 
 */
package com.finallygo.build.pojo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.finallygo.common.utils.CommonUtils;

/**
 * <pre>
 * Title:功能描述
 * Description:功能描述
 * </pre>
 * @author liaohongliu liaohl@yuchengtech.com
 * @version 1.0   2009-7-29
 * 
 * <pre>
 * 修改记录
 * 	  修改后版本:      修改人:     修改时间:       修改内容
 * </pre>
 */
public class MyTable {
	private String tableId;//表的编号,用于导入和导出时的校验
	private String tableChineseName;//表的中文名
	private List relationTables;//与之关联的数据库中的表
	private String[] tableCols;//表机构
	private String findMethod;//数据来源
	private String saveMethod;//保存方法
	private String deleteMethod;//删除方法
	private String importMethod;//导入方法
	private String exportMethod;//导出方法
	private String formWidth;//表单宽度
	private String formHeight;//表单高度
	private Class clazz;//对应的java类
	private String pageName;//页面路径
	private String[] excelCols;
	private Map webMap;
	private String subjectType;//专门为管理参数表设置
	private Class readExcelClass;//专门为杨东写的表设置读写类
	private String issueTableName;//发布表表名  
	private String bakTableName;//备份表表名
	private String rmbTableId;//因为有些表区分本外币所以添加此属性
	private String foreTableId;//同上
	private int exportColNumber;//导出的excel列数
	private String historyTableName;//数据库中对应历史表名
	private String tableName;//表名
	private MyField[] fields;//表对应的字段
	private String insertSql;//插入一条记录的sql
	private String updateSql;//以主键为更新条件的sql
	private String deleteSql;//以主键为删除条件的sql
	private String selectSql;//以主键为条件的查询sql

	public String getSelectSql() {
		return selectSql;
	}
	public void setSelectSql(String selectSql) {
		this.selectSql = selectSql;
	}
	public MyField[] getFields() {
		return fields;
	}
	public void setFields(MyField[] fields) {
		this.fields = fields;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getHistoryTableName() {
		return historyTableName;
	}
	public void setHistoryTableName(String historyTableName) {
		this.historyTableName = historyTableName;
	}
	public String getForeTableId() {
		return foreTableId;
	}
	public int getExportColNumber() {
		return exportColNumber;
	}
	public MyTable setExportColNumber(int exportColNumber) {
		this.exportColNumber = exportColNumber;
		return this;
	}
	public MyTable setForeTableId(String foreTableId) {
		this.foreTableId = foreTableId;
		return this;
	}
	public String getRmbTableId() {
		return rmbTableId;
	}
	public MyTable setRmbTableId(String rmbTableId) {
		this.rmbTableId = rmbTableId;
		return this;
	}
	public String getBakTableName() {
		return bakTableName;
	}
	public MyTable setBakTableName(String bakTableName) {
		this.bakTableName = bakTableName;
		return this;
	}
	public String getIssueTableName() {
		return issueTableName;
	}
	public MyTable setIssueTableName(String issueTableName) {
		this.issueTableName = issueTableName;
		return this;
	}
	public Class getReadExcelClass() {
		return readExcelClass;
	}
	public MyTable setReadExcelClass(Class readExcelClass) {
		this.readExcelClass = readExcelClass;
		return this;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public MyTable setSubjectType(String subjectType) {
		this.subjectType = subjectType;
		return this;
	}
	public MyTable() {
		// TODO 自动生成构造函数存根
	}
	public MyTable(Class clazz, String tableChineseName, String[] excelCols,Map webMap) {
		super();
		this.clazz=clazz;
		this.tableChineseName = tableChineseName;
		this.excelCols=excelCols;
		this.webMap=webMap;
	}
	public MyTable(String tableId,Class clazz, String tableChineseName, String[] tableCols,String formWidth,String formHeight) {
		super();
		this.tableId = tableId;
		this.clazz=clazz;
		this.tableChineseName = tableChineseName;
		this.tableCols = tableCols;
		this.formWidth=formWidth;
		this.formHeight=formHeight;
	}
	public MyTable(String tableId,Class clazz, String tableChineseName, String[] tableCols,String formWidth,String formHeight,String pageName) {
		super();
		this.tableId = tableId;
		this.clazz=clazz;
		this.tableChineseName = tableChineseName;
		this.tableCols = tableCols;
		this.formWidth=formWidth;
		this.formHeight=formHeight;
		this.pageName=pageName;
	}
	public MyTable( String tableChineseName, String[] tableCols) {
		super();
//		this.tableId = tableId;
		this.tableChineseName = tableChineseName;
		this.tableCols = tableCols;
	}
	public String[] getFieldNames() {
		Map webMap=this.getWebMap();
		String[] fieldNames=new String[webMap.keySet().size()];
		int num=0;
		for(Iterator i=webMap.keySet().iterator();i.hasNext();){
			WebField webField=(WebField) webMap.get(i.next());
			String name=webField.getFieldName();
			fieldNames[num]=name;
			num++;
		}
		return fieldNames;
	}
	public String[] getExcelCols() {
		return excelCols;
	}
	public String[] getExcelColNames(){
		Map map=this.getWebMap();
		String[] names=new String[excelCols.length];
		for(int i=0;i<excelCols.length;i++){
			WebField webField=(WebField) map.get(excelCols[i]);
			names[i]=webField.getFieldName();
		}
		return names;
	}
	public void setExcelCols(String[] excelCols) {
		this.excelCols = excelCols;
	}
	public void setTableCols(String[] tableCols) {
		this.tableCols = tableCols;
	}
	public MyTable(String tableId, String tableChineseName) {
		super();
//		this.tableId = tableId;
		this.tableChineseName = tableChineseName;
	}
	public List getRelationTables() {
		return relationTables;
	}
	public void setRelationTables(List relationTables) {
		this.relationTables = relationTables;
	}
	public String getTableChineseName() {
		return tableChineseName;
	}
	public MyTable setTableChineseName(String tableChineseName) {
		this.tableChineseName = tableChineseName;
		return this;
	}
	public String getTableId() {
		return tableId;
	}
	public MyTable setTableId(String tableId) {
		this.tableId = tableId;
		return this;
	}
	public String getFindMethod() {
		return findMethod;
	}
	public MyTable setFindMethod(String findMethod) {
		this.findMethod = findMethod;
		return this;
	}
	public String getDeleteMethod() {
		return deleteMethod;
	}
	public MyTable setDeleteMethod(String deleteMethod) {
		this.deleteMethod = deleteMethod;
		return this;
	}
	public String getFormHeight() {
		return formHeight;
	}
	public MyTable setFormHeight(String formHeight) {
		this.formHeight = formHeight;
		return this;
	}
	public String getFormWidth() {
		return formWidth;
	}
	public void setFormWidth(String formWidth) {
		this.formWidth = formWidth;
	}
	public String getSaveMethod() {
		return saveMethod;
	}
	public MyTable setSaveMethod(String saveMethod) {
		this.saveMethod = saveMethod;
		return this;
	}
	public String getDwrJsName(){
		String name= this.getFindMethod();
		if(name.indexOf(".")!=-1){
			name=name.substring(0,name.indexOf("."));
		}
		return name;
	}
	public String getExportMethod() {
		return exportMethod;
	}
	public Class getClazz() {
		return clazz;
	}
	public MyTable setClazz(Class clazz) {
		this.clazz = clazz;
		return this;
	}
	public void setExportMethod(String exportMethod) {
		this.exportMethod = exportMethod;
	}
	public String getImportMethod() {
		return importMethod;
	}
	public void setImportMethod(String importMethod) {
		this.importMethod = importMethod;
	}
	public String getBBName(){
		String name=this.getFindMethod();
		if(name.indexOf(".")!=-1){
			name=name.substring(1,name.indexOf("."));
		}
		return name;
	}
	public String getClassName(){
		return clazz.getName();
	}
	public String getClassSimpleName(){
		return CommonUtils.getSimpleName(clazz.getName());
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public Map getWebMap() {
		return webMap;
	}
	public void setWebMap(Map webMap) {
		this.webMap = webMap;
	}
	public WebField getWebFieldByFieldName(String fieldName){
		Collection set=webMap.values();
		Iterator i=set.iterator();
		while(i.hasNext()){
			WebField webField=(WebField) i.next();
			if(webField.getFieldName().equals(fieldName)){
				return webField;
			}
		}
		return null;
	}
	public Collection getWebFields(){
		return webMap.values();
	}
	public String getDbTableName() {
		String className=this.getClassSimpleName();
		StringBuffer name=new StringBuffer();
		for(int i=0;i<className.length();i++){
			char c1=className.charAt(i);
			if(Character.isUpperCase(c1)){
				StringBuffer sb=new StringBuffer();
				sb.append(c1);
				for(int j=i+1;j<className.length();j++){
					char c2=className.charAt(j);
					if(Character.isUpperCase(c2)){
						break;
					}
					sb.append(c2);
				}
				name.append(sb+"_");
			}
		}
		
		return name.toString().substring(0,name.length()-1).toUpperCase();
	}
	public String[] getTableCols() {
		return tableCols;
	}
	public String getDeleteSql() {
		return deleteSql;
	}
	public void setDeleteSql(String deleteSql) {
		this.deleteSql = deleteSql;
	}
	public String getInsertSql() {
		return insertSql;
	}
	public void setInsertSql(String insertSql) {
		this.insertSql = insertSql;
	}
	public String getUpdateSql() {
		return updateSql;
	}
	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}
}
