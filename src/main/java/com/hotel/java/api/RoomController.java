package com.hotel.java.api;
import com.hotel.java.application.dto.*;
import com.hotel.java.application.models.*;
import com.hotel.java.application.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping("showAllRooms")
    public List<AllRoomsAndDatesDtoModel> showAllRooms (){
        List<HabitacionModel> habitaciones = habitacionService.showAllHabitaciones();
        List<AllRoomsAndDatesDtoModel> roomsDates = new ArrayList<> ();
        for (HabitacionModel hab : habitaciones){
            AllRoomsAndDatesDtoModel roomsDate = new AllRoomsAndDatesDtoModel (
                    hab.getId (),
                    hab.getCodigo (),
                    hab.getDescripcion (),
                    hab.getPrecio (),
                    hab.getTipoModel (),
                    hab.getNumpersonas (),
                    this.reservaService.listaDate (hab.getId ())
            );
            roomsDates.add(roomsDate);
        }
        if (habitaciones.size ()>0)
            return roomsDates;
        return null; /**Pending show error from null rooms*/
    }

    @GetMapping("showRoomById/{id}")
    public FinalRoomDtoModel showRoomById(@PathVariable ("id") Object hab_id)  {
        long hab = 0;
        try{
            hab = Long.parseLong (String.valueOf (hab_id));
        }catch (Exception ex){
            return new FinalRoomDtoModel (new HabitacionModel (), new ArrayList());
        }
        try {
            HabitacionModel habitacion = habitacionService.showHabitacionByID (hab);
            if (habitacion != null) {
                List<java.sql.Date> fechas = this.reservaService.listaDate (hab);
                return new FinalRoomDtoModel (habitacion, fechas);
            }
        }catch (Exception e){}

        return new FinalRoomDtoModel (new HabitacionModel (), new ArrayList());
    }

}
