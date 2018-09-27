package com.fline.generator.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fline.generator.Generator;
import com.fline.generator.bean.ColumnItem;
import com.fline.generator.bean.JavaFieldGetSet;
import com.fline.generator.bean.TableItem;
import com.fline.generator.util.JavaFileUtil;
import com.fline.generator.util.StringUtils;

/**
 * 
 * <h1>JavaFileUtils</h1>
 * <h1>封装了生成Java文件（源代码）操作</h1>
 * 
 */
public class JavaPoManager {

	/**
	 * po中排除生成的字段（lifecyclemodel中已有的）
	 */
	public static String[] excluArr = new String[] { "name", "id", "code", "version", "memo", "nameLike", "createDate",
			"creator", "updateDate", "updater" };

	/**
	 * 根据字段信息生成Java属性及getset方法
	 * 
	 * @param column
	 *            字段信息
	 * @param convertor
	 *            类型转换器
	 * @return
	 */
	private static JavaFieldGetSet createJavaFieldGetSetSrc(ColumnItem column) {
		JavaFieldGetSet jfgs = new JavaFieldGetSet();

		/*
		 * 拼接属性声明源码 例如：private String name; 拼接时候注意排版
		 */
		jfgs.setFieldInfo("\t/**\n\t * " + column.getRemarks() + "\n\t */\n\tprivate " + column.getJavaType() + " "
				+ column.getFieldName() + ";\n");

		/*
		 * 拼接get方法源码 例如： public String getName() { return name; }
		 */
		StringBuffer sbGet = new StringBuffer();
		sbGet.append(
				"\tpublic " + column.getJavaType() + " get" + StringUtils.capitalize(column.getFieldName()) + "() {\n");
		sbGet.append("\t\treturn " + column.getFieldName() + ";\n");
		sbGet.append("\t}\n");
		jfgs.setGetInfo(sbGet.toString());

		/*
		 * 拼接set方法源码 例如： public void setName() { this.name = name; }
		 */
		StringBuffer sbSet = new StringBuffer();
		sbSet.append("\tpublic void set" + StringUtils.capitalize(column.getFieldName()) + "(" + column.getJavaType()
				+ " " + column.getFieldName() + ") {\n");
		sbSet.append("\t\tthis." + column.getFieldName() + " = " + column.getFieldName() + ";\n");
		sbSet.append("\t}\n");
		jfgs.setSetInfo(sbSet.toString());

		return jfgs;

	}

	/**
	 * 根据表信息生成Java类的源代码
	 * 
	 * @param tableInfo
	 *            表信息
	 * @param convertor
	 *            类型转换器
	 * @return Java类源代码
	 */
	public static String createJavaSrc(TableItem tableItem) {
		System.out.println("生成" + tableItem.getBeanName() + "bean内容中。。。");
		List<String> exclude = Arrays.asList(excluArr);
		List<ColumnItem> columns = tableItem.getColumnList();
		List<JavaFieldGetSet> javaFields = new ArrayList<JavaFieldGetSet>();
		boolean hasDate = false;
		boolean hasDecimal = false;
		for (ColumnItem ci : columns) {
			if (!exclude.contains(ci.getFieldName())) {
				if ("java.util.Date".equals(ci.getJavaType())) {
					hasDate = true;
					ci.setJavaType("Date");
				}
				if ("java.math.BigDecimal".equals(ci.getJavaType())) {
					hasDecimal = true;
					ci.setJavaType("BigDecimal");
				}
				javaFields.add(createJavaFieldGetSetSrc(ci));
			}

		}

		StringBuffer src = new StringBuffer();

		// 生成package语句
		src.append("package " + Generator.modelPackage + ";\n\n");

		// 生成import语句
		if (hasDate) {
			src.append("import java.util.Date;\n\n");
		}
		if (hasDecimal) {
			src.append("import java.math.BigDecimal;\n\n");
		}
		src.append("import com.feixian.tp.model.LifecycleModel;\n\n");
		// 生成类声明语句
		src.append("public class " + tableItem.getBeanName() + " extends LifecycleModel {\n\n");
		src.append("\tprivate static final long serialVersionUID = 1L;\n\n");
		// 生成属性列表
		for (JavaFieldGetSet temp : javaFields) {
			src.append(temp.getFieldInfo() + "\n");
		}

		// 生成get方法语句
		for (JavaFieldGetSet temp : javaFields) {
			src.append(temp.getGetInfo() + "\n");
		}

		// 生成set方法语句
		for (JavaFieldGetSet temp : javaFields) {
			src.append(temp.getSetInfo() + "\n");
		}
		src.append("\n");
		// 结束语句
		src.append("}\n");
		return src.toString();
	}

	/**
	 * 
	 * @param tableItem
	 *            表信息
	 * @param convertor
	 *            类型转换器
	 */
	public static void createJavaPOFile(TableItem tableItem) {
		String src = createJavaSrc(tableItem);
		String poPath = JavaFileUtil.modelPath + "\\" + tableItem.getBeanName() + ".java";
		JavaFileUtil.createJavaFile(poPath, src);
		System.out.println("生成" + tableItem.getBeanName() + ".java文件成功");

	}

}
