package com.example.controller;

import com.example.dao.StudentDAO;
import com.example.dao.daoimpl.StudentDAOimpl;
import com.example.dao.daoimpl.selectedcourseDAOimpl;
import com.example.dao.selectedcourseDAO;
import com.example.entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class Admin_stuinfoController {

    @FXML
    private Button addbtn;

    @FXML
    private Button delbtn;

    @FXML
    private TextField inputid;

    @FXML
    private TextField inputname;

    @FXML
    private TableColumn<?, ?> password;

    @FXML
    private Button refreshbtn;

    @FXML
    private TableColumn<Student, Integer> stuid;

    @FXML
    private TableColumn<Student, String> stuname;


    @FXML
    private TableView<Student> tableview;

    @FXML
    void add(ActionEvent event) throws SQLException {
        Student student = new Student();

        StudentDAO studentDAO = new StudentDAOimpl();

        student.setId(Integer.parseInt(inputid.getText()));
        student.setUsername(inputname.getText());
        student.setPassword(inputid.getText());

        if (studentDAO.AddStudent(student)==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("学生已存在!");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText("学生添加成功!");
            alert.showAndWait();
            loadData();
        }
    }

    @FXML
    void del(ActionEvent event) throws SQLException {
        StudentDAO studentDAO = new StudentDAOimpl();
        if(studentDAO.DeleteStudent(Integer.parseInt(inputid.getText()))==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("学生ID不存在!");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("成功删除学生信息!");
            alert.showAndWait();
            loadData();
        }
    }

    @FXML
    void refresh(ActionEvent event) throws SQLException {
        loadData();
    }

    ObservableList<Student> studentList= FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        loadData();
    }
    public void loadData() throws SQLException {
        studentList.clear();
        StudentDAO studentDAO = new StudentDAOimpl();

        List<Student> studentlist= studentDAO.getAllStudents();

        for(Student student:studentlist){
            studentList.add(student);
        }

        //studentList = FXCollections.observableArrayList(studentDAO.getAllStudents());
        stuid.setCellValueFactory(new PropertyValueFactory<>("id"));
        stuname.setCellValueFactory(new PropertyValueFactory<>("username"));

        tableview.setItems(studentList);
    }

}