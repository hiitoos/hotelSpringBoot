package com.hotel.java.application.services;

import com.hotel.java.application.models.ClienteModel;

public interface ClienteService {
    long createCliente(ClienteModel cliente);
    ClienteModel buscaId(long id);
}
