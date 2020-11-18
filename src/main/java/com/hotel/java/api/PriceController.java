package com.hotel.java.api;
import com.hotel.java.application.dto.*;
import com.hotel.java.application.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api")
public class PriceController {

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private DateService dateService;

    @Autowired
    private PrecioService precioService;

    @PostMapping("calculaPrecio")
    public double calculaPrecio (@RequestBody PrecioDtoModel datosPrecio) throws Exception {
        double precioHab = this.habitacionService.showHabitacionByID (datosPrecio.getId ()).getPrecio ();
        long dias = this.dateService.getDaysBetweenTwoDates (datosPrecio.checkIn, datosPrecio.checkOut);
        double descTemporada = this.precioService.calculaTemporada (datosPrecio.checkIn, datosPrecio.checkOut);
        double subTotal = this.dateService.calculateTotalPrice (datosPrecio.checkIn, datosPrecio.checkOut, precioHab);
        double descuento = this.precioService.calculaDescuento (dias);
        descuento += descTemporada;
        double total = subTotal - (subTotal*descuento);
        return total!=0?total:precioHab;
    }
}
