package com.example.dao;

import com.example.entity.Teacher;

import java.util.List;

public interface TeacherDAO {
    public void AddTeacher(Teacher teacher);
    public void DeleteTeacher(int id);
    public List<Teacher> getAllTeachers();
    public Teacher getTeacherById(int id);
}
