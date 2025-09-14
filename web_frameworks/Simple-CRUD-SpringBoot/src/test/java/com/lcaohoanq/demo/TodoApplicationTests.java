package com.lcaohoanq.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class TodoApplicationTests {

    @Value("${spring.application.name}")
    private String title;

    @Test
    void contextLoads() {
        System.out.println(">>> Application name: " + title);
    }

}
