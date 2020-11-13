package com.hotel.java.api;
import com.hotel.java.application.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api")
public class DateController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("listDate/{id}")
    public List<Date> fechas(@PathVariable ("id") long id){
        List<java.sql.Date> fechas = this.reservaService.listaDate (id);
        return fechas;
    }
}
