/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator;

import com.fline.generator.bean.TableItem;
import com.fline.generator.core.JavaDaoManager;
import com.fline.generator.core.JavaPoManager;
import com.fline.generator.core.TableContext;
import com.fline.generator.core.XmlManager;
import com.fline.generator.typeconvertor.MySQLTypeConvertor;
import com.fline.generator.typeconvertor.TypeConvertor;
import com.fline.generator.util.JavaFileUtil;

/**
 * @since 2017年12月6日 下午2:04:18
 * @version 1.0.0
 * @author 黎明
 *
 */
public class Generator {

	/**
	 * 项目根目录
	 */
	public static String modeProject = "src";
	/**
	 * model目录
	 */
	public static String modelPackage = "com.fline.promise.access.model";
	/**
	 * ibatis的xml目录
	 */
	public static String xmlPackage = "com.fline.promise.access.model.mysql";
	/**
	 * dao目录
	 */
	public static String daoPackage = "com.fline.promise.access.service";
	/**
	 * dao实现类目录
	 */
	public static String daoImplPackage = "com.fline.promise.access.service.impl";
	/**
	 * 驱动
	 */
	public static String DRIVER = "com.mysql.jdbc.Driver";
	/**
	 * url
	 */
	public static String URL = "jdbc:mysql://127.0.0.1:3306/saizhi?useUnicode=true&characterEncoding=UTF8";
	/**
	 * 连接数据库的用户名
	 */
	public static String USER = "root";
	/**
	 * 连接数据库的密码
	 */
	public static String PASS = "123456";
	/**
	 * 命名规则是否为下划线转驼峰命名法
	 */
	public static boolean CAMEL = false;

	/**
	 * 所有表中必须有且仅有一个主键id,
	 * <p>
	 * com.fline.generator.util中可增加修改xml模板文件
	 * </p>
	 * JDK必须1.7(含)以上({@link com.fline.generator.typeconvertor.MySQLTypeConvertor}
	 * 使用了String作为switch的键)
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 要生成的表名，null或""表示生成所有表,
		String tableName = "area";
		// javabean名，必须在tableName不为空时使用
		String BeanName = "Area";

		Generator g = new Generator();
		g.generate(tableName, BeanName);

	}

	public void generate(String tableName, String BeanName) throws Exception {
		TypeConvertor convertor = new MySQLTypeConvertor();
		// 加载数据库配置
		// 加载表信息
		TableContext.init(convertor, tableName, BeanName);
		// 模板生成文件
		JavaFileUtil.createPath();
		for (TableItem item : TableContext.tables) {
			// 文件生成
			JavaDaoManager.createDaoFile(item);
			JavaDaoManager.createDaoImplFile(item);
			XmlManager.createXml(item, "mysql_template.xml");
			JavaPoManager.createJavaPOFile(item);
		}
	}

}
