package com.example.service.impl;

import com.example.dao.AdminDAO;
import com.example.dao.daoimpl.AdminDAOimpl;
import com.example.entity.Admin;
import com.example.service.AdminService;

public class AdminServiceimpl implements AdminService {
        AdminDAO admindao=new AdminDAOimpl();

    @Override
    public Admin adminLogin(String username, String password) {
        return admindao.login(username,password);
    }
}
