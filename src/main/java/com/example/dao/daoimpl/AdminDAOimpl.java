package com.example.dao.daoimpl;

import com.example.dao.AdminDAO;
import com.example.entity.Admin;
import com.example.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOimpl implements AdminDAO {

    @Override
    public Admin login(String username, String password) {
        String sql="select * from admin where username=? and password=?";
        Object[]params={username,password};

        ResultSet rs = DBUtil.executeQuery(sql, params);
        try {
            while(rs.next()){
                //获取数据
                int userID = rs.getInt("id");
                //封装对象
                return new Admin(userID,username,password);
            }
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
        return null;
    }
}
