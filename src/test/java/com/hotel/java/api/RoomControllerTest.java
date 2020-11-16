package com.hotel.java.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hotel.java.JavaApplication;
import com.hotel.java.application.dto.FinalRoomDtoModel;
import com.hotel.java.application.dto.PrecioDtoModel;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.HabitacionModel;
import com.hotel.java.application.models.ReservaModel;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class RoomControllerTest {
    private static Gson gson = new GsonBuilder ().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private HabitacionService habitacionService;

    @Before("")
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup (context)
                .build ()
        ;
    }

    @Test
    @DisplayName("Devuelve el precio total de la habitacion")
    public void ShouldReturnPriceCalculated() throws Exception {
        HabitacionModel hab1 = new HabitacionModel (
                1,
                "HabCod1",
                "DescHab1",
                100,
                new TipoModel (
                        1,
                        "tipo",
                        "tipo"),
                3
        );

        HabitacionModel hab2 = new HabitacionModel (
                2,
                "HabCod2",
                "DescHab2",
                200,
                new TipoModel (
                        1,
                        "tipo",
                        "tipo"),
                3
        );

        List<HabitacionModel> habitaciones = new ArrayList ();
        habitaciones.add (hab1);
        habitaciones.add (hab2);

        when(habitacionService.showAllHabitaciones ()).thenReturn (habitaciones);

        mvc.perform (get("/api/showAllRooms")
                .contentType (MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect (status().isOk())
                .andExpect (jsonPath ("$[0].codigo").value (habitaciones.get (0).getCodigo ()))
                .andExpect (jsonPath ("$[1].descripcion").value (habitaciones.get (1).getDescripcion ()))
        ;
    }

    @Test
    @DisplayName ("Devuelve un DTO con la habitación y las fechas que está ocupada")
    public void ShouldReturnDtoWithRoomAndItsDatesWhenEnterID() throws Exception {
        HabitacionModel hab1 = new HabitacionModel (
                1,
                "HabCod1",
                "DescHab1",
                100,
                new TipoModel (
                        1,
                        "tipo",
                        "desc"),
                3
        );

        List<Date> fechas = new ArrayList<> ();
        fechas.add (Date.valueOf ("2020-11-20"));
        fechas.add (Date.valueOf ("2020-11-21"));
        fechas.add (Date.valueOf ("2020-11-22"));
        fechas.add (Date.valueOf ("2020-11-23"));

        FinalRoomDtoModel finalroom = new FinalRoomDtoModel (
                hab1,
                fechas
        );

        when(habitacionService.showHabitacionByID (1)).thenReturn (hab1);

        mvc.perform (get("/api/showRoomById/1")
                .contentType (MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect (status().isOk())
                .andExpect (jsonPath ("$.habitacion.codigo").value (finalroom.getHabitacion ().getCodigo ()))
        ;

    }

}
