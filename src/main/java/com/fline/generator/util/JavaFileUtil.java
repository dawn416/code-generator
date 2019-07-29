package com.fline.generator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fline.generator.GenerateException;

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
     * @throws IOException
     */
    public static void createJavaFile(String path, String content) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            throw new GenerateException("文件已存在");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path));) {
            bw.write(content);
        }
    }

    private JavaFileUtil() {
    }
}
