package com.hotel.java.application.services;

import com.hotel.java.application.domain.entities.TipoEntity;
import com.hotel.java.application.domain.factories.TipoFactory;
import com.hotel.java.application.models.TipoModel;
import com.hotel.java.application.repositories.MasterRepository;
import com.hotel.java.application.repositories.TipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServiceImplementation implements TipoService{

    private final TipoRepository tipoRepository;
    private final TipoFactory tipoFactory;
    private List<TipoEntity> tipoEntities;

    public TipoServiceImplementation(TipoRepository tipoRepository, TipoFactory tipoFactory) {
        this.tipoRepository = tipoRepository;
        this.tipoFactory = tipoFactory;
    }


    @Override
    public List<TipoModel> showAllTipos() {
        tipoEntities = this.tipoRepository.findAll ();
        List<TipoModel> tipoModels = this.tipoFactory.tipoListEntity2Model(tipoEntities);
        return tipoModels;
    }
}
