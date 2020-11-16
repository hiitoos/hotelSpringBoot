package com.hotel.java.application.services;

import com.hotel.java.JavaApplication;

import com.hotel.java.application.domain.factories.ReservaFactory;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.HabitacionModel;
import com.hotel.java.application.models.ReservaModel;
import com.hotel.java.application.models.TipoModel;
import com.hotel.java.application.repositories.ReservaRepository;
import org.junit.Assert;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class ReservaServiceTest {

    @Autowired
    private ReservaService reservaService;

    @MockBean
    private ReservaFactory reservaFactory;

    @Test
    @DisplayName ("Devuelve la lista de reservas cuando llama al metodo")
    public void ShouldReturnBookingListWhenCallTheMethod(){
        ReservaModel reserva1 = new ReservaModel (
                1,
                Date.valueOf ("2020-11-19"),
                Date.valueOf ("2020-11-21"),
                300,
                new ClienteModel (1, "nombre", "apellido", "correo@correo.com"),
                new HabitacionModel (1, "HabCod", "DescHab", 100, new TipoModel (1, "tipo", "tipo"), 3)

        );

        ReservaModel reserva2 = new ReservaModel (
                2,
                Date.valueOf ("2020-11-22"),
                Date.valueOf ("2020-11-23"),
                300,
                new ClienteModel (1, "nombre", "apellido", "correo@correo.com"),
                new HabitacionModel (1, "HabCod", "DescHab", 100, new TipoModel (1, "tipo", "tipo"), 3)

        );

        List<ReservaModel> reservas = new ArrayList ();
        reservas.add (reserva1);
        reservas.add (reserva2);

        when (reservaFactory.reservaListEntity2Model (any (List.class))).thenReturn (reservas);

        assertThat(reservaService.listReservas ()).hasSizeGreaterThan (1);
        assertThat(reservaService.listReservas ()).isEqualTo (reservas);
    }

//    @Test
//    @DisplayName ("Devuelve la reserva1 cuando se busca con id 1")
//    public void ShouldReturnReserva1WhenSearchByItsID(){
//        ReservaModel reserva1 = new ReservaModel (
//                1,
//                Date.valueOf ("2020-11-19"),
//                Date.valueOf ("2020-11-21"),
//                300,
//                new ClienteModel (1, "nombre", "apellido", "correo@correo.com"),
//                new HabitacionModel ("HabCod", "DescHab", 100, new TipoModel (1, "tipo", "tipo"), 3)
//
//        );
//        when (reservaFactory.reservaEntity2Model (any (ReservaEntity.class))).thenReturn (reserva1);
//        assertThat (reservaService.listReservaById (1)).isEqualTo (reserva1);
//
//    }

//    @Test
//    @DisplayName ("Devuelve el id de la reserva al crearla")
//    public void ShouldReturnBookingIdWhenCreateIt(){
//        ReservaModel reserva1 = new ReservaModel (
//                Date.valueOf ("2020-11-19"),
//                Date.valueOf ("2020-11-21"),
//                300,
//                new ClienteModel (1, "nombre", "apellido", "correo@correo.com"),
//                new HabitacionModel (1,"HabCod", "DescHab", 100, new TipoModel (1, "tipo", "tipo"), 3)
//        );
//        //ReservaEntity reserva = rf.reservaModel2Entity (reserva1);
//        //when (rf.reservaModel2Entity (reserva1)).thenReturn (reserva);
//        long id = reservaService.operateReserva (reserva1, "new");
//        assertThat(id).isGreaterThan (1);
//    }

    @MockBean
    private ReservaRepository reservaRepository;


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

        List<Date[]> dates = new ArrayList();
        Date fechas[] = new Date[2];
        fechas[0] = reserva1.getFechaIn ();
        fechas[1] = reserva1.getFechaOut();
        dates.add(fechas);

        List<Date>  dias = new ArrayList ();
        dias.add (Date.valueOf ("2020-11-19"));
        dias.add (Date.valueOf ("2020-11-20"));
        dias.add (Date.valueOf ("2020-11-21"));
        dias.add (Date.valueOf ("2020-11-22"));


        when(reservaRepository.dateBookingsByRoom (1)).thenReturn (dates);
        List<Date> res = reservaService.listaDate (reserva1.getId ());
        Assert.assertEquals(dias, res);
    }
}
