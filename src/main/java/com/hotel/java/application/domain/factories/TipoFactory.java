package com.hotel.java.application.domain.factories;

import com.hotel.java.application.domain.entities.TipoEntity;
import com.hotel.java.application.models.TipoModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TipoFactory {

    public TipoEntity tipoModel2Entity (TipoModel tipoModel){
        TipoEntity tipoEntity = new TipoEntity (
                tipoModel.getId (),
                tipoModel.getNombre (),
                tipoModel.getDescripcion ()
        );
        return tipoEntity;
    }


    public TipoModel tipoEntity2Model (TipoEntity tipoEntity){
        TipoModel tipoModel = new TipoModel (
                tipoEntity.getId (),
                tipoEntity.getNombre (),
                tipoEntity.getDescripcion ()
        );
        return tipoModel;
    }


    public List<TipoEntity> tipoListModel2Entity (List<TipoModel> tipoModels){
        List<TipoEntity> tipoEntities = new ArrayList<> ();
        for (TipoModel tipo : tipoModels){
            TipoEntity tipoEntity =
                    new TipoEntity (
                            tipo.getId (),
                            tipo.getNombre (),
                            tipo.getDescripcion ()
                    );
            tipoEntities.add (tipoEntity);
        }
        return tipoEntities;
    }

    public List<TipoModel> tipoListEntity2Model (List<TipoEntity> tipoEntities){
        List<TipoModel> tipoModels = new ArrayList<> ();
        for (TipoEntity tipo : tipoEntities){
            TipoModel tipoModel =
                    new TipoModel (
                            tipo.getId (),
                            tipo.getNombre (),
                            tipo.getDescripcion ()
                    );
            tipoModels.add (tipoModel);
        }
        return tipoModels;
    }
}
