package com.hotel.java.application.models;

import java.util.List;

public class LoginModel {
    private long id;
    private String username;
    private String password;
    private String role;
    private boolean enabled;
    ClienteModel clienteModel;

    public LoginModel(String username, String password, String role, boolean enabled, ClienteModel clienteModel) {
        setUsername (username);
        setPassword (password);
        setRole (role);
        setEnabled (enabled);
        setClienteModel (clienteModel);
    }

    public LoginModel(long id, String username, String password, String role, boolean enabled, ClienteModel clienteModel) {
        setId(id);
        setUsername (username);
        setPassword (password);
        setRole (role);
        setEnabled (enabled);
        setClienteModel (clienteModel);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "usuario='" + username + '\'' +
                ", password='" + password + '\'' +
                ", clienteModel=" + clienteModel +
                '}';
    }
}
