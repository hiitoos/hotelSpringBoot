package com.hotel.java.application.services;

import com.hotel.java.JavaApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.sql.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class DateServiceTest {

    @MockBean
    private DateService dateService;

    @Test
    @DisplayName ("Debería devolver 2 días")
    public void ShouldReturn2WhenInputAre19and21OfNov(){
        when(dateService.getDaysBetweenTwoDates (Date.valueOf ("2020-11-19"), Date.valueOf ("2020-11-21"))).thenReturn (TimeUnit.DAYS.convert(2, TimeUnit.MILLISECONDS));
        assertEquals (dateService.getDaysBetweenTwoDates (Date.valueOf ("2020-11-19"), Date.valueOf ("2020-11-21")), TimeUnit.DAYS.convert(2, TimeUnit.MILLISECONDS));
    }

    @Test
    @DisplayName ("Debería devolver 300")
    public void ShouldReturn300Due2Have2DaysBetween(){
        when(dateService.calculateTotalPrice (Date.valueOf ("2020-11-19"), Date.valueOf ("2020-11-21"), 150)).thenReturn (300.0);
        assertEquals (dateService.calculateTotalPrice (Date.valueOf ("2020-11-19"), Date.valueOf ("2020-11-21"), 150), 300);
    }

}
