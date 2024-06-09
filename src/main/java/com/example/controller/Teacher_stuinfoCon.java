package com.example.controller;

import com.example.dao.daoimpl.CourseDAOimpl;
import com.example.dao.daoimpl.StudentDAOimpl;
import com.example.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import com.example.dao.daoimpl.selectedcourseDAOimpl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import javax.annotation.PostConstruct;
import java.util.List;

public class Teacher_stuinfoCon {

    @FXML
    private Button confirm;

    @FXML
    private TableColumn<?, ?> coursename;

    @FXML
    private TextField newmark;

    @FXML
    private TableColumn<?, ?> score;

    @FXML
    private TableColumn<?, ?> stuid;

    @FXML
    private TableColumn<?, ?> stuname;

    @FXML
    private TableView<scoreList> tableview;

    ObservableList<scoreList> sl = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        loadData();
        tableview.refresh();
        System.out.println("set");
    }
    public void loadData() {
        int ID = Userid.id;
        CourseDAOimpl courseDAOimpl = new CourseDAOimpl();
        selectedcourseDAOimpl selectedcourseDAOimpl = new selectedcourseDAOimpl();
        StudentDAOimpl studentDAOimpl = new StudentDAOimpl();
        List<Course> courselist = courseDAOimpl.getCourseByTeacherID(ID);
        if(courselist != null){
            for(Course course : courselist){
                scoreList score = new scoreList();
                score.setCourseName(course.getCourseName());
                List<selectedcourse> select = selectedcourseDAOimpl.getByCourseID(course.getCourseID());
                if(select != null){
                    for(selectedcourse s : select){
                        score.setScore(s.getMark());
                        score.setStudentID(s.getStudentID());
                        Student stu = studentDAOimpl.getStudentById(s.getStudentID());
                        score.setStudentName(stu.getUsername());
                        sl.add(score);
                    }
                }
            }
        }
        stuname.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        stuid.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tableview.setItems(sl);
    }
    @FXML
    void change(ActionEvent event) {

    }

}