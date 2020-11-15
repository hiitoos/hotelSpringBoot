package com.hotel.java.application.services;

import com.hotel.java.JavaApplication;
import com.hotel.java.application.models.TipoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class TipoServiceTest {

    @MockBean
    private TipoService tipoService;

    @Test
    @DisplayName ("Muestra todos los tipos en forma de lista")
    public void ShouldReturnAllTypesAsaList(){
        TipoModel tipo1 = new TipoModel (
                1,
                "tipo1",
                "desctipo1"
        );

        TipoModel tipo2 = new TipoModel (
                2,
                "tipo2",
                "desctipo2"
        );

        List<TipoModel> tipos = new ArrayList<> ();
        tipos.add (tipo1);
        tipos.add (tipo2);

        when(tipoService.showAllTipos ()).thenReturn (tipos);
        assertThat(tipoService.showAllTipos ()).isEqualTo (tipos);
    }
}
