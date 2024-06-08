package com.example.dao;

import com.example.entity.Course;
import java.util.List;

public interface CourseDAO {
    public int addCourse(int courseID, String courseName, int teacherID, String courseTime, String classRoom);
    public int deleteCourse(int courseID);
    public Course getCourseByID(int courseID);
    public List<Course> getCourseByTeacherID(int teacherID);
    public List<Course> getAllCourses();
    public int setCourseName(int courseID, String courseName);
    public int setTeacherID(int courseID, int teacherID);
    public int setCourseTime(int courseID, String courseTime);
    public int setClassRoom(int courseID, String classRoom);
}
