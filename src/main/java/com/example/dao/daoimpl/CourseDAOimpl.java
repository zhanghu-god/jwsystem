package com.example.dao.daoimpl;

import com.example.entity.Course;
import com.example.dao.CourseDAO;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public int addCourse(int courseID, String courseName, int teacherID, String courseTime, String classRoom) {
        String sql1 = "select * from course where courseID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        int conduct = 0;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql1);
            stmt.setObject(1, courseID);
            rs1 = stmt.executeQuery();
            if (rs1.next()) {
                throw new ResultException("课程ID已存在");
            }else{
                String sql = "insert into course values(?, ?, ?, ?, ?)";
                Object[] params = {courseID, courseName, teacherID, courseTime, classRoom};
                DBUtil.update(sql, params);
                //System.out.println("添加成功");
                conduct = 1;
            }
        }catch(SQLException | ResultException e){
            //System.out.println(e.getMessage());
        }finally {
            DBUtil.closeAll(rs1, stmt, conn);
            return conduct;
        }
    }

    @Override
    public int deleteCourse(int courseID){
        String sql1 = "select * from course where courseID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        int conduct = 0;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql1);
            stmt.setObject(1, courseID);
            rs1 = stmt.executeQuery();
            if (!rs1.next()) {
                throw new ResultException("课程不存在");
            }
            else {
                String sql = "delete from course where courseID = ?";
                Object[] params = {courseID};
                DBUtil.update(sql, params);
                //System.out.println("删除成功");
                conduct = 1;
                }
            }
        catch (SQLException | ResultException e){
            //System.out.println(e.getMessage());
        }finally {
            DBUtil.closeAll(rs1, stmt, conn);
            return conduct;
        }
    }
    @Override
    public Course getCourseByID(int courseID){
        String sql = "select * from course where courseID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, courseID);
            rs1 = stmt.executeQuery();
            if (!rs1.next()) {
                DBUtil.closeAll(rs1, stmt, conn);
                throw new ResultException("课程不存在");
            }
            else {
                Course course = new Course();
                course.setCourseID(rs1.getInt("courseID"));
                course.setCourseName(rs1.getString("courseName"));
                course.setTeacherID(rs1.getInt("teacherID"));
                course.setCourseTime(rs1.getString("courseTime"));
                course.setClassRoom(rs1.getString("classRoom"));
                DBUtil.closeAll(rs1, stmt, conn);
                return course;
            }
        }
        catch (SQLException | ResultException e){
            //System.out.println(e.getMessage());
            }
        return null;
    }
    @Override
    public List<Course> getCourseByTeacherID(int teacherID){
        String sql = "select * from course where teacherID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, teacherID);
            rs1 = stmt.executeQuery();
            if (!rs1.next()){
                throw new ResultException("课程不存在");
            }
            else{
                List<Course> courseList = new ArrayList<>();
                do{
                    Course course = new Course();
                    course.setCourseID(rs1.getInt("courseID"));
                    course.setCourseName(rs1.getString("courseName"));
                    course.setTeacherID(rs1.getInt("teacherID"));
                    course.setCourseTime(rs1.getString("courseTime"));
                    course.setClassRoom(rs1.getString("classRoom"));
                    courseList.add(course);
                }while (rs1.next());
                DBUtil.closeAll(rs1, stmt, conn);
                return courseList;
            }
        }
        catch (SQLException | ResultException e){
            //System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public List<Course> getAllCourses(){
        String sql = "select * from course";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql);
            rs1 = stmt.executeQuery();
            if(!rs1.next()){
                DBUtil.closeAll(rs1, stmt, conn);
                throw new ResultException("没有课程");
            }
            else{
                List<Course> courseList = new ArrayList<>();
                do{
                    Course course = new Course();
                    course.setCourseID(rs1.getInt("courseID"));
                    course.setCourseName(rs1.getString("courseName"));
                    course.setTeacherID(rs1.getInt("teacherID"));
                    course.setCourseTime(rs1.getString("courseTime"));
                    course.setClassRoom(rs1.getString("classRoom"));
                    courseList.add(course);
                }while (rs1.next());
                DBUtil.closeAll(rs1, stmt, conn);
                return courseList;
            }
        }
        catch (SQLException | ResultException e){
            //System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public int setCourseName(int courseID, String courseName){
        String sql1 ="select * from course where courseID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        int conduct = 0;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql1);
            stmt.setObject(1, courseID);
            rs1 = stmt.executeQuery();
            if (!rs1.next()){
                DBUtil.closeAll(rs1, stmt, conn);
                throw new ResultException("课程不存在");
            }
            else{
                DBUtil.closeAll(rs1, stmt, conn);
                String sql = "update course set courseName = ? where courseID = ?";
                Object[] params = {courseName, courseID};
                DBUtil.update(sql, params);
                conduct = 1;
                }
        }
        catch (SQLException | ResultException e) {
            //System.out.println(e.getMessage());
            }finally {
            return conduct;
        }
        }
    @Override
    public int setTeacherID(int courseID, int teacherID){
        String sql1 = "select * from course where courseID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        int conduct = 0;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql1);
            stmt.setObject(1, courseID);
            rs1 = stmt.executeQuery();
            if (!rs1.next()){
                DBUtil.closeAll(rs1, stmt, conn);
                throw new ResultException("课程不存在");
            }
            else{
                DBUtil.closeAll(rs1, stmt, conn);
                String sql = "update course set teacherID = ? where courseID = ?";
                Object[] params = {teacherID, courseID};
                DBUtil.update(sql, params);
                conduct = 1;
            }
        }
        catch (SQLException | ResultException e){
            //System.out.println(e.getMessage());
        }finally {
            return conduct;
        }
    }
    @Override
    public int setCourseTime(int courseID, String courseTime){
        String sql1 = "select * from course where courseID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        int conduct = 0;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql1);
            stmt.setObject(1, courseID);
            rs1 = stmt.executeQuery();
            if (!rs1.next()){
                DBUtil.closeAll(rs1, stmt, conn);
                throw new ResultException("课程不存在");
            }
            else{
                DBUtil.closeAll(rs1, stmt, conn);
                String sql = "update course set courseTime = ? where courseID = ?";
                Object[] params = {courseTime, courseID};
                DBUtil.update(sql, params);
                conduct = 1;
            }
        }
        catch (SQLException | ResultException e){
            //System.out.println(e.getMessage());
        }finally {
            return conduct;
        }
    }
    @Override
    public int setClassRoom(int courseID, String classRoom){
        String sql1 = "select * from course where courseID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        int conduct = 0;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql1);
            stmt.setObject(1, courseID);
            rs1 = stmt.executeQuery();
            if (!rs1.next()){
                DBUtil.closeAll(rs1, stmt, conn);
                throw new ResultException("课程不存在");
            }
            else{
                DBUtil.closeAll(rs1, stmt, conn);
                String sql = "update course set classRoom = ? where courseID = ?";
                Object[] params = {classRoom, courseID};
                DBUtil.update(sql, params);
                conduct = 1;
            }
        }
        catch (SQLException | ResultException e){
            //System.out.println(e.getMessage());
        }finally {
            return conduct;
        }
    }
}


