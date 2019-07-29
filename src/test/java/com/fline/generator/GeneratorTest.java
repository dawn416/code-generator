package com.fline.generator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @since 2019年7月29日 下午5:16:30
 */
public class GeneratorTest {

    @Test
    public void test() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("excludeFields", Arrays.asList("id", "name", "code"));
        Generator.generate("codeGenerator/config.xml", map);
    }
}
