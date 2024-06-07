package com.example.dao;

import com.example.entity.Student;

import java.util.List;

public interface StudentDAO {
    public Student login(String username, String password);
    void AddStudent(Student student);
    void DeleteStudent(int id);
    List<Student> getAllStudents();
    Student getStudentById(int id);
    void UpdatePassword(int id, String newPassword);
}
