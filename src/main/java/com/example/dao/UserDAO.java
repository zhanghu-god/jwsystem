package com.example.dao;

import com.example.entity.User;

import java.util.List;

public interface UserDAO {

    void AddUser(User user);
    void DeleteUser(int id);

    List<User> getAllUsers();

    User getuserById(int id);
    void UpdatePassword(int id, String newPassword);
}
