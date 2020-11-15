package com.hotel.java.application.services;

import com.hotel.java.JavaApplication;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.LoginModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    @DisplayName ("Devuelve el ID del LoginCreado")
    public void ShouldReturnIdWhenCreateLogin(){
        LoginModel log = new LoginModel (
                RandomStringUtils.random(10, true, false),
                "1234",
                "ROLE_USER",
                true,
                new ClienteModel (
                        1,
                        RandomStringUtils.random(10, true, false),
                        RandomStringUtils.random(10, true, false),
                        "test@test.com"
                )
        );
        assertThat (loginService.createLogin(log)).isGreaterThan (1);
    }

    @Test
    @DisplayName ("Debe coincidir el ID del login con la búsqueda del login por usuario")
    public void ShouldBeEqualIdFromReturnUsername(){
        LoginModel login = new LoginModel (
                1,
                "adrian",
                "1234",
                "ROLE_USER",
                true,
                new ClienteModel (
                        1,
                        "Carlos",
                        "Tocho",
                        "test@test.com"
                )
        );
        assertThat(loginService.buscaClientIdFromUsername ("adrian").getId ()).isEqualTo (login.getId ());
    }

}
