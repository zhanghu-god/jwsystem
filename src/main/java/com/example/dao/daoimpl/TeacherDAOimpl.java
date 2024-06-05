package com.example.dao.daoimpl;

import com.example.dao.TeacherDAO;
import com.example.entity.Teacher;
import com.example.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TeacherDAOimpl implements TeacherDAO {
    private DBUtil dbUtil;
    private static class ResultException extends Exception {
        public ResultException(String message) {
            super(message);
        }
    }

    public TeacherDAOimpl() {
        this.dbUtil = new DBUtil();
    }

    @Override
    public Teacher login(String username, String password) {
        String sql="SELECT * FROM teacher WHERE userName=? AND password=?";
        Object[]params={username,password};

        ResultSet rs = DBUtil.executeQuery(sql, params);
        try {
            while(rs.next()){
                //获取数据
                int userID = rs.getInt("id");
                //封装对象
                return new Teacher(userID,username,password);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void AddTeacher(Teacher teacher) {
        try {
            String sql_find = "SELECT * FROM teacher WHERE userID = ?";
            Object[] params_find = new Object[]{teacher.getId()};
            ResultSet resultSet = dbUtil.executeQuery(sql_find, params_find);
            if(resultSet.next()) {
                // 用户已存在
                throw new ResultException("User already exists");
            } else {
                String sql = "INSERT INTO teacher (userID, userName, password) VALUES (?, ?, ?)";
                Object[]params = new Object[]{teacher.getId(), teacher.getUsername(), teacher.getPassword()};
                dbUtil.update(sql, params);
            }
        } catch(RuntimeException | ResultException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void DeleteTeacher(int id) {
        try {
            String sql_find = "SELECT * FROM teacher WHERE userID = ?";
            Object[] params_find = new Object[]{id};
            ResultSet resultSet = dbUtil.executeQuery(sql_find, params_find);
            if(!resultSet.next()) {
                // 用户不存在
                throw new ResultException("User not exists");
            } else {
                String sql = "DELETE FROM teacher WHERE userID = ?";
                Object[]params = new Object[]{id};
                dbUtil.update(sql, params);
            }
        } catch(RuntimeException | ResultException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        LinkedList<Teacher> teachers = new LinkedList<>();
        Teacher teacher = new Teacher(10000, "name", "10000" );
        try {
            String sql = "SELECT * FROM teacher";
            Object[] params = new Object[]{};
            ResultSet resultSet = dbUtil.executeQuery(sql, params);
            if (!resultSet.next()) {
                throw new ResultException("Teacher not found");
            } else {
                do {
                    teacher.setUsername(resultSet.getString("userName"));
                    teacher.setId(resultSet.getInt("userID"));
                    teacher.setPassword(resultSet.getString("password"));
                    teachers.add(teacher);
                } while(resultSet.next());
                return teachers;
            }
        } catch (SQLException | ResultException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Teacher getTeacherById(int id) {
        Teacher teacher = new Teacher(id, "name", "10000" );
        try {
            String sql = "SELECT * FROM teacher WHERE userID = ?";
            Object[] params = new Object[]{id};
            ResultSet resultSet = dbUtil.executeQuery(sql, params);
            if (!resultSet.next()) {
                throw new ResultException("Teacher not found");
            } else if (resultSet.next()) {
                throw new ResultException("Duplicate teacher ID");
            } else {
                teacher.setUsername(resultSet.getString("userName"));
                teacher.setId(resultSet.getInt("userID"));
                teacher.setPassword(resultSet.getString("password"));
                return teacher;
            }
        } catch (SQLException | ResultException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void UpdatePassword(int id, String newPassword) {
        try {
            String sql_find = "SELECT * FROM teacher WHERE userID = ?";
            Object[] params_find = new Object[] {id} ;
            ResultSet resultSet = dbUtil.executeQuery(sql_find, params_find);
            if(!resultSet.next()) {
                // 用户不存在
                throw new ResultException("User not found");
            } else if (resultSet.next()) {
                //重复ID
                throw new ResultException("Duplicate user ID");
            } else {
                String sql = "UPDATE teacher SET password = ? WHERE userID = ?";
                Object[]params = new Object[]{newPassword, id};
                dbUtil.update(sql, params);
            }
        } catch(RuntimeException | ResultException | SQLException e) {
            System.out.println(e);
        }
    }
}
