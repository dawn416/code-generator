/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator.core;

import com.fline.generator.Generator;
import com.fline.generator.bean.TableItem;

/**
 * @since 2017年12月7日 下午3:32:10
 * @version 1.0.0
 * @author 黎明
 *
 */
public class JavaDaoManager {

	/**
	 * 生成dao层文件
	 * 
	 * @param tableItem
	 *            表信息
	 */
	public static void createDaoFile(TableItem tableItem) {
		String content = createDao(tableItem);
		// String path = JavaFileUtil.daoPath + "\\" + tableItem.getBeanName() +
		// "AccessService.java";
		// JavaFileUtil.createJavaFile(path, content);
		System.out.println("生成" + tableItem.getBeanName() + "AccessService.java文件成功");
	}

	/**
	 * 生成daoImpl文件
	 * 
	 * @param tableItem
	 */
	public static void createDaoImplFile(TableItem tableItem) {
		String content = createDaoImpl(tableItem);
		// String path = JavaFileUtil.daoImplPath + "\\" +
		// tableItem.getBeanName() + "AccessServiceImpl.java";
		// JavaFileUtil.createJavaFile(path, content);
		System.out.println("生成" + tableItem.getBeanName() + "AccessServiceImpl.java文件成功");
	}

	/**
	 * dao内容生成
	 * 
	 * @param tableItem
	 * @return
	 */
	public static String createDao(TableItem tableItem) {
		System.out.println("生成" + tableItem.getBeanName() + "dao层内容。。。");
		StringBuilder builder = new StringBuilder();
		builder.append("package ");
		builder.append(Generator.daoPackage);
		builder.append(";\n\n");
		builder.append("import com.feixian.aip.platform.access.common.service.AbstractNamespaceAccessService;\n");
		builder.append("import ");
		builder.append(Generator.modelPackage);
		builder.append(".");
		builder.append(tableItem.getBeanName());
		builder.append(";\n\n");
		builder.append("public interface ");
		builder.append(tableItem.getBeanName());
		builder.append("AccessService extends AbstractNamespaceAccessService<");
		builder.append(tableItem.getBeanName());
		builder.append("> {\n\n");
		builder.append("}");
		return builder.toString();
	}

	/**
	 * daoimpl内容生成
	 * 
	 * @param tableItem
	 * @return
	 */
	public static String createDaoImpl(TableItem tableItem) {
		System.out.println("生成" + tableItem.getBeanName() + "daoImpl层内容。。。");
		StringBuilder builder = new StringBuilder();
		builder.append("package ");
		builder.append(Generator.daoImplPackage);
		builder.append(";\n\n");
		builder.append(
				"import com.feixian.aip.platform.access.common.service.impl.AbstractNamespaceAccessServiceImpl;\n");
		builder.append("import ");
		builder.append(Generator.modelPackage);
		builder.append(".");
		builder.append(tableItem.getBeanName());
		builder.append(";\n\n");
		builder.append("import ");
		builder.append(Generator.daoPackage);
		builder.append(".");
		builder.append(tableItem.getBeanName());
		builder.append("AccessService;\n\n");
		builder.append("public class ");
		builder.append(tableItem.getBeanName());
		builder.append("AccessServiceImpl extends ");
		builder.append("AbstractNamespaceAccessServiceImpl<");
		builder.append(tableItem.getBeanName());
		builder.append("> implements ");
		builder.append(tableItem.getBeanName());
		builder.append("AccessService {\n\n");
		builder.append("}");
		return builder.toString();

	}
}
