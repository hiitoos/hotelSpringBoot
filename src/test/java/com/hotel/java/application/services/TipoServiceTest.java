package com.hotel.java.application.services;

import com.hotel.java.JavaApplication;
import com.hotel.java.application.domain.entities.TipoEntity;
import com.hotel.java.application.domain.factories.TipoFactory;
import com.hotel.java.application.models.TipoModel;
import com.hotel.java.application.repositories.TipoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TipoService tipoService;

    @Autowired
    private TipoFactory tipoFactory;

    @MockBean
    private TipoRepository tipoRepository;

    @Test
    @DisplayName ("Muestra todos los tipos en forma de lista")
    public void ShouldReturnAllTypesAsaList(){
        TipoEntity tipo1 = new TipoEntity (
                1,
                "tipo1",
                "desctipo1"
        );

        TipoEntity tipo2 = new TipoEntity (
                2,
                "tipo2",
                "desctipo2"
        );

        List<TipoEntity> tipos = new ArrayList<> ();
        tipos.add (tipo1);
        tipos.add (tipo2);

        when(tipoRepository.findAll ()).thenReturn (tipos);
        assertThat(tipoService.showAllTipos ().size ()).isEqualTo (tipoFactory.tipoListEntity2Model (tipos).size ());
    }

}
