package com.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherController {
    @FXML
    private BorderPane border;

    @FXML
    private JFXButton changepwd;

    @FXML
    private JFXButton courseinfo;

    @FXML
    private JFXButton main;

    @FXML
    private JFXButton stuinfo;

    @FXML
    public void initialize(){
        // 设置默认显示的界面
        loadView("/Teacher_main.fxml");
    }

    @FXML
    void showchangepwd(ActionEvent event) {
        loadView("/Teacher_changepwd.fxml");
    }

    @FXML
    void showcourseinfo(ActionEvent event) {
        loadView("/Teacher_courseinfo.fxml");
    }

    @FXML
    void showmain(ActionEvent event) {
        loadView("/Teacher_main.fxml");
    }

    @FXML
    void showstuinfo(ActionEvent event) {
        loadView("/Teacher_stuinfo.fxml");
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
