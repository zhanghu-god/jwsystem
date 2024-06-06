package com.example.util;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String DRIVER;
    //将加载区块的方法放在静态代码块中
    static {
        try {
            //创建Properties集合类。
            Properties pro = new Properties();
            //获取配置文件
            ClassLoader classLoader = DBUtil.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            assert res != null;
            String path = res.getPath();
            //加载文件
            pro.load(new FileReader(path));
            //赋值
            URL=pro.getProperty("url");
            PASSWORD=pro.getProperty("password");
            USERNAME=pro.getProperty("username");
            DRIVER=pro.getProperty("driver");
            Class.forName(DRIVER);
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    //封装连接
    public static Connection getconn(){
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    //通用的增删改的封装
    public static int update(String sql,Object[] params){
        //调用上边封装好的方法获取conn对象
        Connection conn = getconn();
        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement(sql);
            //调用通用的设置参数的方法 向sql语句中设置参数
            setParam(ps,params);
            //执行sql语句
            int i=ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            //调用通用的关闭方法
            closeAll(null,ps,conn);
        }
    }
    public static ResultSet executeQuery(String sql,Object... params) {
        Connection conn = getconn();
        PreparedStatement ps=null;

        try {
            // 加载数据库驱动（如果需要）
            // Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            ps=conn.prepareStatement(sql);
            //调用通用的设置参数的方法 向sql语句中设置参数
            setParam(ps,params);

            // 执行查询操作
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeAll(null,ps,conn);
        }

        return null;
    }
    //封装通用的设置参数的方法
    public static void setParam(PreparedStatement ps,Object... params){
        try {
            if(params!=null){
                for (int i = 0; i< params.length; i++) {
                    ps.setObject(i+1,params[i]);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //封装通用的关闭方法
    public static void closeAll(ResultSet set, PreparedStatement ps, Connection conn){
        try {
            if(set!=null) {
                set.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(ps!=null) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(conn!=null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


