package com.fline.generator.bean;

/**
 * @since 2017年12月6日 下午4:05:34
 * @version 1.0.0
 * @author 黎明
 *
 */
public class ColumnItem {
    private String columnName;
    private String fieldName;
    private String jdbcType;
    private String javaType;
    private String remarks;
    private boolean id;
    private int datasize;
    private int digits;
    private boolean nullable;
    private String defaultValue;
    private boolean autoincrement;

    /**
     * @param columnName
     * @param fieldName
     * @param jdbcType
     * @param javaType
     */
    public ColumnItem(String columnName, String fieldName, String jdbcType, String javaType) {
        super();
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.jdbcType = jdbcType;
        this.javaType = javaType;
        this.id = false;
    }

    @Override
    public String toString() {
        return "ColumnItem [columnName=" + columnName + ", fieldName=" + fieldName + ", jdbcType=" + jdbcType
                + ", javaType=" + javaType + ", remarks=" + remarks + ", id=" + id + "]";
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return the jdbcType
     */
    public String getJdbcType() {
        return jdbcType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @param jdbcType the jdbcType to set
     */
    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    /**
     * @return the javaType
     */
    public String getJavaType() {
        return javaType;
    }

    /**
     * @param javaType the javaType to set
     */
    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public int getDatasize() {
        return datasize;
    }

    public void setDatasize(int datasize) {
        this.datasize = datasize;
    }

    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isAutoincrement() {
        return autoincrement;
    }

    public void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

}
