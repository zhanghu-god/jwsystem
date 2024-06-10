package com.example.controller;

import com.example.entity.Userid;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StudentController {
    @FXML
    private BorderPane border;

    @FXML
    private JFXButton changepwd;

    @FXML
    private JFXButton checkscore;

    @FXML
    private JFXButton main;

    @FXML
    private JFXButton select;

    @FXML
    private JFXButton logout;

    public void initialize(){
        // 设置默认显示的界面
        loadView("/Student_main.fxml");
    }
    @FXML
    void showmain(ActionEvent event) {
        loadView("/Student_main.fxml");
    }

    @FXML
    void showchangepwd(ActionEvent event) {
        loadView("/Student_changepwd.fxml");
    }

    @FXML
    void showcheckscore(ActionEvent event) {
        loadView("/Student_checkscore.fxml");
    }

    @FXML
    void showselect(ActionEvent event) {
        loadView("/Student_select.fxml");
    }

    @FXML
    void showlogout(ActionEvent event) {
        Userid.id = 0;
        Stage stage = (Stage) border.getScene().getWindow();
        //加载登录界面绝对路径
        Parent root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Login.fxml")));
        stage.setTitle("登录界面");
        //设置窗口不可伸缩
        stage.setResizable(false);
        stage.setScene(new Scene(root, 522, 294));
        stage.show();
    }

    private void loadView(String fxmlFile) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlFile));
            border.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
