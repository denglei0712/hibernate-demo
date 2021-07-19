package com.example.hibernatequickstart.controller;

import com.example.hibernatequickstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test/hibernate")
    public void testHibernate() {
        userService.findAll().forEach(System.out::println);
    }

}
