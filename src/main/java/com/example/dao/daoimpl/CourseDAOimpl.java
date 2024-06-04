package com.example.dao.daoimpl;

import com.example.entity.Course;
import com.example.dao.CourseDAO;
import com.example.util.DBUtil;
import java.sql.ResultSet;
import java.util.*;
import java.sql.SQLException;

public class CourseDAOimpl implements CourseDAO{

    @Override
    public void addCourse(int courseID, String courseName, int teacherID, String courseTime, String classRoom) {
        String sql = "insert into course values(?, ?, ?, ?, ?)";
        Object[] params = {courseID, courseName, teacherID, courseTime, classRoom};
        DBUtil.update(sql, params);
    }

    @Override
    public void deleteCourse(int courseID){
        String sql = "delete from course where courseID = ?";
        Object[] params = {courseID};
        DBUtil.update(sql, params);
    }
    @Override
    public Course getCourseByID(int courseID){
        String sql = "select * from course where courseID = ?";
        Object[] params = {courseID};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        Course course = Course.createCourse(resultSet);
        return course;
    }
    @Override
    public Course getCourseByName(String courseName){
        String sql = "select * from course where courseName = ?";
        Object[] params = {courseName};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        Course course = Course.createCourse(resultSet);
        return course;
    }
    @Override
    public Course getCourseByTeacherID(int teacherID){
        String sql = "select * from course where teacherID = ?";
        Object[] params = {teacherID};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        Course course = Course.createCourse(resultSet);
        return course;
    }
    @Override
    public List<Course> getAllCourses(){
        String sql = "select * from course";
        Object[] params = {};
        List<Course> courseList = new ArrayList<>();
        try{
            ResultSet resultSet = DBUtil.executeQuery(sql, params);
            while(resultSet.next()){
                Course course = Course.createCourse(resultSet);
                courseList.add(course);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return courseList;
    }
    @Override
    public void setCourseName(int courseID, String courseName){
        String sql = "update course set courseName = ? where courseID = ?";
        Object[] params = {courseName, courseID};
        DBUtil.update(sql, params);
    }
    @Override
    public void setTeacherID(int courseID, int teacherID){
        String sql = "update course set teacherID = ? where courseID = ?";
        Object[] params = {teacherID, courseID};
        DBUtil.update(sql, params);
    }
    @Override
    public void setCourseTime(int courseID, String courseTime){
        String sql = "update course set courseTime = ? where courseID = ?";
        Object[] params = {courseTime, courseID};
        DBUtil.update(sql, params);
    }
    @Override
    public void setClassRoom(int courseID, String classRoom){
        String sql = "update course set classRoom = ? where courseID = ?";
        Object[] params = {classRoom, courseID};
        DBUtil.update(sql, params);
    }
}
