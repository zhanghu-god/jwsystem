package com.example.entity;

public class Teacher {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String name;
    private String password;
    public Teacher(int id, String name, String password) {
        // 默认构造函数
        this.id=id;
        this.password=password;
        this.name=name;
    }


}
