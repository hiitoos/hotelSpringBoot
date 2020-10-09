package com.hotel.java.application.services;

import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.LoginModel;

public interface LoginService {
   void createLogin(LoginModel loginmodel);
   LoginModel buscaClientIdFromUsername(String username);
}
