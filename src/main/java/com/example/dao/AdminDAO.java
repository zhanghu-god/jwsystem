package com.example.dao;


import com.example.entity.Admin;

import java.util.List;

public interface AdminDAO {
    //管理员查询单个操作
    public Admin login(String username,String password);
    void AddAdmin(Admin admin);
    void DeleteAdmin(int id);
    List<Admin> getAllAdmins();
    Admin getAdminById(int id);
    void UpdatePassword(int id, String newPassword);
}
