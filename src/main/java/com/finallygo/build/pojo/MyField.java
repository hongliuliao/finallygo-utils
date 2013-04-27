package com.finallygo.build.pojo;

public class MyField {
	protected String fieldName;//字段名
	protected Class fieldType;//字段类型
	protected int fieldLength;//字段长度
	protected boolean allowNull;//是否允许为空
	protected int dataType;//对应java.sql.Types中的类型
	protected String dbFieldType;//在数据库中类型
	protected boolean key;//是否是主键
	protected Object defaultValue;
	protected boolean foreignKey;//是否有外键
	protected int scale;//精度(小数位)
	protected int precision;//小数总位数,这个是针对oracle设计的字段
	//protected String chineseName;//中文名
	
	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDbFieldType() {
		return dbFieldType;
	}

	public void setDbFieldType(String dbFieldType) {
		this.dbFieldType = dbFieldType;
	}

	public MyField() {
		// TODO Auto-generated constructor stub
	}
	
	public MyField(String fieldName, Class fieldType, String chineseName) {
		super();
		this.fieldName = fieldName;
		this.fieldType = fieldType;
//		this.chineseName = chineseName;
	}

	public MyField(String fieldName, Class fieldType, int fieldLength, boolean isNull,String chineseName) {
		super();
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldLength = fieldLength;
		this.allowNull = isNull;
//		this.chineseName=chineseName;
	}
	public int getFieldLength() {
		return fieldLength;
	}
	public MyField setFieldLength(int fieldLength) {
		this.fieldLength = fieldLength;
		return this;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Class getFieldType() {
		return fieldType;
	}

	public void setFieldType(Class fieldType) {
		this.fieldType = fieldType;
	}

	public boolean isAllowNull() {
		return allowNull;
	}

	public MyField setAllowNull(boolean allowNull) {
		this.allowNull = allowNull;
		return this;
	}

	public boolean isKey() {
		return key;
	}

	public void setKey(boolean key) {
		this.key = key;
	}

	public boolean isForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(boolean foreignKey) {
		this.foreignKey = foreignKey;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}


//	public String getChineseName() {
//		return chineseName;
//	}
//	public void setChineseName(String chineseName) {
//		this.chineseName = chineseName;
//	}
}
