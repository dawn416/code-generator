/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator;

import java.io.InputStream;
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
        map.put("key", "value");
        Generator.generate("test/NewFile.xml", map);
    }

    public static void generate(String configFile, Map<String, Object> param) throws Exception {
        customParams = param;
        InputStream resourceAsStream = Generator.class.getClassLoader().getResourceAsStream(configFile);
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xstream.alias("generator", GeneratorConfig.class);
        generatorConfig = (GeneratorConfig) xstream.fromXML(resourceAsStream);
        System.out.println(generatorConfig);
        for (TypeConvert typeconcert : generatorConfig.getTypeConvertList()) {
            CommonConvertor.map.put(typeconcert.getJdbc(), typeconcert.getJava());
        }
        TableContext.loadTable();

        for (TableItem item : TableContext.TABLES) {
            System.out.println(item);
            for (TemplateItem templateItem : generatorConfig.getTemplateList()) {
                try {
                    String templatePackage = templateItem.getTemplatePackage().replace($_ENTITY, item.getBeanName())
                            .replace($_TABLE, item.getTableName());
                    String fileName = templateItem.getFileName().replace($_ENTITY, item.getBeanName()).replace($_TABLE,
                            item.getTableName());
                    String templateFile = templateItem.getTemplateFile().replace($_ENTITY, item.getBeanName())
                            .replace($_TABLE, item.getTableName());
                    String path = StringUtil.pathConvert(templatePackage);
                    JavaFileUtil.createPath(path);
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("tableItem", item);
                    dataMap.put("fileName", fileName);
                    dataMap.put("templatePackage", templatePackage);
                    TemplateManager.createXml(templateFile, path, fileName, dataMap);
                } catch (GenerateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
