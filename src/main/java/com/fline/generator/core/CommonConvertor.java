package com.fline.generator.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @since 2019年7月27日 上午9:57:37
 */
public class CommonConvertor {

    public static final Map<String, String> map = new HashMap<>();

    public static String dbType2JavaType(String columnType) {
        return map.get(columnType);
    }

    private CommonConvertor() {
    }

}
