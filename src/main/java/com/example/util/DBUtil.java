package com.example.util;


import java.sql.*;

public class DBUtil {
    //将加载区块的方法放在静态代码块中
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static String URL="jdbc:mysql://localhost:3306/bs_xk?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
    public static String USERNAME="root";
    public static String PASSWORD="ZLPzh123";
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
    public static ResultSet executeQuery(String sql,Object[] params) {
        Connection conn = getconn();
        PreparedStatement ps=null;
        ResultSet resultSet = null;

        try {
            // 加载数据库驱动（如果需要）
            // Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            ps=conn.prepareStatement(sql);
            //调用通用的设置参数的方法 向sql语句中设置参数
            setParam(ps,params);

            // 执行查询操作
            resultSet = ps.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭资源
            closeAll(null,ps,conn);
        }

        return resultSet;
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


