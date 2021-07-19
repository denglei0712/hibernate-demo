package com.example.hibernatequickstart;

import com.example.hibernatequickstart.repository.UserRepository;
import com.example.hibernatequickstart.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
public class HibernateQuickstartApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        userService.findAll().forEach(System.out::println);
    }

}
