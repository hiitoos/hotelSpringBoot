package com.hotel.java.application.domain.factories;

import com.hotel.java.application.domain.entities.ClienteEntity;
import com.hotel.java.application.domain.entities.LoginEntity;
import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.LoginModel;
import org.springframework.stereotype.Component;

@Component
public class LoginFactory {
    private ClienteFactory clienteFactory = new ClienteFactory ();



    public LoginEntity loginModel2Entity (LoginModel loginModel){
        LoginEntity loginEntity = new LoginEntity (
                     loginModel.getId (),
                     loginModel.getUsername (),
                     loginModel.getPassword (),
                     loginModel.getRole (),
                     loginModel.isEnabled (),
                     clienteFactory.clienteModel2Entity (loginModel.getClienteModel ())
             );
        return loginEntity;
    }


    public LoginModel loginEntity2Model (LoginEntity loginEntity){
        LoginModel loginModel = new LoginModel (
                loginEntity.getId (),
                loginEntity.getUsername (),
                loginEntity.getPassword (),
                loginEntity.getRole (),
                loginEntity.isEnabled (),
                clienteFactory.clienteEntity2Model (loginEntity.getClienteEntity ())
        );
        return loginModel;
    }

}
