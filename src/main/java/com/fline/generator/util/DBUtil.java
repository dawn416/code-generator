package com.fline.generator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fline.generator.Generator;

/**
 * 
 * <h1>DBManager</h1>
 * <h1>根据配置信息，维持连接对象的管理</h1>
 * 
 */
public class DBUtil {

    /**
     * 创建一个新的数据库连接
     * 
     * @return java.sql.Connection
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public static Connection createConn() throws SQLException, ClassNotFoundException {
        String driver = Generator.generatorConfig.getJdbcInfo().getDriver();
        String url = Generator.generatorConfig.getJdbcInfo().getUrl();
        String username = Generator.generatorConfig.getJdbcInfo().getUsername();
        String password = Generator.generatorConfig.getJdbcInfo().getPassword();
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取数据库连接失败");
            throw e;
        }
    }

    private DBUtil() {
    }

}
