package com.hotel.java.application.services;

import com.hotel.java.JavaApplication;
import com.hotel.java.application.domain.entities.ClienteEntity;
import com.hotel.java.application.domain.factories.ClienteFactory;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.LoginModel;
import com.hotel.java.application.repositories.ClienteRepository;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteFactory clienteFactory;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private LoginService loginService;

    /*@Test
    @DisplayName ("Se comprueba que devuelve el id del cliente al crear cliente")
    public void ShouldReturnClientId() {
        ClienteModel mockCliente = new ClienteModel ("Cliente", "SuApellido", "suCorreo@correo.com");
        LoginModel login = new LoginModel ("usu", "pass", "ROLE_USER", true, mockCliente);

        ClienteEntity cliente = new ClienteEntity ("Cliente", "SuApellido", "suCorreo@correo.com");
        when (clienteFactory.clienteModel2Entity (mockCliente)).thenReturn (cliente);
        when (clienteRepository.save (cliente).getId ()).thenReturn (1L);
        assertThat(clienteService.createCliente (mockCliente, login)).isEqualTo (1L);
    }*/

    @Test
    @DisplayName ("Devuelve cliente cuando buscamos con su ID")
    public void ShouldReturnClienteWhenShearchId1(){
        ClienteEntity cliente = new ClienteEntity (1, "Cliente", "SuApellido", "suCorreo@correo.com");
        ClienteModel mockCliente = new ClienteModel (1, "Carlos", "Tocho", "test@test.com");
        when(clienteRepository.findById (1L)).thenReturn (java.util.Optional.of (cliente));
        when(clienteFactory.clienteEntity2Model (cliente)).thenReturn (mockCliente);
        assertThat (clienteService.buscaId (1)).isEqualTo (mockCliente);
    }

}
