package com.hotel.java.api;
import com.hotel.java.application.dto.*;
import com.hotel.java.application.models.*;
import com.hotel.java.application.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("showAllBookings")
    public List<ReservaModel> viewList(){
        List<ReservaModel> reservas = reservaService.listReservas ();
        if(reservas.size ()>0)
            return reservas;
        return null;
    }

    @GetMapping("showBookingById/{id}")
    public ReservaModel showBooking(@PathVariable ("id") long id){
       ReservaModel reservaModel = this.reservaService.listReservaById (id);
        return reservaModel;
    }

    @PostMapping("newBooking")
    public long newBooking (@RequestBody ReservaDtoModel reservaDtoModel){
        HabitacionModel habitacionModel = habitacionService.showHabitacionByID (reservaDtoModel.getHabId ());
        ClienteModel clienteReserva = clienteService.buscaId (reservaDtoModel.getId_cliente ());
        ReservaModel reservaModel = new ReservaModel (reservaDtoModel.getCheckIn (), reservaDtoModel.getCheckOut (), (float) reservaDtoModel.getPrecioHab (), clienteReserva, habitacionModel);
        long result = this.reservaService.operateReserva (reservaModel, "new");
        if(result>0)
            return result;
        return 0;
    }
}
