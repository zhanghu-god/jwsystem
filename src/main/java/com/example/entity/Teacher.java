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
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }


}
