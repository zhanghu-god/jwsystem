package com.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminController {
    @FXML
    private BorderPane border;

    @FXML
    private JFXButton changepwd;

    @FXML
    private JFXButton main;

    @FXML
    private JFXButton stuinfo;

    @FXML
    private JFXButton couinfo;

    public void initialize(){
        // 设置默认显示的界面
        loadView("/Admin_main.fxml");
    }

    @FXML
    void showchangepwd(ActionEvent event) {
        loadView("/Admin_changepwd.fxml");
    }

    @FXML
    void showmain(ActionEvent event) {
        loadView("/Admin_main.fxml");
    }

    @FXML
    void showstuinfo(ActionEvent event) {
        System.out.println(main.getScene().getWindow().getUserData());

        loadView("/Admin_stuinfo.fxml");
    }

    @FXML
    void showcouinfo(ActionEvent event) {
        loadView("/Admin_couinfo.fxml");
    }

    private void loadView(String fxmlFile) {
        try {
            Parent view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            border.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}