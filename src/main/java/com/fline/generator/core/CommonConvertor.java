package com.fline.generator.core;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
