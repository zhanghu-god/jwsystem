package com.example.controller;

import ch.qos.logback.core.joran.event.BodyEvent;
import com.example.dao.CourseDAO;
import com.example.dao.TeacherDAO;
import com.example.dao.daoimpl.CourseDAOimpl;
import com.example.dao.daoimpl.TeacherDAOimpl;
import com.example.dao.daoimpl.selectedcourseDAOimpl;
import com.example.dao.selectedcourseDAO;
import com.example.entity.Course;
import com.example.entity.Teacher;
import com.example.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Admin_couinfoController {
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
    private TextField inputcouid;

    @FXML
    private TextField inputcouname;

    @FXML
    private TextField inputtcid;

    @FXML
    private TextField inputtcname;

    @FXML
    private TextField inputtime;

    @FXML
    private Button refreshbtn;

    @FXML
    private TableColumn<?, ?> time;

    @FXML
    private TableView<Course> tableview;

    @FXML
    private TableColumn<Course, Integer> tcid;

    @FXML
    private TableColumn<?, ?> tcname;

    @FXML
    void add(ActionEvent event) throws SQLException {
        Course course = new Course();
        Teacher teacher = new Teacher();
        //校验教师信息
        TeacherDAO teacherDAO = new TeacherDAOimpl();
        teacher = teacherDAO.getTeacherById(Integer.parseInt(inputtcid.getText()));
        if (teacher == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("教师不存在!");
            alert.showAndWait();
            return;
        }
        if (!teacher.getUsername().equals(inputtcname.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("教师用户名不匹配!");
            alert.showAndWait();
            return ;
        }
        course.setCourseID(Integer.parseInt(inputcouid.getText()));
        course.setCourseName(inputcouname.getText());
        course.setClassRoom(inputclassroom.getText());
        course.setTeacherID(Integer.parseInt(inputtcid.getText()));
        course.setCourseTime(inputtime.getText());

        CourseDAO courseDAO = new CourseDAOimpl();
        courseDAO.addCourse(course.getCourseID(), course.getCourseName(),
                course.getTeacherID(),course.getCourseTime(),course.getClassRoom());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText("课程添加成功!");
        alert.showAndWait();

        loadData();
    }


    @FXML
    void del(ActionEvent event) throws SQLException {
        CourseDAO courseDAO = new CourseDAOimpl();
        if(courseDAO.deleteCourse(Integer.parseInt(inputcouid.getText()))==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Course ID does not exist!");
            alert.showAndWait();
            return;
        }

        //删除选课信息
        selectedcourseDAO selectedcourseDAO = new selectedcourseDAOimpl();
        selectedcourseDAO.delete(Integer.parseInt(inputcouid.getText()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Delete successfully!");
        alert.showAndWait();

        loadData();
    }

    @FXML
    void refresh(ActionEvent event) throws SQLException {
        loadData();

    }

    ObservableList<Course> courseList= FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        loadData();
    }
    public void loadData() throws SQLException {
        courseList.clear();
        CourseDAO courseDAO = new CourseDAOimpl();
        TeacherDAO teacherDAO = new TeacherDAOimpl();

        List<Course> courselist= courseDAO.getAllCourses();

        for(Course course:courselist){
            Teacher teacher = teacherDAO.getTeacherById(course.getTeacherID());
            course.setTeacherName(teacher.getUsername());
            courseList.add(course);
        }

        //courseList = FXCollections.observableArrayList(courseDAO.getAllCourses());
        courseid.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        classroom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
        tcid.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        tcname.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        time.setCellValueFactory(new PropertyValueFactory<>("courseTime"));

        tableview.setItems(courseList);
    }

}


   /* private <__TMP__> __TMP__ loadData() {
        return null;
    }*/