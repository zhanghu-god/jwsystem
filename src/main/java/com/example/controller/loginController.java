package com.example.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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
        Stage stage=(Stage)yes.getScene().getWindow();
        stage.close();
        // 加载Admin.fxml文件
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Student.fxml"));

        try {

            // 获取Admin.fxml文件的根节点
            Parent root = loader.load();

            // 创建新的舞台（Stage）
            Stage newStage = new Stage();

            // 设置新的场景（Scene）
            Scene scene = new Scene(root, 800, 600);

            // 设置新的舞台的场景
            newStage.setScene(scene);

            // 显示新的舞台
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
