package com.hotel.java.application.services;

import com.hotel.java.JavaApplication;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.LoginModel;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class ClienteServiceTest {

    @MockBean
    private ClienteService clienteService;

    @Test
    @DisplayName ("Se comprueba que devuelve el id del cliente")
    public void ShouldReturnClientId() {
        ClienteModel mockCliente = new ClienteModel ("Cliente", "SuApellido", "suCorreo@correo.com");
        LoginModel login = new LoginModel ("usu", "pass", "ROLE_USER", true, mockCliente);
        long idClient = clienteService.createCliente (mockCliente, login);
        Assert.assertTrue (isNumeric(idClient));
    }

    @Test
    @DisplayName ("Devuelve cliente cuando buscamos con su ID")
    public void ShouldReturnClienteWhenShearchId1(){
        ClienteModel mockCliente = new ClienteModel (1, "Cliente", "SuApellido", "suCorreo@correo.com");
        when(clienteService.buscaId (1)).thenReturn (mockCliente);
        Assert.assertEquals (clienteService.buscaId (1), mockCliente);
    }

}
