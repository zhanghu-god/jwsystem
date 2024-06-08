package com.example.controller;

import com.example.entity.Teacher;
import com.example.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin_couinfoController {
    @FXML
    private Button addbtn;

    @FXML
    private TableColumn<?, ?> classroom;

    @FXML
    private TableColumn<?, ?> courseid;

    @FXML
    private TableColumn<?, ?> coursename;

    @FXML
    private Button delbtn;

    @FXML
    private TextField inputclassroom;

    @FXML
    private TextField inputcouid;

    @FXML
    private TextField inputcouname;

    @FXML
    private TextField inputtcid;

    @FXML
    private TextField inputtcname;

    @FXML
    private TextField inputtime;

    @FXML
    private Button refreshbtn;

    @FXML
    private TableColumn<?, ?> time;

    @FXML
    private TableView<Teacher> tableview;

    @FXML
    private TableColumn<Teacher, Integer> tcid;

    @FXML
    private TableColumn<?, ?> tcname;

    @FXML
    void add(ActionEvent event) {

    }


    @FXML
    void del(ActionEvent event) {

    }

    @FXML
    void refresh(ActionEvent event) {

    }
    //创建集合teacherList
    ObservableList<Teacher> teacherList= FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        loadData();
    }
    public void loadData() throws SQLException {
        /*String sql = "SELECT userid,username,password FROM teacher";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("userid");
                String name = rs.getString("username");
                String password = rs.getString("password");
                Teacher teacher = new Teacher(id, name, password);
                teacherList.add(teacher);
            }
        } finally {
            DBUtil.closeAll(rs, stmt, conn);
        }
        //设置tableview的每一列
        tcid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcname.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        //将数据添加到tableview中

        tableview.setItems(teacherList);*/

    }
}

