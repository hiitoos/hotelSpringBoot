package com.hotel.java.ui.controllers;

import com.hotel.java.application.models.HabitacionModel;
import com.hotel.java.application.services.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {
    private final HabitacionService habitacionService;

    @Autowired
    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping("/")
    public ModelAndView showAll() {
        List<HabitacionModel> habitaciones = habitacionService.showAllHabitaciones();
        for (HabitacionModel up: habitaciones){
            System.out.println(up.getTipoModel());
        }
        ModelAndView model = new ModelAndView ("habitaciones");
        model.addObject ("habitaciones", habitaciones);

        return model;
    }

    @GetMapping("/showByGuest/")
    public ModelAndView showbByGuest(
            ModelMap model2,
            @RequestParam(value="checkIn")String checkIn,
            @RequestParam(value="checkOut")String checkOut,
            @RequestParam(value="numguest") int numguest) {
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
        Date check_In = null;
        Date check_Out = null;
        try {
            check_In = formatter.parse(checkIn);
            check_Out = formatter.parse(checkOut);
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        List<HabitacionModel> habitaciones = habitacionService.showHabitacionesByGuest (numguest);
        long diffInMillis = check_Out.getTime () - check_In.getTime ();
        ModelAndView model = new ModelAndView ("habitacion");
        System.out.println ("CheckIn: " + check_In + "\nCheckOut: " + check_Out);
        if (diffInMillis<0) {
            model2.addAttribute ("fechaErronea", "Fechas erroneas");
            return new ModelAndView ("redirect:/", model2);
        }
        model.addObject ("habitacion", habitaciones);

        return model;
    }

    @GetMapping("/showByType/{id}")
    public ModelAndView showByType(@PathVariable ("id") long tipo_id) {

        List<HabitacionModel> habitaciones = habitacionService.showHabitacionesByTipoID(tipo_id);
        for (HabitacionModel up: habitaciones){
            System.out.println(up.getTipoModel());
        }
        ModelAndView model = new ModelAndView ("habitacion");
        model.addObject ("habitacion", habitaciones);

        return model;
    }

    @GetMapping("/showRoom/{id}")
    public ModelAndView showRoom(@PathVariable ("id") long hab_id) {
        HabitacionModel habitacion = habitacionService.showHabitacionByID(hab_id);
        if (habitacion!= null) {
            ModelAndView model = new ModelAndView ("hab_final");
            model.addObject ("habitacion", habitacion);

            return model;
        }
        return new ModelAndView ("404");
    }

}
