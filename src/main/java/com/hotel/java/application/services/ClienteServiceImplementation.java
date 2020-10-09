package com.hotel.java.application.services;

import com.hotel.java.application.domain.entities.ClienteEntity;
import com.hotel.java.application.domain.factories.ClienteFactory;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.repositories.MasterRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImplementation implements ClienteService{
    private final MasterRepository masterRepository;
    private ClienteFactory clienteFactory;
    private ClienteEntity clienteEntity;

    public ClienteServiceImplementation(MasterRepository masterRepository, ClienteFactory clienteFactory) {
        this.masterRepository = masterRepository;
        this.clienteFactory = clienteFactory;
    }

    @Override
    public long createCliente(ClienteModel cliente) {
        clienteEntity = this.clienteFactory.clienteModel2Entity (cliente);
        long res = this.masterRepository.newSignUp (clienteEntity);
        return res;
    }

    @Override
    public ClienteModel buscaId(long id) {
        ClienteEntity clienteEntity = (ClienteEntity) this.masterRepository.listarById (id, ClienteEntity.class);
        ClienteModel clienteModel = this.clienteFactory.clienteEntity2Model (clienteEntity);
        return clienteModel;
    }

}
