/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import com.fline.generator.bean.GeneratorConfig;
import com.fline.generator.bean.TableItem;
import com.fline.generator.bean.TemplateItem;
import com.fline.generator.core.JavaDaoManager;
import com.fline.generator.core.JavaPoManager;
import com.fline.generator.core.TableContext;
import com.fline.generator.core.XmlManager;
import com.fline.generator.typeconvertor.MySQLTypeConvertor;
import com.fline.generator.typeconvertor.TypeConvertor;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

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
	public static final XmlFriendlyNameCoder nameCoder = new XmlFriendlyNameCoder("-_", "_");
	private static final String ENCODING = "UTF-8";
	private static final DomDriver fixDriver = new DomDriver(ENCODING, nameCoder);
	public static final XStream XSTREAM = new XStream(fixDriver);
	private static final String CHINA_TIME_ZONE = "Asia/Shanghai";
	static {
		// 时区处理
		TimeZone zone = TimeZone.getTimeZone(CHINA_TIME_ZONE); // 获得时区
		XSTREAM.registerConverter(new DateConverter(zone), XStream.PRIORITY_NORMAL);
		XSTREAM.autodetectAnnotations(true); // 开启序列化的注解形式
		XSTREAM.setMode(XStream.NO_REFERENCES);// 取消引用,如果没有这一步,会出现xml引用格式reference

	}
	public static void main(String[] args) throws Exception {

		InputStream resourceAsStream = Generator.class.getClassLoader()
				.getResourceAsStream("NewFile.xml");
		GeneratorConfig target = null;
		XStream xstream = XSTREAM;
		try {
			target = (GeneratorConfig) xstream.fromXML(resourceAsStream, target);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
