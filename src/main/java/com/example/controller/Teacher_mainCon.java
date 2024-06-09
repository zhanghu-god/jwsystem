package com.example.controller;

import com.example.dao.StudentDAO;
import com.example.dao.TeacherDAO;
import com.example.dao.daoimpl.StudentDAOimpl;
import com.example.dao.daoimpl.TeacherDAOimpl;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.entity.Userid;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class Teacher_mainCon {
    @FXML
    private Label tcname;

    public void initialize(){
        int id = Userid.id;
        TeacherDAO teacherDAO = new TeacherDAOimpl();
        Teacher teacher = teacherDAO.getTeacherById(id);
        tcname.setText(teacher.getUsername());
    }
}
