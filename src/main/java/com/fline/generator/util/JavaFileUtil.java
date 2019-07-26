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

	/**
	 * 根据包名创建目录
	 */
	public static void createPath(String modeProject) {
		modelPath = Generator.modeProject + "\\" + StringUtils.pathConvert(Generator.modelPackage);
		File modelFile = new File(modelPath);
		if (!modelFile.exists()) {
			modelFile.mkdirs();
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
