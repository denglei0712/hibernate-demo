package com.example.hibernatequickstart.service.impl;

import com.example.hibernatequickstart.entity.User;
import com.example.hibernatequickstart.repository.UserRepository;
import com.example.hibernatequickstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
