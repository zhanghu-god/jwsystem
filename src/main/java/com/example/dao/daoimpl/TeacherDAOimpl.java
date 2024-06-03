package com.example.dao.daoimpl;

import com.example.dao.TeacherDAO;
import com.example.dao.UserDAO;
import com.example.entity.Teacher;
import com.example.util.DBUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.example.util.DBUtil.getconn;
import static com.example.util.DBUtil.setParam;

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
        Connection conn = dbUtil.getconn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            dbUtil.setParam(preparedStatement, params);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                //获取数据
                int userID = rs.getInt("id");
                //封装对象
                return new Teacher(userID,username,password);
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
    public int AddTeacher(Teacher teacher) {
        Connection conn = dbUtil.getconn();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int conduct = 0;
        try {
            String sql_find = "SELECT * FROM teacher WHERE userID = ?";
            Object[] params_find = new Object[]{teacher.getId()};
            preparedStatement = conn.prepareStatement(sql_find);
            dbUtil.setParam(preparedStatement, params_find);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                // 用户已存在
                throw new ResultException("User already exists");
            } else {
                String sql = "INSERT INTO teacher (userID, userName, password) VALUES (?, ?, ?)";
                Object[]params = new Object[]{teacher.getId(), teacher.getUsername(), teacher.getPassword()};
                dbUtil.update(sql, params);
                conduct = 1;
            }

        } catch(RuntimeException | ResultException | SQLException e) {
            System.out.println(e);
        } finally {
            dbUtil.closeAll(resultSet, preparedStatement, conn);
            return conduct;
        }
    }

    @Override
    public int DeleteTeacher(int id) {
        Connection conn = dbUtil.getconn();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int conduct = 0;
        try {
            String sql_find = "SELECT * FROM teacher WHERE userID = ?";
            Object[] params_find = new Object[]{id};
            preparedStatement = conn.prepareStatement(sql_find);
            dbUtil.setParam(preparedStatement, params_find);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                // 用户不存在
                throw new ResultException("User not exists");
            } else {
                String sql = "DELETE FROM teacher WHERE userID = ?";
                Object[]params = new Object[]{id};
                dbUtil.update(sql, params);
                conduct = 1;
            }
        } catch(RuntimeException | ResultException | SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeAll(resultSet, preparedStatement, conn);
            return conduct;
        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        LinkedList<Teacher> teachers = new LinkedList<>();
        Connection conn = dbUtil.getconn();
        try {
            String sql = "SELECT * FROM teacher";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new ResultException("Teacher not found");
            } else {
                do {
                    Teacher teacher = new Teacher(10000, "name", "10000" );
                    teacher.setUsername(resultSet.getString("userName"));
                    teacher.setId(resultSet.getInt("userID"));
                    teacher.setPassword(resultSet.getString("password"));
                    teachers.add(teacher);
                } while(resultSet.next());
                return teachers;
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
    public Teacher getTeacherById(int id) {
        Teacher teacher = new Teacher(id, "name", "10000" );
        Connection conn = dbUtil.getconn();
        try {
            String sql = "SELECT * FROM teacher WHERE userID = ?";
            Object[] params = new Object[]{id};
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            dbUtil.setParam(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new ResultException("Teacher not found");
            } else {
                teacher.setUsername(resultSet.getString("userName"));
                teacher.setId(resultSet.getInt("userID"));
                teacher.setPassword(resultSet.getString("password"));
                return teacher;
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
        //user表更改密码
        UserDAO userDAO = new UserDAOimpl();
        userDAO.UpdatePassword(id, newPassword);
        Connection conn = dbUtil.getconn();
        try {
            String sql_find = "SELECT * FROM teacher WHERE userID = ?";
            Object[] params_find = new Object[] {id} ;
            PreparedStatement preparedStatement_find = conn.prepareStatement(sql_find);
            dbUtil.setParam(preparedStatement_find, params_find);
            ResultSet resultSet = preparedStatement_find.executeQuery();
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
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
