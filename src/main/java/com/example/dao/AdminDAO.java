package com.example.dao;


import com.example.entity.Admin;

public interface AdminDAO {
    //管理员查询单个操作
    public Admin login(String username,String password);
}
