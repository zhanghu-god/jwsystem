package com.example.controller;

import com.example.dao.TeacherDAO;
import com.example.dao.daoimpl.TeacherDAOimpl;
import com.example.entity.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class Admin_teainfoController {
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
    private TableColumn<Teacher, Integer> teaid;

    @FXML
    private TableColumn<Teacher, String> teaname;

    @FXML
    private TableView<Teacher> tableview;


    public void add(ActionEvent event) throws SQLException {
        Teacher teacher = new Teacher();

        TeacherDAO teacherDAO = new TeacherDAOimpl();

        teacher.setId(Integer.parseInt(inputid.getText()));
        teacher.setUsername(inputname.getText());
        teacher.setPassword(inputid.getText());

        if (teacherDAO.AddTeacher(teacher)==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("教师已存在!");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("成功添加教师信息!");
            alert.showAndWait();
            loadData();
        }
    }

    public void del(ActionEvent event) throws SQLException {
        TeacherDAO teacherDAO = new TeacherDAOimpl();
        if(teacherDAO.DeleteTeacher(Integer.parseInt(inputid.getText()))==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("教师ID不存在!");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("成功删除教师信息!");
            alert.showAndWait();
            loadData();
            return;
        }
    }

    public void refresh(ActionEvent event) throws SQLException {
        loadData();
    }
    
    ObservableList<Teacher> teacherList= FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        loadData();
    }
    public void loadData() throws SQLException {
        teacherList.clear();
        TeacherDAO teacherDAO = new TeacherDAOimpl();

        List<Teacher> teacherlist= teacherDAO.getAllTeachers();

        for(Teacher teacher:teacherlist){
            teacherList.add(teacher);
        }

        //teacherList = FXCollections.observableArrayList(teacherDAO.getAllTeachers());
        teaid.setCellValueFactory(new PropertyValueFactory<>("id"));
        teaname.setCellValueFactory(new PropertyValueFactory<>("username"));

        tableview.setItems(teacherList);
    }
}
