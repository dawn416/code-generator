package com.fline.generator;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import freemarker.template.TemplateException;

/**
 * @since 2017年12月6日 下午2:04:18
 * @version 1.0.0
 * @author 黎明
 */
public class Generator {
    private static final Logger LOG = LoggerFactory.getLogger(Generator.class);

    private static final String VAR_ENTITY = "${entity}";
    private static final String VAR_TABLE = "${table}";
    private static final String VAR_MODULE = "${module}";
    public static GeneratorConfig generatorConfig;
    protected static Map<String, Object> customParams;

    public static void generate(String configFile, Map<String, Object> param) throws IOException, TemplateException {
        customParams = param;
        try (InputStream resourceAsStream = Generator.class.getClassLoader().getResourceAsStream(configFile);) {
            XStream xstream = new XStream();
            xstream.autodetectAnnotations(true);
            xstream.alias("generator", GeneratorConfig.class);
            generatorConfig = (GeneratorConfig) xstream.fromXML(resourceAsStream);
        } catch (IOException e1) {
            LOG.error("读取配置文件出错");
            throw e1;
        }
        LOG.debug("{}", generatorConfig);
        for (TypeConvert typeconcert : generatorConfig.getTypeConvertList()) {
            CommonConvertor.map.put(typeconcert.getJdbc(), typeconcert.getJava());
        }
        TableContext.loadTable();
		// Map<String, Object> dataMap = new HashMap<>();
		// if (Generator.customParams != null) {
		// dataMap.put("customItem", Generator.customParams);
		// }
		// for (TableItem item : TableContext.TABLES) {
		// refactor(dataMap, item);
		// }
    }

    /**
     * @since 2019年7月29日 下午3:13:47
     * @param dataMap
     * @param item
     * @throws IOException
     * @throws TemplateException
     */
    private static void refactor(Map<String, Object> dataMap, TableItem item) throws IOException, TemplateException {
        LOG.debug("{}", item);
        Map<String, Object> templateItemMap = new HashMap<>();
        for (TemplateItem templateItem : generatorConfig.getTemplateList()) {
            String targetPackage = templateItem.getTargetPackage().replace(VAR_ENTITY, item.getBeanName())
                    .replace(VAR_TABLE, item.getTableName())
                    .replace(VAR_MODULE, generatorConfig.getJdbcInfo().getModule());
            String targetFileName = templateItem.getTargetFileName().replace(VAR_ENTITY, item.getBeanName())
                    .replace(VAR_TABLE, item.getTableName())
                    .replace(VAR_MODULE, generatorConfig.getJdbcInfo().getModule());
            String templateFile = templateItem.getTemplateFile().replace(VAR_ENTITY, item.getBeanName())
                    .replace(VAR_TABLE, item.getTableName())
                    .replace(VAR_MODULE, generatorConfig.getJdbcInfo().getModule());
            templateItem.setTargetPackage(targetPackage);
            templateItem.setTargetFileName(targetFileName);
            templateItem.setTemplateFile(templateFile);
            templateItemMap.put(templateItem.getId(), templateItem);
        }
        dataMap.put("templateItem", templateItemMap);
        for (TemplateItem templateItem : generatorConfig.getTemplateList()) {
            try {
                String path = StringUtil
                        .pathConvert(templateItem.getTargetProject() + "." + templateItem.getTargetPackage());
                JavaFileUtil.createPath(path);
                dataMap.put("tableItem", item);
                LOG.debug("{}", item);
                TemplateManager.createXml(templateItem.getTemplateFile(), path, templateItem.getTargetFileName(),
                        dataMap);
            } catch (CatchGenerateException e) {
                LOG.error(e.getMessage());
            }
        }
    }

    private Generator() {
    }
}
