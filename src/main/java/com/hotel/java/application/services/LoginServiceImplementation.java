package com.hotel.java.application.services;

import com.hotel.java.application.domain.entities.ClienteEntity;
import com.hotel.java.application.domain.entities.LoginEntity;
import com.hotel.java.application.domain.factories.ClienteFactory;
import com.hotel.java.application.domain.factories.LoginFactory;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.LoginModel;
import com.hotel.java.application.repositories.MasterRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplementation implements LoginService{
    private final ClienteFactory clienteFactory;
    private final MasterRepository masterRepository;
    private final LoginFactory loginFactory;
    private LoginEntity loginEntity;

    public LoginServiceImplementation(ClienteFactory clienteFactory, MasterRepository masterRepository, LoginFactory loginFactory) {
        this.clienteFactory = clienteFactory;
        this.masterRepository = masterRepository;
        this.loginFactory = loginFactory;
    }

    @Override
    public void createLogin(LoginModel loginmodel) {
        loginEntity = this.loginFactory.loginModel2Entity(loginmodel);
        this.masterRepository.newObject (loginEntity, true);
    }

    @Override
    public LoginModel buscaClientIdFromUsername(String username){
        LoginEntity loginEntity = (LoginEntity) this.masterRepository.listarCampo ("username", LoginEntity.class, username);
        LoginModel loginModel = loginFactory.loginEntity2Model (loginEntity);
        return loginModel;
    }
}
