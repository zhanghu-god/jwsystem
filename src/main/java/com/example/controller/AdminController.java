package com.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

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
    private JFXButton tcinfo;

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
        loadView("/Admin_stuinfo.fxml");
    }

    @FXML
    void showtcinfo(ActionEvent event) {
        loadView("/Admin_tcinfo.fxml");
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