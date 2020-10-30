package com.hotel.java.ui.controllers;

import com.hotel.java.application.dto.ReservaDtoModel;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.HabitacionModel;
import com.hotel.java.application.models.ReservaModel;
import com.hotel.java.application.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final DateDiffService dateDiffService;
    private final LoginService loginService;
    private final HabitacionService habitacionService;
    private final ClienteService clienteService;

    @Autowired
    public ReservaController(ReservaService reservaService, DateDiffService dateDiffService, LoginService loginService, HabitacionService habitacionService, ClienteService clienteService) {
        this.reservaService = reservaService;
        this.dateDiffService = dateDiffService;
        this.loginService = loginService;
        this.habitacionService = habitacionService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public ModelAndView test(){
        ModelAndView mav = new ModelAndView("reservas");
        return mav;
    }


    @GetMapping("showall")
    public ModelAndView viewList(){
        List<ReservaModel> reservas = reservaService.listReservas ();
        ModelAndView mav = new ModelAndView("reservas");
        mav.addObject("reservas", reservas);
        return mav;
    }

    @GetMapping("newReserva")
    public ModelAndView newReserva(@Valid @ModelAttribute("reserva")ReservaDtoModel reservaDtoModel) {
        float precioTotal = (float) dateDiffService.calculateTotalPrice (reservaDtoModel.getCheckIn (), reservaDtoModel.getCheckOut (), reservaDtoModel.getPrecioHab ());
        //ClienteModel clienteReserva = clienteService.buscaId (loginService.buscaClientIdFromUsername (reservaDtoModel.getUsername ()).getId ());
        HabitacionModel habitacionModel = habitacionService.showHabitacionByID (reservaDtoModel.getHabId ());
        //ReservaModel reservaModel = new ReservaModel (reservaDtoModel.getCheckIn (), reservaDtoModel.getCheckOut (), precioTotal, clienteReserva, habitacionModel);
        //this.reservaService.operateReserva (reservaModel, "new");
        return new ModelAndView ("index");
    }


}
