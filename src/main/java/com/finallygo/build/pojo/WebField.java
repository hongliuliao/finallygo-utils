/*
 * FileName:WebField.java, v1.0 Jun 19, 2009 12:21:30 PM created by finallygo
 *
 * Copyright (c) 2009 Wance 
 * All Rights Reserved.
 * Confidential and for internal user only.
 */

package com.finallygo.build.pojo;

import com.finallygo.common.utils.CommonUtils;


/**
 * TODO描述类的功能
 *
 * @author finallygo
 * @version $Revision: 1.5 $
 * @since 1.0
 */
public class WebField extends MyField {
	private String webType;
	private boolean isKey;
	private boolean isQueryCondition;
	private String store;//当设置类型为combobox的时候,需要设置数据源来源
	private String queryType;
	private String simpleName;
	private String renderer;//页面上需要用到转换器,一般用于字段在页面显示是combobox时
	private String chineseName;
	private String regExp;
	public final static String LIKE="0";
	public final static String EQUAL="2";
	public String validator;//网页上的校验器
//	private Method techMethod;//为了保证数据能正常导入数据库的校验方法(集中在CommonUtils.java)
	private String columnWidth;//
	private int gridColumnWidth;//表格的列宽

	public int getGridColumnWidth() {
		return gridColumnWidth;
	}
	public WebField setGridColumnWidth(int gridColumnWidth) {
		this.gridColumnWidth = gridColumnWidth;
		return this;
	}
	public String getColumnWidth() {
		return columnWidth;
	}
	public WebField setColumnWidth(String columnWidth) {
		this.columnWidth = columnWidth;
		return this;
	}
	public WebField() {
		// TODO Auto-generated constructor stub
	}
	public WebField(String chineseName,String fieldName,String webType,boolean isKey,boolean isQueryConditon){
		this.chineseName=chineseName;
		this.fieldName=fieldName;
		this.webType=webType;
		this.isKey=isKey;
		this.isQueryCondition=isQueryConditon;
	}
	/**
	 * webField的构造函数(终结版?)
	 * @param chineseName 中文名
	 * @param fieldName 字段名(带层次)
	 * @param webType 在页面上的类型,已支持textfield,combobox,datefield,textarea
	 * @param isKey 是否主键
	 * @param isQueryConditon 是否作为查询条件
	 * @param regExp 在导入Excel时,做的数据有效性校验的正则表达式
	 */
	public WebField(String chineseName,String fieldName,String webType,boolean isKey,boolean isQueryConditon,String regExp){
		this.chineseName=chineseName;
		this.fieldName=fieldName;
		this.webType=webType;
		this.isKey=isKey;
		this.isQueryCondition=isQueryConditon;
		this.regExp=regExp;
	}
	public WebField(String fieldName,String webType,boolean isKey,boolean isQueryConditon){
//		this.chineseName=chineseName;
		this.fieldName=fieldName;
		this.webType=webType;
		this.isKey=isKey;
		this.isQueryCondition=isQueryConditon;
	}
	public WebField(String fieldName,String webType,boolean isKey,boolean isQueryConditon,String store){
//		this.chineseName=chineseName;
		this.fieldName=fieldName;
		this.webType=webType;
		this.isKey=isKey;
		this.isQueryCondition=isQueryConditon;
		this.store=store;
	}
	public WebField(String fieldName,String webType,boolean isKey,boolean isQueryConditon,String store,String renderer){
//		this.chineseName=chineseName;
		this.fieldName=fieldName;
		this.webType=webType;
		this.isKey=isKey;
		this.isQueryCondition=isQueryConditon;
		this.store=store;
		this.renderer=renderer;
	}
	public WebField(String fieldName,String webType){
		this.fieldName=fieldName;
		this.webType=webType;
	}	
	public WebField(String fieldName, Class fieldType, int fieldLength, boolean isNull,String chineseName) {
//		super();
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldLength = fieldLength;
		this.allowNull = isNull;
//		this.chineseName=chineseName;
	}
	public String getWebType() {
		return webType;
	}

	public void setWebType(String webType) {
		this.webType = webType;
	}
	public boolean isKey() {
		return isKey;
	}
	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}
	public boolean isQueryCondition() {
		return isQueryCondition;
	}
	public void setQueryCondition(boolean isQueryCondition) {
		this.isQueryCondition = isQueryCondition;
	}
	public String getStore() {
		return store;
	}
	public WebField setStore(String store) {
		this.store = store;
		return this;
	}
	public String getRenderer() {
		return renderer;
	}
	public WebField setRenderer(String renderer) {
		this.renderer = renderer;
		return this;
	}
	public String getFieldSimpleName() {
		String name=this.getFieldName();
		if(CommonUtils.isValidString(name)){
			return CommonUtils.getSimpleName(name);
		}
		return simpleName;
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getRegExp() {
		return regExp;
	}
	public WebField setRegExp(String regExp) {
		this.regExp = regExp;
		return this;
	}
	public String getValidator() {
		return validator;
	}
	public WebField setValidator(String validator) {
		this.validator = validator;
		return this;
	}
	public String getQueryType() {
		return queryType;
	}
	public WebField setQueryType(String queryType) {
		this.queryType = queryType;
		return this;
	}
	public String getDbFieldName(){
		String simpleName=this.getFieldSimpleName();
		StringBuffer name=new StringBuffer(simpleName);
		for(int i=0;i<name.length();i++){
			char c1=name.charAt(i);
			if(Character.isUpperCase(c1)){
				name.insert(i, "_");
				i++;
			}
		}
		return name.toString().toUpperCase();
	}
}
