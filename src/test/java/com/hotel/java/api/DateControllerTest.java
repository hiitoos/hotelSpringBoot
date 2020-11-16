package com.hotel.java.api;

import com.hotel.java.JavaApplication;
import com.hotel.java.application.services.ReservaService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class DateControllerTest {

    @MockBean
    private ReservaService reservaService;

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
    @DisplayName ("Devuelve la lista de fechas según ID")
    public void ShouldReturnDateListOfDaysOfFullFilledRoom() throws Exception {
        List<Date> fechas = new ArrayList<> ();
        fechas.add(Date.valueOf ("2020-11-19"));
        fechas.add(Date.valueOf ("2020-11-20"));
        fechas.add(Date.valueOf ("2020-11-21"));
        fechas.add(Date.valueOf ("2020-11-22"));

        when(reservaService.listaDate (1)).thenReturn (fechas);
        jsonPath ("$[0]");
        mvc.perform (get("/api/listDate/1")
                .contentType (MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect (status().isOk())
                .andExpect (jsonPath ("$").isArray ())
                .andExpect (jsonPath ("$[0]").value (fechas.get (0).toString ()))
        ;
    }
}
