package com.hotel.java.application.services;

import com.hotel.java.application.models.ReservaModel;

import java.sql.Date;
import java.util.List;

public interface ReservaService {
    List<ReservaModel> listReservas();
    ReservaModel listReservaById(long id);
    /*void newReserva(ReservaModel reservaModel);
    void deleteReserva(ReservaModel reservaModel);
    void updateReserva(ReservaModel reservaModel);*/
    boolean operateReserva(ReservaModel reservaModel, String modo);
    List<Date> listaDate (long id);
}
