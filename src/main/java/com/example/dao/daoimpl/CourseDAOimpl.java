package com.example.dao.daoimpl;

import com.example.entity.Course;
import com.example.dao.CourseDAO;
import com.example.util.DBUtil;
import java.sql.ResultSet;
import java.util.*;
import java.sql.SQLException;

public class CourseDAOimpl implements CourseDAO{

    @Override
    public void addCourse(int courseId, String courseName, int teacherID, String courseTime, String classRoom) {
        String sql = "insert into course values(?, ?, ?, ?, ?)";
        Object[] params = {courseId, courseName, teacherID, courseTime, classRoom};
        DBUtil.update(sql, params);
    }

    @Override
    public void deleteCourse(int courseId){
        String sql = "delete from course where courseId = ?";
        Object[] params = {courseId};
        DBUtil.update(sql, params);
    }
    @Override
    public Course getCourseByID(int courseId){
        String sql = "select * from course where courseId = ?";
        Object[] params = {courseId};
        Course course = new Course();
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        course = Course.createCourse(resultSet);
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
    public void setCourseName(int courseId, String courseName){
        String sql = "update course set courseName = ? where courseId = ?";
        Object[] params = {courseName, courseId};
        DBUtil.update(sql, params);
    }
    @Override
    public void setTeacherID(int courseId, int teacherID){
        String sql = "update course set teacherID = ? where courseId = ?";
        Object[] params = {teacherID, courseId};
        DBUtil.update(sql, params);
    }
    @Override
    public void setCourseTime(int courseId, String courseTime){
        String sql = "update course set courseTime = ? where courseId = ?";
        Object[] params = {courseTime, courseId};
        DBUtil.update(sql, params);
    }
    @Override
    public void setClassRoom(int courseId, String classRoom){
        String sql = "update course set classRoom = ? where courseId = ?";
        Object[] params = {classRoom, courseId};
        DBUtil.update(sql, params);
    }
}
