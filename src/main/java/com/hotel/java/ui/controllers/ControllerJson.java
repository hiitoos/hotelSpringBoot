package com.hotel.java.ui.controllers;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.hotel.java.application.models.ReservaModel;
import com.hotel.java.application.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerJson {

    private final ReservaService reservaService;
    private final DateDiffService dateDiffService;
    private final LoginService loginService;
    private final HabitacionService habitacionService;
    private final ClienteService clienteService;

    @Autowired
    public ControllerJson(ReservaService reservaService, DateDiffService dateDiffService, LoginService loginService, HabitacionService habitacionService, ClienteService clienteService) {
        this.reservaService = reservaService;
        this.dateDiffService = dateDiffService;
        this.loginService = loginService;
        this.habitacionService = habitacionService;
        this.clienteService = clienteService;
    }


    @GetMapping("/showReservas")
    public List<?> showAllReservas (){
        List<ReservaModel> reservas = reservaService.listReservas ();
        return reservas;
    }
}
