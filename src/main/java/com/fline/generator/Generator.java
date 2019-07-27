/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator;

import java.io.InputStream;
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
        InputStream resourceAsStream = Generator.class.getClassLoader().getResourceAsStream("NewFile.xml");
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xstream.alias("generator", GeneratorConfig.class);
        generatorConfig = (GeneratorConfig) xstream.fromXML(resourceAsStream);
        System.out.println(generatorConfig);

        TableContext.loadTable();
        System.out.println(TableContext.TABLES);
    }

    /**
     * 
     * @since 2019年7月27日 上午10:04:48
     */
    private void init() {
        for (TypeConvert typeconcert : generatorConfig.getTypeConvertList()) {
            CommonConvertor.map.put(typeconcert.getJdbc(), typeconcert.getJava());
        }

    }

    public static void create(String tableName, String beanName) throws Exception {
        TableContext.loadTable();

        for (TableItem item : TableContext.TABLES) {
            for (TemplateItem templateItem : generatorConfig.getTemplateList()) {
                String templatePackage = templateItem.getTemplatePackage().replaceAll($_ENTITY, item.getBeanName())
                        .replaceAll($_TABLE, item.getTableName());
                String fileName = templateItem.getFileName().replaceAll($_ENTITY, item.getBeanName())
                        .replaceAll($_TABLE, item.getTableName());
                String templateFile = templateItem.getTemplateFile().replaceAll($_ENTITY, item.getBeanName())
                        .replaceAll($_TABLE, item.getTableName());
                String path = StringUtil.pathConvert(templatePackage);
                JavaFileUtil.createPath(path);
                TemplateManager.createXml(item, templateFile, fileName);
            }
        }
    }

    public void generate(String tableName, String BeanName) throws Exception {

        // 加载数据库配置
        // 加载表信息
        TableContext.loadTable();
        // 模板生成文件
        for (TableItem item : TableContext.TABLES) {
            // 文件生成
            System.out.println(item);
        }
    }

}
