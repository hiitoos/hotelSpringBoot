package com.hotel.java.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hotel.java.JavaApplication;
import com.hotel.java.application.dto.ReservaDtoModel;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.HabitacionModel;
import com.hotel.java.application.models.ReservaModel;
import com.hotel.java.application.models.TipoModel;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class ControllerJsonTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before("")
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }


    @Test
    public void ShouldReturnBookingIdWhenNewBooking() throws Exception{
        ReservaDtoModel reserva = new ReservaDtoModel (
            new java.sql.Date(Calendar.getInstance ().getTime ().getTime ()),
            new java.sql.Date(Calendar.getInstance ().getTime ().getTime ()),
            200,
            1,
            1
        );

        mvc.perform (post("/api/newBooking")
                .contentType (MediaType.APPLICATION_JSON)
                .content (this.toJson(reserva)))
            .andDo(print())
            .andExpect (status().isOk())
            .andExpect (jsonPath ("$").isNumber ()) //h2 and expect id = 1
        ;
    }

    static byte[] toJson(Object object) throws  Exception{
        Gson gson = new GsonBuilder ().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        return  gson.toJson(object).getBytes();
    }
}
