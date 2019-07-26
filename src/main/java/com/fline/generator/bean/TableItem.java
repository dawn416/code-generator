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
	private String remark;
	private String alias;

	/**
	 * 
	 */
	public TableItem() {
	}

	/**
	 * @param beanName
	 * @param tableName
	 * @param primaryKey
	 * @param columnList
	 */
	public TableItem(String beanName, String tableName, ColumnItem primaryKey, List<ColumnItem> columnList) {
		super();
		this.beanName = beanName;
		this.tableName = tableName;
		this.primaryKey = primaryKey;
		this.columnList = columnList;
	}

	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public ColumnItem getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(ColumnItem primaryKey) {
		this.primaryKey = primaryKey;
	}
	public List<ColumnItem> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<ColumnItem> columnList) {
		this.columnList = columnList;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
