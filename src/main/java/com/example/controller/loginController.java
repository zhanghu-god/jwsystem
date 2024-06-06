package com.example.controller;

import com.example.dao.AdminDAO;
import com.example.dao.StudentDAO;
import com.example.dao.TeacherDAO;
import com.example.dao.daoimpl.AdminDAOimpl;
import com.example.dao.daoimpl.StudentDAOimpl;
import com.example.dao.daoimpl.TeacherDAOimpl;
import com.example.entity.Admin;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class loginController {

    @FXML
    private JFXRadioButton adlog;

    @FXML
    private PasswordField pwd;

    @FXML
    private JFXRadioButton stulog;

    @FXML
    private JFXRadioButton tclog;

    @FXML
    private ToggleGroup type;

    @FXML
    private TextField username;

    @FXML
    private Button yes;

    @FXML
    void confirm(ActionEvent event) {
        /*if(adlog.isSelected()){
            AdminDAO adminDAO=new AdminDAOimpl();
            List<Admin> admins= adminDAO.getAllAdmins();
            if (admins == null || admins.isEmpty()) {
                // 显示错误提示框
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("没有找到管理员信息！");
                alert.showAndWait();
            }
            else {
                for (Admin admin : admins) {
                    if (admin.getUsername().equals(username.getText()) && admin.getPassword().equals(pwd.getText())) {
                        loaderFXML("Admin.fxml");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("登录失败");
                        alert.setHeaderText(null);
                        alert.setContentText("用户名或密码输入错误！");
                        alert.showAndWait();
                    }
                }
            }
        }
        else if(stulog.isSelected()){
            StudentDAO studentDAO=new StudentDAOimpl();
            List<Student> students= studentDAO.getAllStudents();
            if (students == null || students.isEmpty()) {
                // 显示错误提示框
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("没有找到学生信息！");
                alert.showAndWait();
            }
            else {
                for (Student student : students) {
                    if (student.getUsername().equals(username.getText()) && student.getPassword().equals(pwd.getText())) {
                        loaderFXML("Student.fxml");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("登录失败");
                        alert.setHeaderText(null);
                        alert.setContentText("用户名或密码输入错误！");
                        alert.showAndWait();
                    }
                }
            }
        }
        else if(tclog.isSelected()){
            TeacherDAO teacherDAO=new TeacherDAOimpl();
            List<Teacher> teachers= teacherDAO.getAllTeachers();
            if (teachers == null || teachers.isEmpty()) {
                // 显示错误提示框
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("没有找到教师信息！");
                alert.showAndWait();
            }
            else {
                for (Teacher teacher : teachers) {
                    if (teacher.getUsername().equals(username.getText()) && teacher.getPassword().equals(pwd.getText())) {
                        loaderFXML("Teacher.fxml");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("登录失败");
                        alert.setHeaderText(null);
                        alert.setContentText("用户名或密码输入错误！");
                        alert.showAndWait();
                    }
                }
            }
        }*/
        loaderFXML("/Admin.fxml");
    }
    void loaderFXML(String fxmlfile){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlfile));

        try {

            // 获取Admin.fxml文件的根节点
            Parent root = loader.load();

            // 创建新的舞台（Stage）
            Stage newStage = new Stage();

            // 设置新的场景（Scene）
            Scene scene = new Scene(root, 800, 600);

            // 设置新的舞台的场景
            newStage.setScene(scene);

            Stage stage=(Stage)yes.getScene().getWindow();
            stage.close();

            // 显示新的舞台
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
