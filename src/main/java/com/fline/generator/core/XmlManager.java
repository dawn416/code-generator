/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator.core;

import java.util.HashMap;
import java.util.Map;

import com.fline.generator.Generator;
import com.fline.generator.bean.TableItem;
import com.fline.generator.util.FreemarkerUtil;
import com.fline.generator.util.JavaFileUtil;
import com.fline.generator.util.StringUtils;

/**
 * @since 2017年12月7日 下午5:54:00
 * @version 1.0.0
 * @author 黎明
 *
 */
public class XmlManager {
	public static void createXml(TableItem tableItem, String templateName) {
		System.out.println("生成xml文件中。。。");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("tableItem", tableItem);
		dataMap.put("modelPath", Generator.modelPackage);
		dataMap.put("alias", StringUtils.uncapitalize(tableItem.getBeanName()));
		dataMap.put("excluArr", JavaPoManager.excluArr);
		String result = FreemarkerUtil.createXmlFile("", templateName, "UTF-8", dataMap);
		String outPath = JavaFileUtil.xmlPath + "\\" + tableItem.getBeanName() + ".xml";
		JavaFileUtil.createJavaFile(outPath, result);
		System.out.println("生成xml文件成功");
	}
}
