package com.hotel.java.application.services;

import com.hotel.java.application.domain.entities.LoginEntity;
import com.hotel.java.application.domain.factories.ClienteFactory;
import com.hotel.java.application.domain.factories.LoginFactory;
import com.hotel.java.application.models.LoginModel;
import com.hotel.java.application.repositories.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImplementation implements LoginService{
    private final ClienteFactory clienteFactory;
    private final LoginRepository loginRepository;
    private final LoginFactory loginFactory;
    private LoginEntity loginEntity;
    private LoginModel loginModel;

    public LoginServiceImplementation(ClienteFactory clienteFactory, LoginRepository loginRepository, LoginFactory loginFactory) {
        this.clienteFactory = clienteFactory;
        this.loginRepository = loginRepository;
        this.loginFactory = loginFactory;
    }

    @Override
    public long createLogin(LoginModel loginmodel) {
        loginEntity = this.loginFactory.loginModel2Entity(loginmodel);
        long id = this.loginRepository.save (loginEntity).getId ();
        this.loginRepository.flush ();
        return id;
    }

    @Override
    public LoginModel buscaClientIdFromUsername(String username){
        List<LoginEntity> loginEntities = this.loginRepository.findAll ();
        for (LoginEntity loginEntity : loginEntities){
            if (loginEntity.getUsername ().equals (username)){
                loginModel = loginFactory.loginEntity2Model (loginEntity);
            }
        }
        return loginModel;
    }
}
