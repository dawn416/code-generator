/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.fline.generator.bean.TableItem;
import com.fline.generator.bean.TemplateItem;
import com.fline.generator.core.JavaDaoManager;
import com.fline.generator.core.JavaPoManager;
import com.fline.generator.core.TableContext;
import com.fline.generator.core.XmlManager;
import com.fline.generator.typeconvertor.MySQLTypeConvertor;
import com.fline.generator.typeconvertor.TypeConvertor;

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

	public static Properties properties;
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
		TemplateItem t1 = new TemplateItem("dao.package", "dao.template");
		TemplateItem t2 = new TemplateItem("service.package", "service.template");
		List<TemplateItem> list = new ArrayList<>();
		list.add(t1);
		list.add(t2);
		properties = new Properties();
		InputStream resourceAsStream = Generator.class.getClassLoader()
				.getResourceAsStream("GeneratorConfig.properties");
		properties.load(resourceAsStream);
		test(list);
	}

	public static void test(List<TemplateItem> list) throws IOException {

		for (TemplateItem templateItem : list) {
			String templatePackage = properties.getProperty(templateItem.getTemplatePackage());
			System.out.println(templatePackage);
		}

	}

	public void generate(String tableName, String BeanName) throws Exception {
		TypeConvertor convertor = new MySQLTypeConvertor();
		// 加载数据库配置
		// 加载表信息
		TableContext.init(convertor, tableName, BeanName);
		// 模板生成文件
		for (TableItem item : TableContext.TABLES) {
			// 文件生成
			JavaDaoManager.createDaoFile(item);
			JavaDaoManager.createDaoImplFile(item);
			XmlManager.createXml(item, "mysql_template.xml");
			JavaPoManager.createJavaPOFile(item);
		}
	}

}
