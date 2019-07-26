package com.fline.generator.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fline.generator.Generator;
import com.fline.generator.bean.ColumnItem;
import com.fline.generator.bean.TableItem;
import com.fline.generator.typeconvertor.TypeConvertor;
import com.fline.generator.util.StringUtils;

/**
 * 
 * <h1>TableContext</h1>
 * <h1>管理数据库所有表结构和类结构的关系，根据表结构可以生成类结构</h1>
 * 
 */
public class TableContext {

	public static final List<TableItem> TABLES = new ArrayList<>();

	/**
	 * @param convert
	 *            类型转换器
	 * @param tableName
	 *            表名（为空则输出所有表）
	 * @param className
	 *            指定javabean名称，必须在有表名的前提下
	 * @throws Exception
	 */
	public static void init(TypeConvertor convert, String tableName, String className) throws Exception {

		try (Connection conn = DBManager.createConn();) {
			// DatabaseMetaData 封装了数据库的源信息
			DatabaseMetaData dbmd = conn.getMetaData();
			String tablePattern = "%";
			if (!StringUtils.isEmpty(tableName)) {
				tablePattern = tableName;
			}
			try (ResultSet tableRs = dbmd.getTables(null, null, tablePattern, new String[] { "TABLE" });) {
				while (tableRs.next()) {
					tableName = tableRs.getString("TABLE_NAME");
					tableName = tableName.toUpperCase();
					System.out.println("加载" + tableName + "表信息中。。。");
					String beanName = null;
					if (StringUtils.isEmpty(className)) {
						beanName = StringUtils.underline2Camel(tableName, false);
					} else {
						beanName = className;
					}
					TableItem tableItem = new TableItem(tableName, beanName, null, new ArrayList<ColumnItem>());

					TABLES.add(tableItem);

					// 查询表中的所有字段
					try (ResultSet columnRs = dbmd.getColumns(null, "%", tableName, "%");) {
						while (columnRs.next()) {
							String columnName = columnRs.getString("COLUMN_NAME");
							String fieldName = null;
							if (Generator.CAMEL) {
								fieldName = StringUtils.underline2Camel(columnName, true);
							} else {
								fieldName = columnName;
							}
							String dbType = columnRs.getString("TYPE_NAME");
							String remarks = columnRs.getString("REMARKS");
							String javaType = convert.dbType2JavaType(dbType);
							ColumnItem columnItem = new ColumnItem(columnName, fieldName, dbType, javaType);
							columnItem.setRemarks(remarks);
							if (tableItem.getPrimaryKey() == null || !columnItem.getColumnName()
									.equalsIgnoreCase(tableItem.getPrimaryKey().getColumnName())) {
								tableItem.getColumnList().add(columnItem);
							}
						}
					}
					// 查询表中的主键
					try (ResultSet pkRs = dbmd.getPrimaryKeys(null, "%", tableName);) {
						while (pkRs.next()) {
							String pkName = pkRs.getString("COLUMN_NAME");
							for (ColumnItem cItem : tableItem.getColumnList()) {
								if (cItem.getColumnName().equalsIgnoreCase(pkName)) {
									tableItem.getColumnList().remove(cItem);
									tableItem.setPrimaryKey(cItem);
									break;
								}
							}
						}
					}
					if (tableItem.getPrimaryKey() == null) {
						throw new RuntimeException("表" + tableName + "缺少主键");
					}
				}
			}
			System.out.println("所有表加载完成");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("加载表信息失败");
			throw e;
		}

	}

}
