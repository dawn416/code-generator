package com.fline.generator.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fline.generator.Generator;
import com.fline.generator.bean.GeneratorConfig;

/**
 * 
 * @since 2019年7月27日 上午9:57:37
 */
public class CommonConvertor {

    private static final Logger LOG = LoggerFactory.getLogger(CommonConvertor.class);

    public static final Map<String, String> map = new HashMap<>();

    public static String dbType2JavaType(String columnType) {
        return map.get(columnType);
    }

    private CommonConvertor() {
    }

    public static void main(String[] args) throws JAXBException, IOException {
        try (InputStream resourceAsStream = Generator.class.getClassLoader()
                .getResourceAsStream("codeGenerator/config.xml");) {
            JAXBContext context = JAXBContext.newInstance(GeneratorConfig.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            GeneratorConfig unmarshal = (GeneratorConfig) unmarshaller.unmarshal(resourceAsStream);
            LOG.debug("{}", unmarshal);
        }

    }
}
