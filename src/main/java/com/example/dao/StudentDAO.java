package com.example.dao;

import com.example.entity.Student;

import java.util.List;

public interface StudentDAO {
    void AddStudent(Student student);
    void DeleteStudent(int id);
    List<Student> getAllStudents();
    Student getStudentById(int id);
}
