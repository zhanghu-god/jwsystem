package com.example.dao.daoimpl;

import com.example.dao.AdminDAO;
import com.example.entity.Admin;
import com.example.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
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

        ResultSet rs = DBUtil.executeQuery(sql, params);
        try {
            while(rs.next()){
                //获取数据
                int userID = rs.getInt("id");
                //封装对象
                return new Admin(userID,username,password);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void AddAdmin(Admin admin) {
        try {
            String sql_find = "SELECT * FROM admin WHERE userID = ?";
            Object[] params_find = new Object[]{admin.getId()};
            ResultSet resultSet = dbUtil.executeQuery(sql_find, params_find);
            if(resultSet.next()) {
                // 用户已存在
                throw new ResultException("User already exists");
            } else {
                String sql = "INSERT INTO admin (userID, userName, password) VALUES (?, ?, ?)";
                Object[]params = new Object[]{admin.getId(), admin.getUsername(), admin.getPassword()};
                dbUtil.update(sql, params);
            }
        } catch(RuntimeException | AdminDAOimpl.ResultException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void DeleteAdmin(int id) {
        try {
            String sql_find = "SELECT * FROM admin WHERE userID = ?";
            Object[] params_find = new Object[]{id};
            ResultSet resultSet = dbUtil.executeQuery(sql_find, params_find);
            if(!resultSet.next()) {
                // 用户不存在
                throw new ResultException("User not exists");
            } else {
                String sql = "DELETE FROM admin WHERE userID = ?";
                Object[]params = new Object[]{id};
                dbUtil.update(sql, params);
            }
        } catch(RuntimeException | AdminDAOimpl.ResultException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        LinkedList<Admin> admins = new LinkedList<>();
        Admin admin = new Admin(10000, "name", "10000" );
        try {
            String sql = "SELECT * FROM admin";
            Object[] params = new Object[]{};
            ResultSet resultSet = dbUtil.executeQuery(sql, params);
            if (!resultSet.next()) {
                throw new ResultException("Admin not found");
            } else {
                do {
                    admin.setUsername(resultSet.getString("userName"));
                    admin.setId(resultSet.getInt("userID"));
                    admin.setPassword(resultSet.getString("password"));
                    admins.add(admin);
                } while(resultSet.next());
                return admins;
            }
        } catch (SQLException | AdminDAOimpl.ResultException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Admin getAdminById(int id) {
        Admin admin = new Admin(id, "name", "10000" );
        try {
            String sql = "SELECT * FROM admin WHERE userID = ?";
            Object[] params = new Object[]{id};
            ResultSet resultSet = dbUtil.executeQuery(sql, params);
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
        } catch (SQLException | AdminDAOimpl.ResultException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void UpdatePassword(int id, String newPassword) {
        try {
            String sql_find = "SELECT * FROM admin WHERE userID = ?";
            Object[] params_find = new Object[] {id} ;
            ResultSet resultSet = dbUtil.executeQuery(sql_find, params_find);
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
        }
    }
}
