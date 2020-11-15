package com.hotel.java.application.services;

import com.hotel.java.JavaApplication;
import com.hotel.java.application.models.HabitacionModel;
import com.hotel.java.application.models.TipoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class HabitacionServiceTest {

    @Autowired
    private HabitacionService habitacionService;

    HabitacionModel hab1 =  new HabitacionModel (1, "HabCod1", "DescHab1", 100, new TipoModel (1, "tipo1", "tipo"), 3);
    HabitacionModel hab2 =  new HabitacionModel (2, "HabCod2", "DescHab2", 100, new TipoModel (2, "tipo2", "tipo"), 4);
    List<HabitacionModel> habitaciones = new ArrayList ();


    @Test
    @DisplayName ("Debería devolver todas las habitaciones")
    public void ShouldReturnAllRooms(){
        habitaciones.add(hab1);
        habitaciones.add(hab2);
        assertThat(habitacionService.showAllHabitaciones ().equals (habitaciones));
    }

    @Test
    @DisplayName ("Debería devolver HAB1")
    public void ShouldReturnHab1WhenGuestNumIs3(){
        assertThat (habitacionService.showHabitacionesByGuest (3).equals (hab1));
    }

    @Test
    @DisplayName ("Debería devolver HAB2 ")
    public void ShouldReturnHab2WhenIdHabIs2(){
        assertThat (habitacionService.showHabitacionByID (2).equals(hab2));
    }

}
