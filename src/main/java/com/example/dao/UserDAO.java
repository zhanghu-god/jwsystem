package com.example.dao.daoimpl;

import com.example.dao.UserDAO;
import com.example.entity.User;
import com.example.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDAOimpl implements UserDAO {
    private class ResultException extends Exception {
        public ResultException(String message) {
            super(message);
        }
    }

    public UserDAOimpl() {
    }

    @Override
    public void AddUser(User user) {
        try {
            String sql_find = "SELECT * FROM user WHERE userID = ?";
            Object[] params_find = {user.getId()};
            ResultSet resultSet = DBUtil.executeQuery(sql_find, params_find);
            if(resultSet.next()) {
                // 用户已存在
                throw new UserDAOimpl.ResultException("User already exists");
            } else {
                String sql = "INSERT INTO user (userID, userName, password) VALUES (?, ?, ?)";
                Object[] params = new Object[]{user.getId(), user.getUsername(), user.getPassword()};
                DBUtil.update(sql, params);
            }
        } catch(RuntimeException | UserDAOimpl.ResultException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void DeleteUser(int id) {
        try {
            String sql_find = "SELECT * FROM user WHERE userID = ?";
            Object[] params_find = new Object[]{id};
            ResultSet resultSet = DBUtil.executeQuery(sql_find, params_find);
            if(!resultSet.next()) {
                // 用户不存在
                throw new UserDAOimpl.ResultException("User not exists");
            } else {
                String sql = "DELETE FROM user WHERE userID = ?";
                Object[]params = new Object[]{id};
                DBUtil.update(sql, params);
            }
        } catch(RuntimeException | UserDAOimpl.ResultException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        LinkedList<User> users = new LinkedList<>();
        User user = new User(10000, "name", "10000");
        try {
            String sql = "SELECT * FROM user";
            Object[] params = new Object[]{};
            ResultSet resultSet = DBUtil.executeQuery(sql, params);
            if (!resultSet.next()) {
                throw new UserDAOimpl.ResultException("User not found");
            } else {
                do {
                    user.setUsername(resultSet.getString("userName"));
                    user.setId(resultSet.getInt("userID"));
                    user.setPassword(resultSet.getString("password"));
                    users.add(user);
                } while (resultSet.next());
                return users;
            }
        } catch (SQLException | UserDAOimpl.ResultException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getuserById(int id) {
        User user = new User(id, "name", "10000" );
        try {
            String sql = "SELECT * FROM user WHERE userID = ?";
            Object[] params = new Object[]{id};
            ResultSet resultSet = DBUtil.executeQuery(sql, params);
            if (!resultSet.next()) {
                throw new UserDAOimpl.ResultException("user not found");
            } else if (resultSet.next()) {
                throw new UserDAOimpl.ResultException("Duplicate user ID");
            } else {
                user.setUsername(resultSet.getString("userName"));
                user.setId(resultSet.getInt("userID"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException | UserDAOimpl.ResultException e) {
            throw new RuntimeException(e);
        }
    }
}
