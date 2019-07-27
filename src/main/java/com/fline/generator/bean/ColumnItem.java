/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
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
    }

    @Override
    public String toString() {
        return "ColumnItem [columnName=" + columnName + ", fieldName=" + fieldName + ", jdbcType=" + jdbcType
                + ", javaType=" + javaType + ", remarks=" + remarks + "]";
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

}
