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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + ((javaType == null) ? 0 : javaType.hashCode());
		result = prime * result + ((jdbcType == null) ? 0 : jdbcType.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ColumnItem other = (ColumnItem) obj;
		if (columnName == null) {
			if (other.columnName != null) {
				return false;
			}
		} else if (!columnName.equals(other.columnName)) {
			return false;
		}
		if (fieldName == null) {
			if (other.fieldName != null) {
				return false;
			}
		} else if (!fieldName.equals(other.fieldName)) {
			return false;
		}
		if (javaType == null) {
			if (other.javaType != null) {
				return false;
			}
		} else if (!javaType.equals(other.javaType)) {
			return false;
		}
		if (jdbcType == null) {
			if (other.jdbcType != null) {
				return false;
			}
		} else if (!jdbcType.equals(other.jdbcType)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\tColumnItem [columnName=" + columnName + ", fieldName=" + fieldName + ", jdbcType=" + jdbcType
				+ ", javaType=" + javaType + "]\n";
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName
	 *            the columnName to set
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
	 * @param fieldName
	 *            the fieldName to set
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
	 * @param jdbcType
	 *            the jdbcType to set
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
	 * @param javaType
	 *            the javaType to set
	 */
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

}
