package com.example.dao;

import com.example.entity.Teacher;

import java.util.List;

public interface TeacherDAO {
    public Teacher login(String username, String password);
    public void AddTeacher(Teacher teacher);
    public void DeleteTeacher(int id);
    public List<Teacher> getAllTeachers();
    public Teacher getTeacherById(int id);
    void UpdatePassword(int id, String newPassword);
}
