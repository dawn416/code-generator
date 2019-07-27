/**
 * Copyright(C) 2017 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @since 2017年12月7日 上午11:48:58
 * @version 1.0.0
 * @author 黎明
 *
 */
public class JavaFileUtil {

    /**
     * 根据包名创建目录
     */
    public static void createPath(String packagePath) {
        File filePath = new File(packagePath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
    }

    /**
     * 生成文件
     * 
     * @param path
     * @param content
     */
    public static void createJavaFile(String path, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path));) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JavaFileUtil() {
    }
}
