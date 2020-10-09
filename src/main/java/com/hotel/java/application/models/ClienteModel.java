package com.hotel.java.application.models;

public class ClienteModel {
    private long id;
    private String nombre;
    private String apellido;
    private String email;

    public ClienteModel(String nombre, String apellido, String email) {
        setNombre (nombre);
        setApellido (apellido);
        setEmail (email);
    }

    public ClienteModel(long id, String nombre, String apellido, String email) {
        setId (id);
        setNombre (nombre);
        setApellido (apellido);
        setEmail (email);
    }

    public ClienteModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClienteModel{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
