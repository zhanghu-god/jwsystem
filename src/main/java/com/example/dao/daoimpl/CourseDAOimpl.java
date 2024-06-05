package com.example.dao.daoimpl;

import com.example.entity.Course;
import com.example.dao.CourseDAO;
import com.example.util.DBUtil;
import java.sql.ResultSet;
import java.util.*;
import java.sql.SQLException;

public class CourseDAOimpl implements CourseDAO{
    private static class ResultException extends Exception {
        public ResultException(String message) {
            super(message);
        }
    }
    @Override
    public void addCourse(int courseID, String courseName, int teacherID, String courseTime, String classRoom) {
        String sql1 = "select * from course where courseID = ?";
        Object[] params1 = {courseID};
        ResultSet resultSet1 = DBUtil.executeQuery(sql1, params1);
        try {
            if (resultSet1.next()) {
                throw new ResultException("课程ID已存在");
            }else{
                String sql = "insert into course values(?, ?, ?, ?, ?)";
                Object[] params = {courseID, courseName, teacherID, courseTime, classRoom};
                DBUtil.update(sql, params);
                System.out.println("添加成功");
            }
        }catch(SQLException | ResultException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCourse(int courseID){
        String sql1 = "select * from course where courseID = ?";
        Object[] params1 = {courseID};
        ResultSet resultSet = DBUtil.executeQuery(sql1, params1);
        try {
            if (!resultSet.next()) {
                throw new ResultException("课程不存在");
            }
            else {
                String sql = "delete from course where courseID = ?";
                Object[] params = {courseID};
                DBUtil.update(sql, params);
                System.out.println("删除成功");
                }
            }
        catch (SQLException | ResultException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Course getCourseByID(int courseID){
        String sql = "select * from course where courseID = ?";
        Object[] params = {courseID};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        try {
            if (!resultSet.next())
                throw new ResultException("课程不存在");
            else {
                return Course.createCourse(resultSet);
            }
        }
        catch (SQLException | ResultException e){
            System.out.println(e.getMessage());
            }
        return null;
    }
    @Override
    public Course getCourseByName(String courseName){
        String sql = "select * from course where courseName = ?";
        Object[] params = {courseName};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        try{
            if (!resultSet.next()){
                throw new ResultException("课程不存在");
            }
            else{
             return Course.createCourse(resultSet);
            }
        }
        catch (SQLException | ResultException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public Course getCourseByTeacherID(int teacherID){
        String sql = "select * from course where teacherID = ?";
        Object[] params = {teacherID};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        try{
            if (!resultSet.next()){
                throw new ResultException("课程不存在");
            }
            else{
            return Course.createCourse(resultSet);
            }
        }
        catch (SQLException | ResultException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public List<Course> getAllCourses(){
        String sql = "select * from course";
        Object[] params = {};
        List<Course> courseList = new ArrayList<>();
        try{
            ResultSet resultSet = DBUtil.executeQuery(sql, params);
            if(!resultSet.next()){
                throw new ResultException("没有课程");
            }
            else{
                do{
                Course course = Course.createCourse(resultSet);
                courseList.add(course);
                }while (resultSet.next());
                return courseList;
            }
        }
        catch (SQLException | ResultException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public void setCourseName(int courseID, String courseName){
        String sql1 ="select * from course where courseID = ?";
        Object[] params1 = {courseID};
        ResultSet resultSet1 = DBUtil.executeQuery(sql1, params1);
        try{
            if (!resultSet1.next()){
                throw new ResultException("课程不存在");
            }
            else{
                String sql2 = "select * from course where courseName = ?";
                Object[] params2 = {courseName};
                ResultSet resultSet2 = DBUtil.executeQuery(sql2, params2);
                if (resultSet2.next()){
                    throw new ResultException("课程已存在");
                }
                else{
                    String sql = "update course set courseName = ? where courseID = ?";
                    Object[] params = {courseName, courseID};
                    DBUtil.update(sql, params);
                }
            }
        }
        catch (SQLException | ResultException e) {
            System.out.println(e.getMessage());
            }
        }
    @Override
    public void setTeacherID(int courseID, int teacherID){
        String sql1 = "select * from course where courseID = ?";
        Object[] params1 = {courseID};
        ResultSet resultSet1 = DBUtil.executeQuery(sql1, params1);
        try{
            if (!resultSet1.next()){
                throw new ResultException("课程不存在");
            }
            else{
                String sql = "update course set teacherID = ? where courseID = ?";
                Object[] params = {teacherID, courseID};
                DBUtil.update(sql, params);
            }
        }
        catch (SQLException | ResultException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void setCourseTime(int courseID, String courseTime){
        String sql1 = "select * from course where courseID = ?";
        Object[] params1 = {courseID};
        ResultSet resultSet1 = DBUtil.executeQuery(sql1, params1);
        try{
            if (!resultSet1.next()){
                throw new ResultException("课程不存在");
            }
            else{
                String sql = "update course set courseTime = ? where courseID = ?";
                Object[] params = {courseTime, courseID};
                DBUtil.update(sql, params);
            }
        }
        catch (SQLException | ResultException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void setClassRoom(int courseID, String classRoom){
        String sql1 = "select * from course where courseID = ?";
        Object[] params1 = {courseID};
        ResultSet resultSet1 = DBUtil.executeQuery(sql1, params1);
        try{
            if (!resultSet1.next()){
                throw new ResultException("课程不存在");
            }
            else{
                String sql = "update course set classRoom = ? where courseID = ?";
                Object[] params = {classRoom, courseID};
                DBUtil.update(sql, params);
            }
        }
        catch (SQLException | ResultException e){
            System.out.println(e.getMessage());
        }
    }
}
