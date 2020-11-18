package com.hotel.java.application.services;

import com.hotel.java.JavaApplication;
import com.hotel.java.application.repositories.TemporadaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaApplication.class)
@AutoConfigureMockMvc
public class PrecioServiceTest {

    @Autowired
    private PrecioService precioService;

    @MockBean
    private TemporadaRepository temporadaRepository;

    @Test
    @DisplayName ("Devuelve 0 como descuento con menos de 10 dias")
    public void ShouldReturn0WhenInputLess10asDays(){
        assertThat (precioService.calculaDescuento (7)).isEqualTo (0);
    }

    @Test
    @DisplayName ("Devuelve 0.05 como descuento con 10 a 15 dias")
    public void ShouldReturn005WhenInput13asDays(){
        assertThat (precioService.calculaDescuento (12)).isEqualTo (0.05);
    }

    @Test
@DisplayName ("Devuelve 0.10 como descuento con 16 a 19 dias")
    public void ShouldReturn010WhenInput16asDays(){
        assertThat (precioService.calculaDescuento (16)).isEqualTo (0.10);
    }

    @Test
    @DisplayName ("Devuelve 0.20 como descuento con más de 20 dias")
    public void ShouldReturn020WhenInput21asDays(){
        assertThat (precioService.calculaDescuento (21)).isEqualTo (0.20);
    }

    @Test
    @DisplayName ("Devuelve 0.08 como descuento por temporada baja")
    public void ShouldReturn008WhenLowSeason(){
        when(temporadaRepository.descuento (Date.valueOf ("2020-11-19"))).thenReturn (0.08f);
        when(temporadaRepository.descuento (Date.valueOf ("2020-11-21"))).thenReturn (0.08f);
        assertThat (precioService.calculaTemporada (Date.valueOf ("2020-11-19"), Date.valueOf ("2020-11-21"))).isEqualTo (0.08f);
    }


    @Test
    @DisplayName ("Devuelve 0.12 como incremento por temporada alta")
    public void ShouldReturn010WhenHighSeason(){
        when(temporadaRepository.descuento (Date.valueOf ("2020-12-16"))).thenReturn (-0.12f);
        when(temporadaRepository.descuento (Date.valueOf ("2020-12-19"))).thenReturn (-0.12f);
        assertThat (precioService.calculaTemporada (Date.valueOf ("2020-12-16"), Date.valueOf ("2020-12-19"))).isEqualTo (-0.12f);
    }

    @Test
    @DisplayName ("Devuelve 0 como descuento por estar fuera de ninguna temporada")
    public void ShouldReturn0WhenNoSeason(){
        when(temporadaRepository.descuento (Date.valueOf ("2021-01-19"))).thenReturn (0.00f);
        when(temporadaRepository.descuento (Date.valueOf ("2021-01-21"))).thenReturn (0.00f);
        assertThat (precioService.calculaTemporada (Date.valueOf ("2021-02-19"), Date.valueOf ("2021-02-21"))).isEqualTo (0.00f);
    }

    @Test
    @DisplayName ("Devuelve 0.06 como incremento por estar entre fuera de temporada y temporada alta")
    public void ShouldReturn006WhenCheckInIsOutOfSeasonAndCheckOutIsHighSeason(){
        when(temporadaRepository.descuento (Date.valueOf ("2020-12-12"))).thenReturn (0.00f);
        when(temporadaRepository.descuento (Date.valueOf ("2020-12-15"))).thenReturn (-0.12f);
        assertThat (precioService.calculaTemporada (Date.valueOf ("2020-12-12"), Date.valueOf ("2020-12-15"))).isEqualTo (-0.06f);
    }

    @Test
    @DisplayName ("Devuelve 0.06 como incremento por estar entre temporada alta y fuera de temporada")
    public void ShouldReturn006WhenCheckInHighSeasonAndCheckOutIsNoSeason(){
        when(temporadaRepository.descuento (Date.valueOf ("2021-01-08"))).thenReturn (-0.12f);
        when(temporadaRepository.descuento (Date.valueOf ("2021-01-12"))).thenReturn (0.00f);
        assertThat (precioService.calculaTemporada (Date.valueOf ("2021-01-08"), Date.valueOf ("2021-01-12"))).isEqualTo (-0.06f);
    }

}
