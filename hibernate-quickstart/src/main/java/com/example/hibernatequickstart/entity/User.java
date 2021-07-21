package com.example.hibernatequickstart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String sex;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
