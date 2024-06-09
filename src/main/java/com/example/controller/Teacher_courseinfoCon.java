package com.example.controller;

import com.example.dao.daoimpl.CourseDAOimpl;
import com.example.entity.Course;
import com.example.entity.Userid;
import com.example.entity.scoreList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class Teacher_courseinfoCon {

    @FXML
    private Button addbtn;

    @FXML
    private TableColumn<?, ?> classroom;

    @FXML
    private TableColumn<?, ?> courseid;

    @FXML
    private TableColumn<?, ?> coursename;

    @FXML
    private Button delbtn;

    @FXML
    private TextField inputclassroom;

    @FXML
    private TextField inputid;

    @FXML
    private TextField inputtime;

    @FXML
    private TextField inputname;

    @FXML
    private Button refreshbtn;

    @FXML
    private TableColumn<?, ?> time;

    @FXML
    private TableView<Course> tableview;

    public void initialize() {
        loadData();
    }

    private void loadData() {
        CourseDAOimpl courseDAOimpl = new CourseDAOimpl();
        List<Course> courses = courseDAOimpl.getCourseByTeacherID(Userid.id);
        ObservableList<Course> courses1 = FXCollections.observableArrayList(courses);
        classroom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
        courseid.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        time.setCellValueFactory(new PropertyValueFactory<>("courseTime"));
        tableview.setItems(courses1);
    }
    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void del(ActionEvent event) {

    }

    @FXML
    void refresh(ActionEvent event) {

    }

}