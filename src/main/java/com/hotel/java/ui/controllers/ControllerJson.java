package com.hotel.java.ui.controllers;

import com.hotel.java.application.dto.FinalRoomDtoModel;
import com.hotel.java.application.dto.PrecioDtoModel;
import com.hotel.java.application.dto.ReservaDtoModel;
import com.hotel.java.application.dto.SignupFormDtoModel;
import com.hotel.java.application.models.*;
import com.hotel.java.application.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api")
public class ControllerJson {

    private final ReservaService reservaService;
    private final DateDiffService dateDiffService;
    private final LoginService loginService;
    private final HabitacionService habitacionService;
    private final ClienteService clienteService;
    private final TipoService tipoService;
    private final PrecioService precioService;

    @Autowired
    public ControllerJson(ReservaService reservaService, TipoService tipoService, DateDiffService dateDiffService, LoginService loginService, HabitacionService habitacionService, ClienteService clienteService, PrecioService precioService) {
        this.reservaService = reservaService;
        this.tipoService = tipoService;
        this.dateDiffService = dateDiffService;
        this.loginService = loginService;
        this.habitacionService = habitacionService;
        this.clienteService = clienteService;
        this.precioService =precioService;
    }

    /***************************-------- HABITACIONES --------***************************/

    @GetMapping("showAllRooms")
    public List<HabitacionModel> showAllRooms (){
        List<HabitacionModel> habitaciones = habitacionService.showAllHabitaciones();
        if (habitaciones.size ()>0)
            return habitaciones;
        return null; /**Pending show error from null rooms*/
    }

    @GetMapping("showRoomsByGuest")
    public List<HabitacionModel> showroomsByGuest(
        @RequestParam(value="checkIn")String checkIn,
        @RequestParam(value="checkOut")String checkOut,
        @RequestParam(value="numguest") int numguest) {
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
        Date check_In = null;
        Date check_Out = null;
        try {
            check_In = formatter.parse (checkIn);
            check_Out = formatter.parse (checkOut);
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        List<HabitacionModel> habitaciones = habitacionService.showHabitacionesByGuest (numguest);
        long diffInMillis = check_Out.getTime () - check_In.getTime ();
        if (diffInMillis < 0) {
            return null; /** Pending show date errors*/
        }
        return habitaciones;
    }

    @GetMapping("showRoomByType/{id}")
    public List<HabitacionModel> showRoomByType(@PathVariable("id") long tipo_id) {
        List<HabitacionModel> habitaciones = habitacionService.showHabitacionesByTipoID(tipo_id);
        if (habitaciones.size ()>0)
            return habitaciones;
        return null; /**Pending show error from null room*/
    }

    @GetMapping("showRoomById/{id}")
    public FinalRoomDtoModel showRoomById(@PathVariable ("id") long hab_id) {
        HabitacionModel habitacion = habitacionService.showHabitacionByID(hab_id);
        if (habitacion!= null) {
//            ModelAndView model = new ModelAndView ("hab_final");
//            model.addObject ("habitacion", habitacion);
            List<java.sql.Date> fechas = this.reservaService.listaDate (hab_id);
            return new FinalRoomDtoModel (habitacion, fechas);
        }
        return null; /**Pending show error from null room*/
    }

    /***************************-------- RESERVAS --------***************************/

    @GetMapping("showAllBookings")
    public List<ReservaModel> viewList(){
        List<ReservaModel> reservas = reservaService.listReservas ();
        if(reservas.size ()>0)
            return reservas;
        return null; /**Pending show erro from null room*/
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

    /***************************-------- Login --------***************************/

    @PostMapping("newUser")
    public String submit(@RequestBody @Valid SignupFormDtoModel signupFormDtoModel){
        ClienteModel cliente = new ClienteModel (signupFormDtoModel.getNombre (), signupFormDtoModel.getApellido (), signupFormDtoModel.getEmail ());
        LoginModel login = new LoginModel (signupFormDtoModel.getNewUsername (), signupFormDtoModel.getNewPassword (), "ROLE_USER", true, null);
        clienteService.createCliente (cliente, login);
        return "redirect:/login?q=Registrado+Correctamente!";
    }

    /***************************-------- Index --------***************************/

    @GetMapping
    public List<TipoModel> index() {
        List<TipoModel> tipos = this.tipoService.showAllTipos ();
        return tipos;
    }

    @GetMapping("listDate/{id}")
    public List<java.sql.Date> fechas(@PathVariable ("id") long id){
        List<java.sql.Date> fechas = this.reservaService.listaDate (id);
        return fechas;
    }

    /***************************-------- Precio --------***************************/
    @PostMapping("calculaPrecio")
    public double calculaPrecio (@RequestBody PrecioDtoModel datosPrecio){
        double precioHab = this.habitacionService.showHabitacionByID (datosPrecio.getId ()).getPrecio ();
        long dias = this.dateDiffService.getDaysBetweenTwoDates (datosPrecio.checkIn, datosPrecio.checkOut);
        double subTotal = this.dateDiffService.calculateTotalPrice (datosPrecio.checkIn, datosPrecio.checkOut, precioHab);
        double descuento = this.precioService.calculaDescuento (dias);
        double total = subTotal - (subTotal*descuento);
        return total;
    }

}
