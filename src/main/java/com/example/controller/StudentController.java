package com.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

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
    private void loadView(String fxmlFile) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlFile));
            border.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
