package com.hotel.java.application.services;

import com.hotel.java.application.domain.entities.HabitacionEntity;
//import com.hotel.java.application.domain.factories.ClienteFactory;
import com.hotel.java.application.domain.factories.HabitacionFactory;
//import com.hotel.java.application.domain.factories.;
import com.hotel.java.application.models.HabitacionModel;
import com.hotel.java.application.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HabitacionServiceImplementation implements HabitacionService{
    private final HabitacionRepository habitacionRepository;
    private final HabitacionFactory habitacionFactory ;
    private List<HabitacionEntity> habitacionEntities;
    private List<HabitacionModel> habitacionModels;
    private HabitacionEntity habitacionEntity;

    @Autowired
    public HabitacionServiceImplementation(HabitacionRepository habitacionRepository,
                                        HabitacionFactory habitacionFactory) {
        this.habitacionRepository = habitacionRepository;
        this.habitacionFactory = habitacionFactory;
    }

    @Override
    public List<HabitacionModel> showAllHabitaciones() {
        habitacionEntities = this.habitacionRepository.findAll ();
        habitacionModels = this.habitacionFactory.habitacionListEntity2Model(habitacionEntities);
        return habitacionModels;
    }

    @Override
    public List<HabitacionModel> showHabitacionesByGuest(int numGuest) {
        habitacionEntities = this.habitacionRepository.findAll ();
        habitacionModels = this.habitacionFactory.habitacionListEntity2Model(habitacionEntities);
        List<HabitacionModel> habitaciones = new ArrayList();
        for (HabitacionModel habitacion : habitacionModels){
            if(habitacion.getNumpersonas ()>=numGuest){
                habitaciones.add (habitacion);
            }
        }
        return habitaciones;
    }

    @Override
    public List<HabitacionModel> showHabitacionesByTipo() {
        habitacionEntities = this.habitacionRepository.findAll((Sort.by("tipo")));
        List<HabitacionModel> habitacionModels = this.habitacionFactory.habitacionListEntity2Model (habitacionEntities);
        return habitacionModels;
    }

    @Override
    public List<HabitacionModel> showHabitacionesByTipoID(long id) {
        habitacionEntities = this.habitacionRepository.findAll ();
        List<HabitacionModel> habitacionModels = this.habitacionFactory.habitacionListEntity2Model (habitacionEntities);
        List<HabitacionModel> habitaciones = new ArrayList();
        for (HabitacionModel habitacion : habitacionModels){
            if (habitacion.getTipoModel ().getId () == id){
                habitaciones.add (habitacion);
            }
        }
        return habitaciones;
    }

    @Override
    public HabitacionModel showHabitacionByID(long hab_id) {
        habitacionEntity = this.habitacionRepository.findById (hab_id).orElseThrow(RuntimeException::new);
        HabitacionModel habitacionModel = this.habitacionFactory.habitacionEntity2Model(habitacionEntity);
        return habitacionModel;
    }
}
