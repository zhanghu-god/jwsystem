package com.example.dao.daoimpl;

import com.example.dao.StudentDAO;
import com.example.entity.Student;
import com.example.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;



public class StudentDAOimpl implements StudentDAO {
    private DBUtil dbUtil;
    private static class ResultException extends Exception {
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

        ResultSet rs = DBUtil.executeQuery(sql, params);
        try {
            while(rs.next()){
                //获取数据
                int userID = rs.getInt("id");
                //封装对象
                return new Student(userID,username,password);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void AddStudent(Student student) {
        try {
            String sql_find = "SELECT * FROM student WHERE userID = ?";
            Object[] params_find = new Object[]{student.getId()};
            ResultSet resultSet = dbUtil.executeQuery(sql_find, params_find);
            if(resultSet.next()) {
                // 用户已存在
                throw new ResultException("User already exists");
            } else {
                String sql = "INSERT INTO student (userID, userName, password) VALUES (?, ?, ?)";
                Object[]params = new Object[]{student.getId(), student.getUsername(), student.getPassword()};
                dbUtil.update(sql, params);
            }
        } catch(RuntimeException | ResultException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void DeleteStudent(int id) {
        try {
            String sql_find = "SELECT * FROM student WHERE userID = ?";
            Object[] params_find = new Object[]{id};
            ResultSet resultSet = dbUtil.executeQuery(sql_find, params_find);
            if(!resultSet.next()) {
                // 用户不存在
                throw new ResultException("User not exists");
            } else {
                String sql = "DELETE FROM student WHERE userID = ?";
                Object[]params = new Object[]{id};
                dbUtil.update(sql, params);
            }
        } catch(RuntimeException | ResultException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        LinkedList<Student> students = new LinkedList<>();
        Student student = new Student(10000, "name", "10000" );
        try {
            String sql = "SELECT * FROM student";
            Object[] params = new Object[]{};
            ResultSet resultSet = dbUtil.executeQuery(sql, params);
            if (!resultSet.next()) {
                throw new ResultException("Student not found");
            } else {
                do {
                    student.setUsername(resultSet.getString("userName"));
                    student.setId(resultSet.getInt("userID"));
                    student.setPassword(resultSet.getString("password"));
                    students.add(student);
                } while(resultSet.next());
                return students;
            }
        } catch (SQLException | ResultException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Student getStudentById(int id) {
        Student student = new Student(id, "name", "10000" );
        try {
            String sql = "SELECT * FROM student WHERE userID = ?";
            Object[] params = new Object[]{id};
            ResultSet resultSet = dbUtil.executeQuery(sql, params);
            if (!resultSet.next()) {
                throw new ResultException("Student not found");
            } else if (resultSet.next()) {
                throw new ResultException("Duplicate student ID");
            } else {
                student.setUsername(resultSet.getString("userName"));
                student.setId(resultSet.getInt("userID"));
                student.setPassword(resultSet.getString("password"));
                return student;
            }
        } catch (SQLException | ResultException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void UpdatePassword(int id, String newPassword) {
        try {
            String sql_find = "SELECT * FROM student WHERE userID = ?";
            Object[] params_find = new Object[] {id} ;
            ResultSet resultSet = dbUtil.executeQuery(sql_find, params_find);
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
        }
    }
}
