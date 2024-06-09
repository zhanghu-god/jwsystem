package com.example.controller;

import com.example.dao.StudentDAO;
import com.example.dao.daoimpl.StudentDAOimpl;
import com.example.entity.Student;
import com.example.entity.Userid;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Student_mainCon {
    @FXML
    private Label stuname;

    public void initialize(){
        //修改stuname为当前用户的名字
        int id = Userid.id;
        StudentDAO studentDAO = new StudentDAOimpl();
        Student student = studentDAO.getStudentById(id);
        stuname.setText(student.getUsername());
    }
}
