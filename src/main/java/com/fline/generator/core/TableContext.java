package com.fline.generator.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fline.generator.Generator;
import com.fline.generator.bean.ColumnItem;
import com.fline.generator.bean.TableItem;
import com.fline.generator.util.DBUtil;
import com.fline.generator.util.StringUtil;

/**
 * 
 * <h1>TableContext</h1>
 * <h1>管理数据库所有表结构和类结构的关系，根据表结构可以生成类结构</h1>
 * 
 */
public class TableContext {

    public static final List<TableItem> TABLES = new ArrayList<>();

    /**
     * @param convert    类型转换器
     * @param tableName  表名（为空则输出所有表）
     * @param entityName 指定javabean名称，必须在有表名的前提下
     * @throws Exception
     */
    public static void loadTable() throws Exception {
        String tableName = Generator.generatorConfig.getJdbcInfo().getTable();
        String entityName = Generator.generatorConfig.getJdbcInfo().getEntity();
        try (Connection conn = DBUtil.createConn();) {
            // DatabaseMetaData 封装了数据库的源信息
            DatabaseMetaData dbmd = conn.getMetaData();
            String tablePattern = "%";
            if (!StringUtil.isEmpty(tableName)) {
                tablePattern = tableName;
            }
            try (ResultSet tableRs = dbmd.getTables(null, null, tablePattern, new String[] { "TABLE" });) {
                while (tableRs.next()) {
                    loadTableDetail(entityName, dbmd, tableRs);
                }
            }
            System.out.println("所有表加载完成");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("加载表信息失败");
            throw e;
        }

    }

    private static void loadTableDetail(String entityName, DatabaseMetaData dbmd, ResultSet tableRs)
            throws SQLException {
        String tableName = tableRs.getString("TABLE_NAME");
        String remarks = tableRs.getString("REMARKS");
        System.out.println("加载" + tableName + "表信息中。。。");
        String beanName = null;
        if (StringUtil.isEmpty(entityName)) {
            beanName = StringUtil.underline2Camel(tableName, false);
        } else {
            beanName = entityName;
        }
        String uncapitalize = StringUtil.uncapitalize(beanName);
        TableItem tableItem = new TableItem(beanName, tableName, new ArrayList<ColumnItem>(),
                new ArrayList<ColumnItem>(), remarks, uncapitalize);

        TABLES.add(tableItem);
        // 查询表中的所有字段
        try (ResultSet columnRs = dbmd.getColumns(null, "%", tableName, "%");) {
            loadColumns(tableItem, columnRs);
        }
        // 查询表中的主键
        try (ResultSet pkRs = dbmd.getPrimaryKeys(null, "%", tableName);) {
            loadPrimaryKeys(tableItem, pkRs);
        }
        if (tableItem.getPrimaryKey() == null) {
            throw new RuntimeException("表" + tableName + "缺少主键");
        }
    }

    private static void loadColumns(TableItem tableItem, ResultSet columnRs) throws SQLException {
        while (columnRs.next()) {
            String columnName = columnRs.getString("COLUMN_NAME");
            String fieldName = null;
            if (Generator.generatorConfig.getJdbcInfo().isCamel()) {
                fieldName = StringUtil.underline2Camel(columnName, true);
            } else {
                fieldName = columnName;
            }
            String dbType = columnRs.getString("TYPE_NAME");
            String remarks = columnRs.getString("REMARKS");
            String javaType = CommonConvertor.dbType2JavaType(dbType);
            ColumnItem columnItem = new ColumnItem(columnName, fieldName, dbType, javaType);
            columnItem.setRemarks(remarks);
            tableItem.getColumnList().add(columnItem);
        }
    }

    private static void loadPrimaryKeys(TableItem tableItem, ResultSet pkRs) throws SQLException {
        while (pkRs.next()) {
            String pkName = pkRs.getString("COLUMN_NAME");
            for (Iterator iterator = tableItem.getColumnList().iterator(); iterator.hasNext();) {
                ColumnItem cItem = (ColumnItem) iterator.next();
                if (cItem.getColumnName().equalsIgnoreCase(pkName)) {
                    iterator.remove();
                    tableItem.getPrimaryKey().add(cItem);
                }
            }
        }
    }

    private TableContext() {
    }
}
