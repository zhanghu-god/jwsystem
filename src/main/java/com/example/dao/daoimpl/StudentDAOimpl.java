package com.example.dao.daoimpl;

import com.example.dao.StudentDAO;
import com.example.dao.UserDAO;
import com.example.entity.Student;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class StudentDAOimpl implements StudentDAO {
    private DBUtil dbUtil;
    static class ResultException extends Exception {
        public ResultException(String message) {
            super(message);
        }
    }

    public StudentDAOimpl() {
        this.dbUtil = new DBUtil();
    }

    @Override
    public Student login(String username, String password) {
        String sql="SELECT * FROM student WHERE userName=? AND password=?";
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
                return new Student(userID,username,password);
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
    public int AddStudent(Student student) {
        Connection  conn = dbUtil.getconn();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int conduct = 0;
        try {
            String sql_find = "SELECT * FROM student WHERE userID = ?";
            Object[] params_find = new Object[]{student.getId()};
            preparedStatement = conn.prepareStatement(sql_find);
            dbUtil.setParam(preparedStatement, params_find);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                // 用户已存在
                throw new ResultException("User already exists");
            } else {
                String sql = "INSERT INTO student (userID, userName, password) VALUES (?, ?, ?)";
                Object[]params = new Object[]{student.getId(), student.getUsername(), student.getPassword()};
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
    public int DeleteStudent(int id) {
        Connection conn = dbUtil.getconn();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int conduct = 0;
        try {
            String sql_find = "SELECT * FROM student WHERE userID = ?";
            Object[] params_find = new Object[]{id};
            preparedStatement = conn.prepareStatement(sql_find);
            dbUtil.setParam(preparedStatement, params_find);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                // 用户不存在
                throw new ResultException("User not exists");
            } else {
                String sql = "DELETE FROM student WHERE userID = ?";
                Object[]params = new Object[]{id};
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
    public List<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Connection conn = dbUtil.getconn();
        try {
            String sql = "SELECT * FROM student";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new ResultException("Student not found");
            } else {
                do {
                    Student student = new Student(10000, "name", "10000" );
                    student.setUsername(resultSet.getString("userName"));
                    student.setId(resultSet.getInt("userID"));
                    student.setPassword(resultSet.getString("password"));
                    students.add(student);
                } while(resultSet.next());
                return students;
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
    public Student getStudentById(int id) {
        Student student = new Student(id, "name", "10000" );
        Connection conn = dbUtil.getconn();
        try {
            String sql = "SELECT * FROM student WHERE userID = ?";
            Object[] params = new Object[]{id};
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            dbUtil.setParam(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new ResultException("Student not found");
            } else {
                student.setUsername(resultSet.getString("userName"));
                student.setId(resultSet.getInt("userID"));
                student.setPassword(resultSet.getString("password"));
                return student;
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
            String sql_find = "SELECT * FROM student WHERE userID = ?";
            Object[] params_find = new Object[] {id} ;
            PreparedStatement preparedStatement = conn.prepareStatement(sql_find);
            dbUtil.setParam(preparedStatement, params_find);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                // 用户不存在
                throw new ResultException("User not found");
            } else if (resultSet.next()) {
                //重复ID
                throw new ResultException("Duplicate user ID");
            } else {
                String sql = "UPDATE student SET password = ? WHERE userID = ?";
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
