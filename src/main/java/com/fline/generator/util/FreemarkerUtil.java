/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator.util;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {

    public static String createFile(String templatePath, String templateName, Map<String, Object> dataMap)
            throws IOException, TemplateException {
        String result = "";
        // 得FreeMarker配置对象
        // 创建Configuration对象
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        // 设置模板编码格式
        cfg.setEncoding(Locale.getDefault(), StandardCharsets.UTF_8.name());
        // 得FreeMarker的关键对象---------模板
        // 创建Template对象
        Template template = null;
        try (StringWriter w = new StringWriter();) {
            // 设置FreeMarker的模版位置
            cfg.setClassLoaderForTemplateLoading(FreemarkerUtil.class.getClassLoader(), templatePath);
            // 设置FreeMarker的模版文件名
            template = cfg.getTemplate(templateName);
            // 生成xml
            template.process(dataMap, w);
            result = w.toString();
        }
        return result;
    }

    private FreemarkerUtil() {
    }
}
