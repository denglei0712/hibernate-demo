package com.example.hibernatequickstart.service;

import com.example.hibernatequickstart.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
}
