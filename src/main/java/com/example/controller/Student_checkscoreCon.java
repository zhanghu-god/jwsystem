package com.example.controller;

import com.example.entity.Userid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import com.example.entity.scoreList;
import javafx.scene.control.TableView;
import com.example.dao.daoimpl.selectedcourseDAOimpl;
import com.example.dao.daoimpl.CourseDAOimpl;
import com.example.entity.Course;
import com.example.entity.selectedcourse;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class Student_checkscoreCon {

    @FXML
    private TableColumn<?, ?> courseid;

    @FXML
    private TableColumn<?, ?> coursename;

    @FXML
    private TableColumn<?, ?> score;

    @FXML
    private TableView<scoreList> tableview;

    ObservableList<scoreList> scoreAll = FXCollections.observableArrayList();

    public void initialize() {
        loadData();
    }
    public void loadData() {
        scoreAll.clear();
        selectedcourseDAOimpl selectedcourseDAOimpl = new selectedcourseDAOimpl();
        CourseDAOimpl courseDAOimpl = new CourseDAOimpl();
        List<selectedcourse> selectedcourse = selectedcourseDAOimpl.getByStudentID(Userid.id);
        if(selectedcourse != null){
            for (selectedcourse s : selectedcourse) {
                scoreList scoreList = new scoreList();
                scoreList.setCourseID(s.getCourseID());
                Course course = courseDAOimpl.getCourseByID(s.getCourseID());
                scoreList.setCourseName(course.getCourseName());
                scoreList.setScore(s.getMark());
                scoreAll.add(scoreList);
            }
        }
        courseid.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tableview.setItems(scoreAll);
    }
}
