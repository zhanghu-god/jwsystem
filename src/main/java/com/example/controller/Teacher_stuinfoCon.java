package com.example.controller;

import com.example.dao.daoimpl.CourseDAOimpl;
import com.example.dao.daoimpl.StudentDAOimpl;
import com.example.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.example.dao.daoimpl.selectedcourseDAOimpl;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @FXML
    private TableColumn<?, ?> cid;

    ObservableList<scoreList> sl = FXCollections.observableArrayList();

    public void initialize() {
        loadData();
    }
    public void loadData() {
        sl.clear();
        int ID = Userid.id;
        CourseDAOimpl courseDAOimpl = new CourseDAOimpl();
        selectedcourseDAOimpl selectedcourseDAOimpl = new selectedcourseDAOimpl();
        StudentDAOimpl studentDAOimpl = new StudentDAOimpl();
        List<Course> courselist = courseDAOimpl.getCourseByTeacherID(ID);
        if(courselist != null){
            for(Course course : courselist){
                List<selectedcourse> select = selectedcourseDAOimpl.getByCourseID(course.getCourseID());
                if(select != null){
                    for(selectedcourse s : select){
                        scoreList score = new scoreList();
                        score.setCourseName(course.getCourseName());
                        score.setCourseID(course.getCourseID());
                        score.setScore(s.getMark());
                        score.setStudentID(s.getStudentID());
                        Student stu = studentDAOimpl.getStudentById(s.getStudentID());
                        score.setStudentName(stu.getUsername());
                        sl.add(score);
                    }
                }
            }
        }
        cid.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        stuname.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        stuid.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tableview.setItems(sl);
    }
    @FXML
    void change(ActionEvent event) {
        int ID = Userid.id;
        scoreList score = tableview.getSelectionModel().getSelectedItem();
        selectedcourseDAOimpl selectedcourseDAOimpl = new selectedcourseDAOimpl();
        try{
        if(score != null){
            int newMark = Integer.parseInt(newmark.getText());
            if(newMark >= 0 && newMark <= 100){
                if(selectedcourseDAOimpl.setMark(score.getCourseID(),score.getStudentID(),newMark) == 1){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle(null);
                    alert.setContentText("修改成功");
                    loadData();
                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle(null);
                    alert.setContentText("修改失败");
                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle(null);
                alert.setContentText("请输入0到100的成绩");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle(null);
            alert.setContentText("请在表格中选中要修改成绩的学生");
            alert.showAndWait();
        }
    }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle(null);
            alert.setContentText("请输入正确的成绩");
            alert.showAndWait();
        }
    }

}