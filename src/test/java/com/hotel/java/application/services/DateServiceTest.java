package com.hotel.java.application.services;

import com.hotel.java.JavaApplication;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class DateServiceTest {

    @MockBean
    private DateService dateService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before("")
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build()
        ;
    }

    @Test
    @DisplayName ("Debería devolver 2 días")
    public void ShouldReturn2WhenInputAre19and21OfNov(){
        when(dateService.getDaysBetweenTwoDates (java.sql.Date.valueOf ("2020-11-19"), java.sql.Date.valueOf ("2020-11-21"))).thenReturn (TimeUnit.DAYS.convert(2, TimeUnit.MILLISECONDS));
        assertEquals (dateService.getDaysBetweenTwoDates (java.sql.Date.valueOf ("2020-11-19"), java.sql.Date.valueOf ("2020-11-21")), TimeUnit.DAYS.convert(2, TimeUnit.MILLISECONDS));
    }

}
