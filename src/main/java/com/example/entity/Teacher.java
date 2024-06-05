package com.example.entity;

import java.util.List;

public class Teacher extends User{
    private int id;
    private String username;
    private String password;

    public Teacher(int id, String username, String password) {
        super(id, username, password);
        // 默认构造函数
        this.id=id;
        this.password=password;
        this.username=username;
    }
}
