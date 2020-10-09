package com.hotel.java.application.services;

import com.hotel.java.application.models.HabitacionModel;

import java.util.List;

public interface HabitacionService {
    List<HabitacionModel> showAllHabitaciones();
    List<HabitacionModel> showHabitacionesByGuest(int numGuest);
    List<HabitacionModel> showHabitacionesByTipo();
    List<HabitacionModel> showHabitacionesByTipoID(long id);
    HabitacionModel showHabitacionByID(long hab_id);
}
