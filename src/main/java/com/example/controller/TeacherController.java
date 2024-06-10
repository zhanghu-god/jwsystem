package com.example.controller;

import com.example.entity.Userid;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private JFXButton logout;

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

    @FXML
    void showlogout(ActionEvent event) {
        Userid.id = 0;
        loaderFXML("/Login.fxml");
    }

    private void loaderFXML(String s) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));

        try {
            // 获取.fxml文件的根节点
            Parent root = loader.load();

            // 创建新的舞台（Stage）
            Stage newStage = new Stage();

            // 设置新的舞台的标题
            newStage.setTitle("登录界面");

            //设置舞台不可伸缩
            newStage.setResizable(false);

            // 设置新的场景（Scene）
            Scene scene = new Scene(root, 522,285);

            // 设置新的舞台的场景
            newStage.setScene(scene);

            Stage stage=(Stage)logout.getScene().getWindow();

            newStage.setUserData(Userid.id);

            stage.close();

            // 显示新的舞台
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
