package com.hotel.java.application.services;

import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.LoginModel;

public interface LoginService {
   long createLogin(LoginModel loginmodel);
   LoginModel buscaClientIdFromUsername(String username);
}
