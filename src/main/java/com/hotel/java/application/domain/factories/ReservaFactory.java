package com.hotel.java.application.domain.factories;



import com.hotel.java.application.domain.entities.ReservaEntity;
import com.hotel.java.application.models.ReservaModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ReservaFactory {
    ClienteFactory clienteFactory = new ClienteFactory ();
    HabitacionFactory habitacionFactory = new HabitacionFactory ();


    public ReservaEntity reservaModel2Entity (ReservaModel reservaModel){
        ReservaEntity reservaEntity =
                new ReservaEntity (
                        reservaModel.getId (),
                        reservaModel.getFechaIn (),
                        reservaModel.getFechaOut (),
                        reservaModel.getPrecioTotal (),
                        clienteFactory.clienteModel2Entity (reservaModel.getCliente ()),
                        habitacionFactory.habitacionModel2Entity (reservaModel.getHabitacion ())
                        );
        return reservaEntity;
    }

    public ReservaModel reservaEntity2Model (ReservaEntity reservaEntity){
        ReservaModel reservaModel =
                new ReservaModel (
                        reservaEntity.getId (),
                        reservaEntity.getFechaIn (),
                        reservaEntity.getFechaOut (),
                        reservaEntity.getPrecioTotal (),
                        clienteFactory.clienteEntity2Model (reservaEntity.getClienteEntity_id ()),
                        habitacionFactory.habitacionEntity2Model (reservaEntity.getHabitacion ())
                );
        return reservaModel;
    }

    public List<ReservaModel> reservaListEntity2Model(List<ReservaEntity> reservaEntities){
        List<ReservaModel> reservaModels = new ArrayList<> ();
        for (ReservaEntity reserva : reservaEntities){
            ReservaModel reservaModel =
                    new ReservaModel (
                            reserva.getId (),
                            reserva.getFechaIn (),
                            reserva.getFechaOut (),
                            reserva.getPrecioTotal (),
                            clienteFactory.clienteEntity2Model (reserva.getClienteEntity_id ()),
                            habitacionFactory.habitacionEntity2Model(reserva.getHabitacion())
                    );
            reservaModels.add (reservaModel);
        }
        return reservaModels;
    }

}
