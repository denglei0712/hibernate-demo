package com.example.hibernatequickstart.controller;

import com.example.hibernatequickstart.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/test/book")
    public void testBook() {
        bookService.findAll().forEach(System.out::println);
    }

}
