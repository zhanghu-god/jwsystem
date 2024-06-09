package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Admin_teainfoController {
    @FXML
    private Button addbtn;

    @FXML
    private Button delbtn;

    @FXML
    private TextField inputid;

    @FXML
    private TextField inputname;

    @FXML
    private TableColumn<?, ?> password;

    @FXML
    private Button refreshbtn;

    @FXML
    private TableColumn<?, ?> teaid;

    @FXML
    private TableColumn<?, ?> teaname;

    @FXML
    private TableView<?> tableview;


    public void add(ActionEvent event) {

    }

    public void del(ActionEvent event) {
    }

    public void refresh(ActionEvent event) {
    }
}
