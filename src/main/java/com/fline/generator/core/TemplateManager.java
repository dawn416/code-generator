package com.fline.generator.core;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fline.generator.util.FreemarkerUtil;
import com.fline.generator.util.JavaFileUtil;

import freemarker.template.TemplateException;

/**
 * @since 2017年12月7日 下午5:54:00
 * @version 1.0.0
 * @author 黎明
 *
 */
public class TemplateManager {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateManager.class);

    public static void createXml(String templateName, String path, String finalFileName, Map<String, Object> dataMap)
            throws IOException, TemplateException {
        LOG.debug("生成{}文件中。。。", finalFileName);
        String result = FreemarkerUtil.createFile("", templateName, dataMap);
        JavaFileUtil.createJavaFile(path + File.separator + finalFileName, result);
        LOG.debug("生成{}文件成功{}", finalFileName, path);
    }

    private TemplateManager() {
    }
}
