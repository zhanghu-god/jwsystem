package com.example.controller;

import com.example.dao.daoimpl.TeacherDAOimpl;
import com.example.entity.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.util.List;
import java.util.ArrayList;
import com.example.entity.Course;
import com.example.dao.daoimpl.CourseDAOimpl;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import com.example.dao.daoimpl.selectedcourseDAOimpl;
import com.example.entity.Course;
import com.example.dao.daoimpl.CourseDAOimpl;

public class Student_selectCon {

    @FXML
    private TableColumn<?, ?> classroom;

    @FXML
    private Button confirm;

    @FXML
    private Button drop;

    @FXML
    private Button refresh;

    @FXML
    private TableColumn<?, ?> courseid;

    @FXML
    private TableColumn<?, ?> coursename;

    @FXML
    private TextField inputid;

    @FXML
    private TableColumn<?, ?> teachername;

    @FXML
    private TableColumn<?, ?> time;

    @FXML
    private TableView<Course> tableview;



    public void initialize() throws SQLException {
        loadData();
    }
    ObservableList<Course> courseList = FXCollections.observableArrayList();
    public void loadData() throws SQLException {
        courseList.clear();
        CourseDAOimpl courseDAOimpl = new CourseDAOimpl();
        List<Course> list = courseDAOimpl.getAllCourses();
        for (Course c : list) {
            courseList.add(c);
        }
        classroom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
        courseid.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        teachername.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        time.setCellValueFactory(new PropertyValueFactory<>("courseTime"));
        tableview.setItems(courseList);
    }
    @FXML
    void select(ActionEvent event) throws SQLException {
        int ID = (int)confirm.getScene().getWindow().getUserData();
        try{
        int courseID = Integer.parseInt(inputid.getText());
        List<Course> list = new ArrayList<>();
        int i = 0;
        CourseDAOimpl courseDAOimpl = new CourseDAOimpl();
        list = courseDAOimpl.getAllCourses();
        if(list == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("没有课程");
            alert.setContentText("请等开课再选");
            alert.showAndWait();
            return;
        }
        for(i = 0; i < list.size(); i++){
            if(list.get(i).getCourseID() == courseID){
                break;
            }
        }
        if(i < list.size()){
            selectedcourseDAOimpl selectedcourseDAOimpl = new selectedcourseDAOimpl();
            if(selectedcourseDAOimpl.addselectedcourse(courseID,ID) == 1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("选课成功");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("重复选课");
                alert.showAndWait();
            };
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("没有此课程");
            alert.showAndWait();
        }
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("请输入正确的课程编号");
            alert.showAndWait();
        }
        loadData();
    }

    @FXML
    void drop(ActionEvent event) throws SQLException {
        int ID = (int)confirm.getScene().getWindow().getUserData();
        try{
            int courseID = Integer.parseInt(inputid.getText());
            List<Course> list = new ArrayList<>();
            int i = 0;
            CourseDAOimpl courseDAOimpl = new CourseDAOimpl();
            list = courseDAOimpl.getAllCourses();
            if(list == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("没有课程");
                alert.showAndWait();
                return;
            }
            for(i = 0; i < list.size(); i++){
                if(list.get(i).getCourseID() == courseID){
                    break;
                }
            }
            if(i < list.size()){
                selectedcourseDAOimpl selectedcourseDAOimpl = new selectedcourseDAOimpl();
                if(selectedcourseDAOimpl.delete(courseID,ID) == 1){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("退课成功");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("未选择该课程");
                    alert.showAndWait();
                };
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("没有此课程");
                alert.showAndWait();
            }
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("请输入正确的课程编号");
            alert.showAndWait();
        }
        loadData();
    }

    @FXML
    public void refresh(ActionEvent actionEvent) throws SQLException {
        loadData();
    }
}
