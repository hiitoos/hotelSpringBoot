package com.hotel.java.application.services;

import com.hotel.java.application.domain.entities.TipoEntity;
import com.hotel.java.application.domain.factories.TipoFactory;
import com.hotel.java.application.models.TipoModel;
import com.hotel.java.application.repositories.MasterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServiceImplementation implements TipoService{

    private final MasterRepository masterRepository;
    private final TipoFactory tipoFactory;
    private List<TipoEntity> tipoEntities;

    public TipoServiceImplementation(MasterRepository masterRepository, TipoFactory tipoFactory) {
        this.masterRepository = masterRepository;
        this.tipoFactory = tipoFactory;
    }


    @Override
    public List<TipoModel> showAllTipos() {
        tipoEntities = (List<TipoEntity>)(List<?>)this.masterRepository.listarTodo (TipoEntity.class);
        List<TipoModel> tipoModels = this.tipoFactory.tipoListEntity2Model(tipoEntities);
        return tipoModels;
    }
}
