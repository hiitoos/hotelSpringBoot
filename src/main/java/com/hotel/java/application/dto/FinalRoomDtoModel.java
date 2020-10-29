package com.hotel.java.application.dto;

import com.hotel.java.application.models.HabitacionModel;
import java.sql.Date;

import java.util.List;

public class FinalRoomDtoModel {
    HabitacionModel habitacion;
    List<Date> fechas;

    public FinalRoomDtoModel(HabitacionModel habitacion, List<Date> fechas) {
        this.habitacion = habitacion;
        this.fechas = fechas;
    }

    public HabitacionModel getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(HabitacionModel habitacion) {
        this.habitacion = habitacion;
    }

    public List<Date> getFechas() {
        return fechas;
    }

    public void setFechas(List<Date> fechas) {
        this.fechas = fechas;
    }
}
