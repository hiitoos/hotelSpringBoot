package com.hotel.java.application.services;

import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.LoginModel;

public interface ClienteService {
    long createCliente(ClienteModel cliente, LoginModel login);
    ClienteModel buscaId(long id);
}
