package com.example.entity;

public class Admin extends User{
    private int id;
    private String username;
    private String password;

    // 管理员类继承自用户类
    public Admin(int id, String username, String password) {
        super(id, username, password);
        // 默认构造函数
        this.id=id;
        this.password=password;
        this.username=username;
    }
    public Admin(){

    }

    // 管理员独有的属性
    private String role;

    // 管理员独有的方法
    public void manageUsers() {
        // 管理用户的方法
    }
}
