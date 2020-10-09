package com.hotel.java.application.models;

public class TipoModel {
    private long id;
    private String nombre;
    private String descripcion;

    public TipoModel(String nombre, String descripcion) {
        setNombre (nombre);
        setDescripcion (descripcion);
    }

    public TipoModel(long id, String nombre, String descripcion) {
        setId (id);
        setNombre (nombre);
        setDescripcion (descripcion);
    }

    public TipoModel() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoModel{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
