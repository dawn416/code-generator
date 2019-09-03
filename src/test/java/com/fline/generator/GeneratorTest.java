/**
 * Copyright(C) 2019 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 */
package com.fline.generator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @since 2019年7月30日 上午8:48:24
 */
public class GeneratorTest {

    @Test
    public void testName() throws Exception {
        Map<String, Object> map = new HashMap<>();
        // map.put("excludeFields", Arrays.asList("id", "name"));
        Generator.generate("codeGenerator/config.xml", null);
    }
}
