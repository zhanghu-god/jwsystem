package com.example.dao;

import com.example.entity.Teacher;

import java.util.List;

public interface TeacherDAO {
    public Teacher login(String username, String password);
    public int AddTeacher(Teacher teacher);
    public int DeleteTeacher(int id);
    public List<Teacher> getAllTeachers();
    public Teacher getTeacherById(int id);
    void UpdatePassword(int id, String newPassword);
}
