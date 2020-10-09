package com.hotel.java.application.services;

import com.hotel.java.application.domain.entities.ClienteEntity;
import com.hotel.java.application.domain.entities.LoginEntity;
import com.hotel.java.application.domain.factories.ClienteFactory;
import com.hotel.java.application.domain.factories.LoginFactory;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.LoginModel;
import com.hotel.java.application.repositories.ClienteRepository;
import com.hotel.java.application.repositories.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImplementation implements ClienteService{
    private final ClienteRepository clienteRepository;
    private final LoginService loginService;
    private LoginFactory loginFactory;
    private ClienteFactory clienteFactory;
    private ClienteEntity clienteEntity;

    public ClienteServiceImplementation(ClienteRepository clienteRepository, LoginService loginService, LoginFactory loginFactory, ClienteFactory clienteFactory) {
        this.clienteRepository = clienteRepository;
        this.loginFactory = loginFactory;
        this.loginService = loginService;
        this.clienteFactory = clienteFactory;
    }

    @Override
    public void createCliente(ClienteModel cliente, LoginModel login) {
        clienteEntity = this.clienteFactory.clienteModel2Entity (cliente);

        long idClient = this.clienteRepository.save(clienteEntity).getId ();
        this.clienteRepository.flush();
        cliente.setId (idClient);
        clienteEntity.setId (idClient);

        login.setClienteModel (cliente);
        this.loginService.createLogin (login);

    }

    @Override
    public ClienteModel buscaId(long id) {
        ClienteEntity clienteEntity = this.clienteRepository.findById (id).orElseThrow(RuntimeException::new);
        ClienteModel clienteModel = this.clienteFactory.clienteEntity2Model (clienteEntity);
        return clienteModel;
    }

}
