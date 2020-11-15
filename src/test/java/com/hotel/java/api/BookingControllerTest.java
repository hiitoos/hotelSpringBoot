package com.hotel.java.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hotel.java.JavaApplication;
import com.hotel.java.application.dto.ReservaDtoModel;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.HabitacionModel;
import com.hotel.java.application.models.ReservaModel;
import com.hotel.java.application.models.TipoModel;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class BookingControllerTest {

    private static Gson gson = new GsonBuilder ().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();;

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
    @DisplayName("Debe devolver la ID como número al realizar una nueva reserva")
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
            .andExpect (jsonPath ("$").isNumber ())
        ;
    }

    @Test
    @DisplayName ("Debe devolver la reserva con entrada su ID")
    public void ShouldReturnBookingWhenInputId() throws Exception {
        ReservaModel reserva1 = new ReservaModel (
                1,
                new java.sql.Date(Calendar.getInstance ().getTime ().getTime ()),
                new java.sql.Date(Calendar.getInstance ().getTime ().getTime ()),
                100,
                new ClienteModel (1, "Jose", "Perez","correo@correo.com"),
                new HabitacionModel ("HabCod", "DescHab", 100, new TipoModel (1, "tipo", "tipo"), 3)
        );

        when(reservaService.listReservaById (1)).thenReturn (reserva1);
        mvc.perform (get("/api/showBookingById/1")
                .contentType (MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect (jsonPath ("$.id").value(1))
                .andExpect (jsonPath ("$.cliente.nombre").value ("Jose"))
        ;

    }

    @Test
    @DisplayName ("Debe devolver la lista de reservas")
    public void ShouldReturnListBooking() throws Exception {
        ReservaModel reserva1 = new ReservaModel (
                1,
                new java.sql.Date(Calendar.getInstance ().getTime ().getTime ()),
                new java.sql.Date(Calendar.getInstance ().getTime ().getTime ()),
                100,
                new ClienteModel (1, "Jose", "Perez","correo@correo.com"),
                new HabitacionModel ("HabCod", "DescHab", 100, new TipoModel (1, "tipo", "tipo"), 3)
        );

        ReservaModel reserva2 = new ReservaModel (
                2,
                new java.sql.Date(Calendar.getInstance ().getTime ().getTime ()),
                new java.sql.Date(Calendar.getInstance ().getTime ().getTime ()),
                200,
                new ClienteModel (1, "Adrian", "Hitos","correo@correo.com"),
                new HabitacionModel ("HabCod", "DescHab", 200, new TipoModel (1, "tipo", "tipo"), 3)
        );

        List<ReservaModel> reservas = new ArrayList();
        reservas.add(reserva1);
        reservas.add(reserva2);
        when(reservaService.listReservas ()).thenReturn (reservas);
        mvc.perform (get("/api/showAllBookings")
                .contentType (MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath ("$", hasSize (2)))
                .andExpect (jsonPath ("$[0].precioTotal").value(100.0))
        ;
    }

    static byte[] toJson(Object object) {
        return gson.toJson(object).getBytes();
    }
    
}
