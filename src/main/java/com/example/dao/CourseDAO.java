package com.example.dao;

import com.example.entity.Course;
import java.util.List;

public interface CourseDAO {
    public void addCourse(int courseId, String courseName, int teacherID, String courseTime, String classRoom);
    public void deleteCourse(int courseId);
    public Course getCourseByID(int courseId);
    public Course getCourseByName(String courseName);
    public Course getCourseByTeacherID(int teacherID);
    public List<Course> getAllCourses();
    public void setCourseName(int courseId, String courseName);
    public void setTeacherID(int courseId, int teacherID);
    public void setCourseTime(int courseId, String courseTime);
    public void setClassRoom(int courseId, String classRoom);
}
