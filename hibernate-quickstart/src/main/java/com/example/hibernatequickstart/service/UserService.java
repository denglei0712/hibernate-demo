package com.example.hibernatequickstart.service;

import com.example.hibernatequickstart.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
