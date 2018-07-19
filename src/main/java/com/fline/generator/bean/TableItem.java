/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator.bean;

import java.util.List;

/**
 * @since 2017年12月6日 下午4:03:04
 * @version 1.0.0
 * @author 黎明
 *
 */
public class TableItem {
	private String beanName;
	private String tableName;
	private ColumnItem primaryKey;
	private List<ColumnItem> columnList;

	/**
	 * @param beanName
	 * @param tableName
	 * @param primaryKey
	 * @param columnList
	 */
	public TableItem(String tableName, String beanName, ColumnItem primaryKey, List<ColumnItem> columnList) {
		super();
		this.beanName = beanName;
		this.tableName = tableName;
		this.primaryKey = primaryKey;
		this.columnList = columnList;
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
		result = prime * result + ((beanName == null) ? 0 : beanName.hashCode());
		result = prime * result + ((columnList == null) ? 0 : columnList.hashCode());
		result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
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
		TableItem other = (TableItem) obj;
		if (beanName == null) {
			if (other.beanName != null) {
				return false;
			}
		} else if (!beanName.equals(other.beanName)) {
			return false;
		}
		if (columnList == null) {
			if (other.columnList != null) {
				return false;
			}
		} else if (!columnList.equals(other.columnList)) {
			return false;
		}
		if (primaryKey == null) {
			if (other.primaryKey != null) {
				return false;
			}
		} else if (!primaryKey.equals(other.primaryKey)) {
			return false;
		}
		if (tableName == null) {
			if (other.tableName != null) {
				return false;
			}
		} else if (!tableName.equals(other.tableName)) {
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
		return "TableItem [beanName=" + beanName + ", tableName=" + tableName + ", primaryKey=" + primaryKey
				+ ", columnList=" + columnList + "]\n";
	}

	/**
	 * @return the beanName
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * @param beanName
	 *            the beanName to set
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the primaryKey
	 */
	public ColumnItem getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey
	 *            the primaryKey to set
	 */
	public void setPrimaryKey(ColumnItem primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return the columnList
	 */
	public List<ColumnItem> getColumnList() {
		return columnList;
	}

	/**
	 * @param columnList
	 *            the columnList to set
	 */
	public void setColumnList(List<ColumnItem> columnList) {
		this.columnList = columnList;
	}
}
