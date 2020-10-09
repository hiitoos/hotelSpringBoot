package com.hotel.java.application.domain.factories;


import com.hotel.java.application.domain.entities.HabitacionEntity;
import com.hotel.java.application.domain.entities.ReservaEntity;
import com.hotel.java.application.models.HabitacionModel;
import com.hotel.java.application.models.ReservaModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HabitacionFactory {
    TipoFactory tipoFactory = new TipoFactory ();

    public HabitacionEntity habitacionModel2Entity (HabitacionModel habitacionModel){
        HabitacionEntity habitacionEntity =
                new HabitacionEntity (
                        habitacionModel.getId (),
                        habitacionModel.getCodigo (),
                        habitacionModel.getDescripcion (),
                        habitacionModel.getPrecio (),
                        tipoFactory.tipoModel2Entity(habitacionModel.getTipoModel ()),
                        habitacionModel.getNumpersonas()
                );
        return habitacionEntity;
    }

    public HabitacionModel habitacionEntity2Model (HabitacionEntity habitacionEntity){
        HabitacionModel habitacionModel =
                new HabitacionModel (
                        habitacionEntity.getId (),
                        habitacionEntity.getCodigo (),
                        habitacionEntity.getDescripcion (),
                        habitacionEntity.getPrecio (),
                        tipoFactory.tipoEntity2Model(habitacionEntity.getTipo ()),
                        habitacionEntity.getNumpersonas()
                );
        return habitacionModel;
    }

    public List<HabitacionModel> habitacionListEntity2Model(List<HabitacionEntity> habitacionEntities){
        List<HabitacionModel> habitacionModels = new ArrayList<> ();
        for (HabitacionEntity habitacion : habitacionEntities){
            HabitacionModel habitacionModel =
                    new HabitacionModel (
                            habitacion.getId (),
                            habitacion.getCodigo (),
                            habitacion.getDescripcion (),
                            habitacion.getPrecio (),
                            tipoFactory.tipoEntity2Model (habitacion.getTipo ()),
                            habitacion.getNumpersonas()
                    );
            habitacionModels.add (habitacionModel);
        }
        return habitacionModels;
    }

}
