package com.fline.generator.bean;

import java.util.List;

/**
 * @since 2017年12月6日 下午4:03:04
 * @version 1.0.0
 * @author 黎明
 */
public class TableItem {
    private String beanName;
    private String tableName;
    private List<ColumnItem> primaryKey;
    private List<ColumnItem> columnList;
    private String remark;
    private String alias;

    /**
     * @param beanName
     * @param tableName
     * @param primaryKey
     * @param columnList
     * @param remark
     * @param alias
     */
    public TableItem(String beanName, String tableName, List<ColumnItem> primaryKey, List<ColumnItem> columnList) {
        super();
        this.beanName = beanName;
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.columnList = columnList;
    }

    /**
     * @param beanName
     * @param tableName
     * @param primaryKey
     * @param columnList
     * @param remark
     * @param alias
     */
    public TableItem(String beanName, String tableName, List<ColumnItem> primaryKey, List<ColumnItem> columnList,
            String remark, String alias) {
        super();
        this.beanName = beanName;
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.columnList = columnList;
        this.remark = remark;
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "TableItem [beanName=" + beanName + ", tableName=" + tableName + ", primaryKey=" + primaryKey
                + ", columnList=" + columnList + ", remark=" + remark + ", alias=" + alias + "]";
    }

    public TableItem() {
    }

    public List<ColumnItem> getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(List<ColumnItem> primaryKey) {
        this.primaryKey = primaryKey;
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
