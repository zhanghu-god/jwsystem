package com.example.dao;

import com.example.entity.Student;

import java.util.List;

public interface StudentDAO {
    public Student login(String username, String password);
    public int AddStudent(Student student);
    public int DeleteStudent(int id);
    public List<Student> getAllStudents();
    public Student getStudentById(int id);
    public void UpdatePassword(int id, String newPassword);
}
