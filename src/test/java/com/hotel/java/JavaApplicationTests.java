package com.hotel.java;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest (classes = JavaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JavaApplicationTests {

    @Test
    void contextLoads() {
    }

}
