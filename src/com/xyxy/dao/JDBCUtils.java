package com.xyxy.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//操作数据库的工具类
public class JDBCUtils {

    public static Connection getConnection() throws Exception {
        //1.读取配置文件
        //方式一:读取配置文件的方式一：使用ClassLoader,src路径下
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverclass = properties.getProperty("driverclass");

        //2.加载驱动
        Class.forName(driverclass);

        //3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
//        System.out.println(conn);
        return conn;

    }


    //关闭资源
    public static void closeResource(Connection conn, PreparedStatement ps,ResultSet rs){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //关闭资源
    public static void closeResource(Connection conn, PreparedStatement ps){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
