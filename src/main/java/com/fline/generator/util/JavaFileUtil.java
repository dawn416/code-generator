/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fline.generator.Generator;
import com.fline.generator.core.DBManager;

/**
 * @since 2017年12月7日 上午11:48:58
 * @version 1.0.0
 * @author 黎明
 *
 */
public class JavaFileUtil {
	public static String modelPath;
	public static String xmlPath;
	public static String daoPath;
	public static String daoImplPath;

	/**
	 * 根据包名创建目录
	 */
	public static void createPath() {
		modelPath = Generator.modeProject + "\\" + StringUtils.pathConvert(Generator.modelPackage);
		xmlPath = Generator.modeProject + "\\" + StringUtils.pathConvert(Generator.xmlPackage);
		daoPath = Generator.modeProject + "\\" + StringUtils.pathConvert(Generator.daoPackage);
		daoImplPath = Generator.modeProject + "\\" + StringUtils.pathConvert(Generator.daoImplPackage);
		File modelFile = new File(modelPath);
		File xmlFile = new File(xmlPath);
		File daoFile = new File(daoPath);
		File daoImplFile = new File(daoImplPath);
		if (!modelFile.exists()) {
			modelFile.mkdirs();
		}
		if (!xmlFile.exists()) {
			xmlFile.mkdirs();
		}
		if (!daoFile.exists()) {
			daoFile.mkdirs();
		}
		if (!daoImplFile.exists()) {
			daoImplFile.mkdirs();
		}
	}

	/**
	 * 生成文件
	 * 
	 * @param path
	 * @param content
	 */
	public static void createJavaFile(String path, String content) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(path));
			bw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(bw);
		}
	}
}
