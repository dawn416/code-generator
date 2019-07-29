package com.fline.generator.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fline.generator.GenerateException;
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

    private static final Logger LOG = LoggerFactory.getLogger(TableContext.class);
    public static final List<TableItem> TABLES = new ArrayList<>();

    /**
     * @param convert    类型转换器
     * @param tableName  表名（为空则输出所有表）
     * @param entityName 指定javabean名称，必须在有表名的前提下
     * @throws Exception
     */
    static String catalog = null;
    static String scheme = "%";

    public static void loadTable() {
        String tableName = Generator.generatorConfig.getJdbcInfo().getTable();
        String entityName = Generator.generatorConfig.getJdbcInfo().getEntity();
        try (Connection conn = DBUtil.createConn();) {
            // DatabaseMetaData 封装了数据库的源信息
            DatabaseMetaData dbmd = conn.getMetaData();
            String tablePattern = "%";
            if (!StringUtil.hasText(tableName)) {
                tablePattern = tableName;
            }

            if (!StringUtil.hasText(Generator.generatorConfig.getJdbcInfo().getCatalog())) {
                catalog = Generator.generatorConfig.getJdbcInfo().getCatalog();
            }
            if (!StringUtil.hasText(Generator.generatorConfig.getJdbcInfo().getScheme())) {
                scheme = Generator.generatorConfig.getJdbcInfo().getScheme();
            }

            try (ResultSet tableRs = dbmd.getTables(catalog, scheme, tablePattern, new String[] { "TABLE" });) {
                while (tableRs.next()) {
                    loadTableDetail(entityName, dbmd, tableRs);
                }
            }
            LOG.debug("所有表信息加载完成");
        } catch (Exception e) {
            throw new GenerateException("加载表信息失败", e);
        }

    }

    private static void loadTableDetail(String entityName, DatabaseMetaData dbmd, ResultSet tableRs)
            throws SQLException {
        String tableName = tableRs.getString("TABLE_NAME");
        String remarks = tableRs.getString("REMARKS");
        LOG.debug("加载{}表信息中。。。", tableName);
        String beanName = null;
        if (StringUtil.hasText(entityName)) {
            beanName = StringUtil.underline2Camel(tableName, false);
        } else {
            beanName = entityName;
        }
        String uncapitalize = StringUtil.uncapitalize(beanName);
        TableItem tableItem = new TableItem(beanName, tableName, new ArrayList<ColumnItem>(), remarks, uncapitalize);

        TABLES.add(tableItem);
        // 查询表中的所有字段
        try (ResultSet columnRs = dbmd.getColumns(catalog, scheme, tableName, "%");) {
            loadColumns(tableItem, columnRs);
        }
        // 查询表中的主键
        try (ResultSet pkRs = dbmd.getPrimaryKeys(catalog, scheme, tableName);) {
            if (loadPrimaryKeys(tableItem, pkRs)) {
                throw new GenerateException("表" + tableName + "缺少主键");
            }
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

    private static boolean loadPrimaryKeys(TableItem tableItem, ResultSet pkRs) throws SQLException {
        boolean noId = true;
        while (pkRs.next()) {
            String pkName = pkRs.getString("COLUMN_NAME");
            for (Iterator<ColumnItem> iterator = tableItem.getColumnList().iterator(); iterator.hasNext();) {
                ColumnItem cItem = iterator.next();
                if (cItem.getColumnName().equals(pkName)) {
                    cItem.setId(true);
                    noId = false;
                }
            }
        }
        return noId;
    }

    private TableContext() {
    }
}
