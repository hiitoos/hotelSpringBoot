package com.hotel.java.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hotel.java.JavaApplication;
import com.hotel.java.application.dto.PrecioDtoModel;
import com.hotel.java.application.models.HabitacionModel;
import com.hotel.java.application.models.TipoModel;
import com.hotel.java.application.services.DateService;
import com.hotel.java.application.services.HabitacionService;
import com.hotel.java.application.services.PrecioService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class PriceControllerTest {

    private static Gson gson = new GsonBuilder ().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private HabitacionService habitacionService;

    @MockBean
    private DateService dateService;

    @MockBean
    private PrecioService precioService;

    @Before("")
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup (context)
                .build ()
        ;
    }

    @Test
    @DisplayName ("Devuelve el precio total de la habitacion")
    public void ShouldReturnPriceCalculated() throws Exception {
        PrecioDtoModel dto = new PrecioDtoModel (
                1,
                Date.valueOf ("2020-11-19"),
                Date.valueOf ("2020-11-20")
        );

        HabitacionModel hab1 =  new HabitacionModel (
                1,
                "HabCod1",
                "DescHab1",
                100,
                new TipoModel (1, "tipo1", "tipo"),
                3
        );

        long dias = 2;
        float descTemp = (float) 0.5;
        double subTotal = 200;
        double total = 100;
        when(habitacionService.showHabitacionByID (dto.getId ())).thenReturn (hab1);
        when(dateService.getDaysBetweenTwoDates (dto.checkIn, dto.checkOut)).thenReturn (dias);
        when(precioService.calculaTemporada (dto.checkIn, dto.checkOut)).thenReturn (descTemp);
        when(dateService.calculateTotalPrice (dto.checkIn, dto.checkOut, hab1.getPrecio ())).thenReturn(subTotal);
        when(precioService.calculaDescuento (dias)).thenReturn (0.0);

        mvc.perform (post("/api/calculaPrecio")
                    .contentType (MediaType.APPLICATION_JSON)
                    .content (this.toJson(dto)))
                .andDo(print())
                .andExpect (status().isOk())
                .andExpect (jsonPath ("$").isNumber ())
                .andExpect (jsonPath ("$").value (total))
        ;
    }

    static byte[] toJson(Object object) {
        return gson.toJson(object).getBytes();
    }

}

