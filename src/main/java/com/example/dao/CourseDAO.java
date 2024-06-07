package com.example.dao;

import com.example.entity.Course;
import java.util.List;

public interface CourseDAO {
    public void addCourse(int courseID, String courseName, int teacherID, String courseTime, String classRoom);
    public void deleteCourse(int courseID);
    public Course getCourseByID(int courseID);
    public List<Course> getCourseByTeacherID(int teacherID);
    public List<Course> getAllCourses();
    public void setCourseName(int courseID, String courseName);
    public void setTeacherID(int courseID, int teacherID);
    public void setCourseTime(int courseID, String courseTime);
    public void setClassRoom(int courseID, String classRoom);
}
