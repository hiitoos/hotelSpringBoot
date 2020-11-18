package com.hotel.java.application.services;

import com.hotel.java.application.domain.entities.ClienteEntity;
import com.hotel.java.application.domain.factories.ClienteFactory;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.LoginModel;
import com.hotel.java.application.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImplementation implements ClienteService{

    private final ClienteRepository clienteRepository;
    private final LoginService loginService;
    private final ClienteFactory clienteFactory;

    private ClienteEntity clienteEntity;

    public ClienteServiceImplementation(ClienteRepository clienteRepository, LoginService loginService, ClienteFactory clienteFactory) {
        this.clienteRepository = clienteRepository;
        this.loginService = loginService;
        this.clienteFactory = clienteFactory;
    }

    @Override
    public long createCliente(ClienteModel cliente, LoginModel login) {
        clienteEntity = this.clienteFactory.clienteModel2Entity (cliente);

        ClienteEntity clienteE  = this.clienteRepository.save(clienteEntity);
        long idClient = clienteE.getId ();
        this.clienteRepository.flush();
        cliente.setId (idClient);
        clienteEntity.setId (idClient);

        login.setClienteModel (cliente);
        this.loginService.createLogin (login);

        return idClient;
    }

    @Override
    public ClienteModel buscaId(long id) {
        clienteEntity = this.clienteRepository.findById (id).orElseThrow(RuntimeException::new);
        return this.clienteFactory.clienteEntity2Model (clienteEntity);
    }

}
