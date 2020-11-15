package com.hotel.java.application.services;

import com.hotel.java.JavaApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
public class DateServiceTest {

    @Autowired
    private DateService dateService;

    @Test
    @DisplayName ("Debería devolver 2 días")
    public void ShouldReturn2WhenInputAre19and21OfNov(){
        assertEquals (dateService.getDaysBetweenTwoDates (Date.valueOf ("2020-11-19"), Date.valueOf ("2020-11-21")), 2);
    }

    @Test
    @DisplayName ("Debería devolver 300")
    public void ShouldReturn300Due2Have2DaysBetween(){
        assertEquals (dateService.calculateTotalPrice (Date.valueOf ("2020-11-19"), Date.valueOf ("2020-11-21"), 150), 300);
    }

}
