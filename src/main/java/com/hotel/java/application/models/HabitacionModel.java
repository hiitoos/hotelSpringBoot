package com.hotel.java.application.models;

import com.hotel.java.application.services.HabitacionService;

import java.util.List;

public class HabitacionModel {
    private long id;
    private String codigo;
    private String descripcion;
    private float precio;
    TipoModel tipoModel;
    private int numpersonas;

    public HabitacionModel(){}

    public HabitacionModel(String codigo, String descripcion, float precio, TipoModel tipoModel, int numpersonas) {
        setCodigo (codigo);
        setDescripcion (descripcion);
        setPrecio (precio);
        setTipoModel (tipoModel);
        setNumpersonas(numpersonas);
    }

    public HabitacionModel(long id, String codigo, String descripcion, float precio, TipoModel tipoModel, int numpersonas) {
        setId (id);
        setCodigo (codigo);
        setDescripcion (descripcion);
        setPrecio (precio);
        setTipoModel (tipoModel);
        setNumpersonas(numpersonas);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public TipoModel getTipoModel() {
        return tipoModel;
    }

    public void setTipoModel(TipoModel tipoModel) {
        this.tipoModel = tipoModel;
    }

    public int getNumpersonas() {
        return numpersonas;
    }

    public void setNumpersonas(int numpersonas) {
        this.numpersonas = numpersonas;
    }

    @Override
    public String toString() {
        return "HabitacionModel{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", tipoModel=" + tipoModel +
                '}';
    }
}
