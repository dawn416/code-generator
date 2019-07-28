/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fline.generator.bean.GeneratorConfig;
import com.fline.generator.bean.TableItem;
import com.fline.generator.bean.TemplateItem;
import com.fline.generator.bean.TypeConvert;
import com.fline.generator.core.CommonConvertor;
import com.fline.generator.core.TableContext;
import com.fline.generator.core.TemplateManager;
import com.fline.generator.util.JavaFileUtil;
import com.fline.generator.util.StringUtil;
import com.thoughtworks.xstream.XStream;

/**
 * @since 2017年12月6日 下午2:04:18
 * @version 1.0.0
 * @author 黎明
 *
 */
public class Generator {

    /**
     *
     */
    private static final String $_ENTITY = "${entity}";
    /**
     *
     */
    private static final String $_TABLE = "${table}";
    public static GeneratorConfig generatorConfig;
    public static Map<String, Object> customParams;

    public static void main(String[] args) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("excludeFields", Arrays.asList("id", "name", "code"));
        Generator.generate("codeGenerator/config.xml", null);
    }

    public static void generate(String configFile, Map<String, Object> param) throws Exception {
        customParams = param;
        try {
            InputStream resourceAsStream = Generator.class.getClassLoader().getResourceAsStream(configFile);
            XStream xstream = new XStream();
            xstream.autodetectAnnotations(true);
            xstream.alias("generator", GeneratorConfig.class);
            generatorConfig = (GeneratorConfig) xstream.fromXML(resourceAsStream);
        } catch (Exception e1) {
            System.out.println("配置文件解析出错");
            throw e1;
        }
        System.out.println(generatorConfig);
        for (TypeConvert typeconcert : generatorConfig.getTypeConvertList()) {
            CommonConvertor.map.put(typeconcert.getJdbc(), typeconcert.getJava());
        }
        TableContext.loadTable();

        for (TableItem item : TableContext.TABLES) {
            System.out.println(item);
            for (TemplateItem templateItem : generatorConfig.getTemplateList()) {
                try {
                    String targetPackage = templateItem.getTargetPackage().replace($_ENTITY, item.getBeanName())
                            .replace($_TABLE, item.getTableName());
                    String targetFileName = templateItem.getTargetFileName().replace($_ENTITY, item.getBeanName())
                            .replace($_TABLE, item.getTableName());
                    String templateFile = templateItem.getTemplateFile().replace($_ENTITY, item.getBeanName())
                            .replace($_TABLE, item.getTableName());
                    TemplateItem realTemplateItem = new TemplateItem();
                    realTemplateItem.setTargetFileName(targetFileName);
                    realTemplateItem.setTemplateFile(templateFile);
                    realTemplateItem.setTargetPackage(targetPackage);
                    realTemplateItem.setTargetProject(templateItem.getTargetProject());
                    String path = StringUtil.pathConvert(templateItem.getTargetProject() + "." + targetPackage);
                    JavaFileUtil.createPath(path);
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("tableItem", item);
                    dataMap.put("templateItem", realTemplateItem);
                    if (Generator.customParams != null) {
                        dataMap.put("customItem", Generator.customParams);
                    }
                    TemplateManager.createXml(templateFile, path, targetFileName, dataMap);
                } catch (GenerateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
