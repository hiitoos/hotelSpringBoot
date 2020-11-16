package com.hotel.java.application.services;

import com.hotel.java.application.models.ReservaModel;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ReservaService {
    List<ReservaModel> listReservas();
    ReservaModel listReservaById(long id);
    /*void newReserva(ReservaModel reservaModel);
    void deleteReserva(ReservaModel reservaModel);
    void updateReserva(ReservaModel reservaModel);*/
    long operateReserva(ReservaModel reservaModel, String modo);
    List<Date> listaDate (long id);
}
