package com.hotel.java.application.services;

import com.hotel.java.JavaApplication;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.HabitacionModel;
import com.hotel.java.application.models.ReservaModel;
import com.hotel.java.application.models.TipoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class ReservaServiceTest {

    @MockBean
    private ReservaService reservaService;

    @Test
    @DisplayName ("Devuelve la lista de reservas cuando llama al metodo")
    public void ShouldReturnBookingListWhenCallTheMethod(){
        ReservaModel reserva1 = new ReservaModel (
                1,
                Date.valueOf ("2020-11-19"),
                Date.valueOf ("2020-11-21"),
                300,
                new ClienteModel (1, "nombre", "apellido", "correo@correo.com"),
                new HabitacionModel ("HabCod", "DescHab", 100, new TipoModel (1, "tipo", "tipo"), 3)

        );

        ReservaModel reserva2 = new ReservaModel (
                2,
                Date.valueOf ("2020-11-22"),
                Date.valueOf ("2020-11-23"),
                300,
                new ClienteModel (1, "nombre", "apellido", "correo@correo.com"),
                new HabitacionModel ("HabCod", "DescHab", 100, new TipoModel (1, "tipo", "tipo"), 3)

        );

        List<ReservaModel> reservas = new ArrayList ();
        reservas.add (reserva1);
        reservas.add (reserva2);
        when (reservaService.listReservas()).thenReturn (reservas);

        assertThat(reservaService.listReservas ()).hasSizeGreaterThan (1);
    }

    @Test
    @DisplayName ("Devuelve la reserva1 cuando se busca con id 1")
    public void ShouldReturnReserva1WhenSearchByItsID(){
        ReservaModel reserva1 = new ReservaModel (
                1,
                Date.valueOf ("2020-11-19"),
                Date.valueOf ("2020-11-21"),
                300,
                new ClienteModel (1, "nombre", "apellido", "correo@correo.com"),
                new HabitacionModel ("HabCod", "DescHab", 100, new TipoModel (1, "tipo", "tipo"), 3)

        );
        when (reservaService.listReservaById (1)).thenReturn (reserva1);
        assertThat (reservaService.listReservaById (1)).isEqualTo (reserva1);

    }

    @Test
    @DisplayName ("Devuelve el id de la reserva al crearla")
    public void ShouldReturnBookingIdWhenCreateIt(){
        ReservaModel reserva1 = new ReservaModel (
                Date.valueOf ("2020-11-19"),
                Date.valueOf ("2020-11-21"),
                300,
                new ClienteModel (1, "nombre", "apellido", "correo@correo.com"),
                new HabitacionModel ("HabCod", "DescHab", 100, new TipoModel (1, "tipo", "tipo"), 3)
        );
        when (reservaService.operateReserva (reserva1, "new")).thenReturn (1L);
        assertThat(reservaService.operateReserva (reserva1, "new")).isEqualTo (1L);
    }

    @Test
    @DisplayName ("Devuelve una lista con los días que esta ocupada una habitación según ID de reserva")
    public void ShouldReturnListOfDaysBetweenCheckInAndCheckOutDependingBookingId() {
        ReservaModel reserva1 = new ReservaModel (
                1,
                Date.valueOf ("2020-11-19"),
                Date.valueOf ("2020-11-23"),
                300,
                new ClienteModel (1, "nombre", "apellido", "correo@correo.com"),
                new HabitacionModel ("HabCod", "DescHab", 100, new TipoModel (1, "tipo", "tipo"), 3)
        );

        List<Date> dates = new ArrayList();
        dates.add(Date.valueOf ("2020-11-19"));
        dates.add(Date.valueOf ("2020-11-20"));
        dates.add(Date.valueOf ("2020-11-21"));
        dates.add(Date.valueOf ("2020-11-22"));

        when(reservaService.listaDate (reserva1.getId ())).thenReturn (dates);
        assertThat (reservaService.listaDate (1).equals (dates));
    }
}
