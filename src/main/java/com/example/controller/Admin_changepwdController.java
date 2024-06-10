package com.example.controller;

import com.example.dao.AdminDAO;
import com.example.dao.TeacherDAO;
import com.example.dao.UserDAO;
import com.example.dao.daoimpl.AdminDAOimpl;
import com.example.dao.daoimpl.TeacherDAOimpl;
import com.example.dao.daoimpl.UserDAOimpl;
import com.example.entity.Admin;
import com.example.entity.Teacher;
import com.example.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class Admin_changepwdController {

    @FXML
    private Button confirm;

    @FXML
    private PasswordField newpwd;

    @FXML
    private PasswordField oldpwd;

    @FXML
    private PasswordField yesnewpwd;

    @FXML
    void changepwd(ActionEvent event) {
        // 获取输入的旧密码和新密码
        String oldPassword = oldpwd.getText();
        String newPassword = newpwd.getText();
        String yesnewPassword = yesnewpwd.getText();
        Stage stage = (Stage) confirm.getScene().getWindow();
        int id = (int) stage.getUserData();
        System.out.println(id);

        // 检查旧密码是否正确
        AdminDAO adminDAO=new AdminDAOimpl();
        UserDAO userDAO=new UserDAOimpl();

        Admin admin=adminDAO.getAdminById(id);
        if(!admin.getPassword().equals(oldPassword)){
            // 提示用户旧密码不正确
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("修改失败");
            alert.setHeaderText(null);
            alert.setContentText("旧密码不正确，请重新输入！");
            alert.showAndWait();
        }// 检查新密码是否与确认密码一
        else if (admin.getPassword().equals(oldPassword)&&!newPassword.equals(yesnewPassword)) {
            // 提示用户新密码与确认密码不一致
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("修改失败");
            alert.setHeaderText(null);
            alert.setContentText("两次密码输入不一致，请重新输入！");
            alert.showAndWait();
        }
        else {
            // 调用dao层的方法执行修改密码操作
            adminDAO.UpdatePassword(id, newPassword);
            userDAO.UpdatePassword(id, newPassword);
            // 修改成功后，关闭当前窗口
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("修改成功");
            alert.setHeaderText(null);
            alert.setContentText("密码修改成功！");
            alert.showAndWait();
        }
    }

}