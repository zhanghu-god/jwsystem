package com.example.dao.daoimpl;

import com.example.dao.AdminDAO;
import com.example.entity.Admin;
import com.example.util.DBUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AdminDAOimpl implements AdminDAO {
    private DBUtil dbUtil;
    private static class ResultException extends Exception {
        public ResultException(String message) {
            super(message);
        }
    }

    public AdminDAOimpl() {
        this.dbUtil = new DBUtil();
    }
    
    @Override
    public Admin login(String username, String password) {
        String sql="select * from admin where username=? and password=?";
        Object[]params={username,password};
        Connection conn = dbUtil.getconn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            dbUtil.setParam(preparedStatement, params);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                //获取数据
                int userID = rs.getInt("id");
                //封装对象
                return new Admin(userID,username,password);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    @Override
    public void AddAdmin(Admin admin) {
        Connection conn = dbUtil.getconn();
        try {
            String sql_find = "SELECT * FROM admin WHERE userID = ?";
            Object[] params_find = new Object[]{admin.getId()};
            PreparedStatement preparedStatement = conn.prepareStatement(sql_find);
            dbUtil.setParam(preparedStatement,params_find);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                // 用户已存在
                throw new ResultException("User already exists");
            } else {
                String sql = "INSERT INTO admin (userID, userName, password) VALUES (?, ?, ?)";
                Object[]params = new Object[]{admin.getId(), admin.getUsername(), admin.getPassword()};
                dbUtil.update(sql, params);
            }
        } catch(RuntimeException | ResultException | SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void DeleteAdmin(int id) {
        Connection conn = dbUtil.getconn();
        try {
            String sql_find = "SELECT * FROM admin WHERE userID = ?";
            Object[] params_find = new Object[]{id};
            PreparedStatement preparedStatement = conn.prepareStatement(sql_find);
            dbUtil.setParam(preparedStatement,params_find);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                // 用户不存在
                throw new ResultException("User not exists");
            } else {
                String sql = "DELETE FROM admin WHERE userID = ?";
                Object[]params = new Object[]{id};
                dbUtil.update(sql, params);
            }
        } catch(RuntimeException | ResultException | SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public List<Admin> getAllAdmins() {
        LinkedList<Admin> admins = new LinkedList<>();
        Connection conn = dbUtil.getconn();
        try {
            String sql = "SELECT * FROM admin";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                throw new ResultException("Error executing query");
            }
            if (!resultSet.next()) {
                throw new ResultException("Admin not found");
            } else {
                do {
                    Admin admin = new Admin(10000, "name", "10000" );
                    admin.setUsername(resultSet.getString("userName"));
                    admin.setId(resultSet.getInt("userID"));
                    admin.setPassword(resultSet.getString("password"));
                    admins.add(admin);
                } while(resultSet.next());
                return admins;
            }
        } catch (SQLException | ResultException e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    @Override
    public Admin getAdminById(int id) {
        Admin admin = new Admin(id, "name", "10000" );
        Connection conn = dbUtil.getconn();
        try {
            String sql = "SELECT * FROM admin WHERE userID = ?";
            Object[] params = new Object[]{id};
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            dbUtil.setParam(preparedStatement,params);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new ResultException("Admin not found");
            } else if (resultSet.next()) {
                throw new ResultException("Duplicate admin ID");
            } else {
                admin.setUsername(resultSet.getString("userName"));
                admin.setId(resultSet.getInt("userID"));
                admin.setPassword(resultSet.getString("password"));
                return admin;
            }
        } catch (SQLException | ResultException e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    @Override
    public void UpdatePassword(int id, String newPassword) {
        Connection conn = dbUtil.getconn();
        try {
            String sql_find = "SELECT * FROM admin WHERE userID = ?";
            Object[] params_find = new Object[] {id} ;
            PreparedStatement preparedStatement = conn.prepareStatement(sql_find);
            dbUtil.setParam(preparedStatement,params_find);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                // 用户不存在
                throw new ResultException("User not found");
            } else if (resultSet.next()) {
                //重复ID
                throw new ResultException("Duplicate user ID");
            } else {
                String sql = "UPDATE admin SET password = ? WHERE userID = ?";
                Object[]params = new Object[]{newPassword, id};
                dbUtil.update(sql, params);
            }
        } catch(RuntimeException | ResultException | SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
