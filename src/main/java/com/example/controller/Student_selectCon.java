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
import com.example.entity.courseList;
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
    private TableView<courseList> tableview;

    ObservableList<courseList> courseList1 = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        loadData();
    }
    public void loadData() throws SQLException {
        CourseDAOimpl courseDAOimpl = new CourseDAOimpl();
        List<Course> list = courseDAOimpl.getAllCourses();
        if(list != null) {
            for (int i = 0; i < list.size(); i++) {
                TeacherDAOimpl teacherDAOimpl = new TeacherDAOimpl();
                courseList courseList0 = new courseList();
                courseList0.setCourseID(list.get(i).getCourseID());
                courseList0.setCourseName(list.get(i).getCourseName());
                courseList0.setClassRoom(list.get(i).getClassRoom());
                courseList0.setCourseTime(list.get(i).getCourseTime());
                Teacher teacher = teacherDAOimpl.getTeacherById(list.get(i).getTeacherID());
                courseList0.setTeacherName(teacher.getUsername());
                courseList1.add(courseList0);
            }
            classroom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
            courseid.setCellValueFactory(new PropertyValueFactory<>("courseID"));
            coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
            teachername.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
            time.setCellValueFactory(new PropertyValueFactory<>("courseTime"));
            tableview.setItems(courseList1);
        }
    }
    @FXML
    void select(ActionEvent event) {
        int ID = (int)confirm.getScene().getWindow().getUserData();
        try{
        int courseID = Integer.parseInt(inputid.getText());
        List<Course> list = new ArrayList<>();
        int i;
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
    }
}
