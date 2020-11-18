package com.hotel.java.application.services;

import java.sql.Date;

public interface PrecioService {
    double calculaDescuento(long dias);
    float calculaTemporada(Date in, Date out) throws Exception;
}
