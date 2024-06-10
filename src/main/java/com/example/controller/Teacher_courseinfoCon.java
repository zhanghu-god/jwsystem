package com.example.controller;

import com.example.dao.CourseDAO;
import com.example.dao.TeacherDAO;
import com.example.dao.daoimpl.CourseDAOimpl;
import com.example.dao.daoimpl.TeacherDAOimpl;
import com.example.dao.daoimpl.selectedcourseDAOimpl;
import com.example.dao.selectedcourseDAO;
import com.example.entity.Course;
import com.example.entity.Teacher;
import com.example.entity.Userid;
import com.example.entity.scoreList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        Course course = new Course();
        Teacher teacher = new Teacher();
        //校验教师信息
        TeacherDAO teacherDAO = new TeacherDAOimpl();
        teacher = teacherDAO.getTeacherById(Userid.id);
        course.setCourseID(Integer.parseInt(inputid.getText()));
        course.setCourseName(inputname.getText());
        course.setClassRoom(inputclassroom.getText());
        course.setTeacherID(Userid.id);
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
    void del(ActionEvent event) {
        CourseDAO courseDAO = new CourseDAOimpl();
        if(courseDAO.deleteCourse(Integer.parseInt(inputid.getText()))==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("课程不存在!");
            alert.showAndWait();
            return;
        }

        //删除选课信息
        selectedcourseDAO selectedcourseDAO = new selectedcourseDAOimpl();
        selectedcourseDAO.delete(Integer.parseInt(inputid.getText()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setHeaderText(null);
        alert.setContentText("删除成功!");
        alert.showAndWait();

        loadData();
    }

    @FXML
    void refresh(ActionEvent event) {
        loadData();
    }

}