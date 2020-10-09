package com.hotel.java.ui.controllers;

import com.hotel.java.application.models.ClienteModel;
import com.hotel.java.application.models.LoginModel;
import com.hotel.java.application.dto.SignupFormDtoModel;
import com.hotel.java.application.models.TipoModel;
import com.hotel.java.application.services.ClienteService;
import com.hotel.java.application.services.LoginService;
import com.hotel.java.application.services.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")

public class LoginController {

    private final ClienteService clienteService;
    private final LoginService loginService;
    private final TipoService tipoService;

    @Autowired
    public LoginController(ClienteService client, LoginService loginService, TipoService tipoService) {
        this.clienteService = client;
        this.loginService = loginService;
        this.tipoService = tipoService;
    }

    @GetMapping("login")
    public ModelAndView login() {
        return new ModelAndView ("login");
    }

    @PostMapping("newUser")
    public String submit(@Valid @ModelAttribute("signUP") SignupFormDtoModel signupFormDtoModel){
        ClienteModel cliente = new ClienteModel (signupFormDtoModel.getNombre (), signupFormDtoModel.getApellido (), signupFormDtoModel.getEmail ());
        LoginModel login = new LoginModel (signupFormDtoModel.getNewUsername (), signupFormDtoModel.getNewPassword (), "ROLE_USER", true, null);
        clienteService.createCliente (cliente, login);
        return "redirect:/login?q=Registrado+Correctamente!";
    }

    @GetMapping
    public ModelAndView index() {
        List<TipoModel> tipos = this.tipoService.showAllTipos ();
        System.out.println (tipos);
        ModelAndView model = new ModelAndView ("index");
        model.addObject ("tipos", tipos);
        return model;
    }
}
