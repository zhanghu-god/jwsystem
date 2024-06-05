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

import static com.example.util.DBUtil.closeAll;
import static com.example.util.DBUtil.executeQuery;

public class Admin_tcinfoController {
    @FXML
    private TextField inputid;

    @FXML
    private TextField inputname;
    @FXML
    private TableColumn<Teacher, String> password;

    @FXML
    private TableView<Teacher> tableview;

    @FXML
    private TableColumn<Teacher, Integer> tcid;

    @FXML
    private TableColumn<Teacher, String> tcname;
    @FXML
    private Button addbtn;

    @FXML
    private Button delbtn;

    @FXML
    private Button refreshbtn;

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
        String sql = "SELECT userid,username,password FROM teacher";
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
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //设置tableview的每一列
        tcid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcname.setCellValueFactory(new PropertyValueFactory<>("name"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        //将数据添加到tableview中

        tableview.setItems(teacherList);

    }
}

