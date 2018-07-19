package com.fline.generator.typeconvertor;

/**
 * 
 * <h1>MySQLTypeConvertor</h1>
 * <h1>MySQL数据类型和Java数据类型的转换</h1>
 * 
 */
public class MySQLTypeConvertor implements TypeConvertor {

	@Override
	public String dbType2JavaType(String columnType) {
		switch (columnType.toLowerCase()) {
		case "varchar":
		case "char":
		case "text":
			return "String";
		case "int":
		case "tinyint":
		case "smallint":
		case "integer":
			return "Integer";
		case "bigint":
			return "Long";
		case "double":
		case "float":
			return "Double";
		case "date":
		case "datetime":
		case "time":
		case "timestamp":
			return "java.util.Date";
		case "decimal":
			return "java.math.BigDecimal";
		// case "clob":
		// return "java.sql.Clob";
		// case "blob":
		// return "java.sql.Blob";
		default:
			return null;
		}
	}

}
