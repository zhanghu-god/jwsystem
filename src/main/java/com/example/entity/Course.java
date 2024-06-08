package com.example.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Course {
    private int courseID;
    private String courseName;
    private int teacherID;
    private String courseTime;
    private String classRoom;
    public Course(){

    }

    public Course(int courseID, String courseName, int teacherID, String courseTime, String classRoom) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.teacherID = teacherID;
        this.courseTime = courseTime;
        this.classRoom = classRoom;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}