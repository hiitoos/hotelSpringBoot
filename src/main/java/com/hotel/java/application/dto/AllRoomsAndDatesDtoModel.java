package com.hotel.java.application.dto;

import com.hotel.java.application.models.HabitacionModel;
import com.hotel.java.application.models.TipoModel;

import java.sql.Date;
import java.util.List;

public class AllRoomsAndDatesDtoModel {
    private long id;
    private String codigo;
    private String descripcion;
    private float precio;
    TipoModel tipoModel;
    private int numpersonas;
    List<Date> fechasOcupadas;

    public AllRoomsAndDatesDtoModel(long id, String codigo, String descripcion, float precio, TipoModel tipoModel, int numpersonas, List<Date> fechasOcupadas) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipoModel = tipoModel;
        this.numpersonas = numpersonas;
        this.fechasOcupadas = fechasOcupadas;
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

    public List<Date> getFechasOcupadas() {
        return fechasOcupadas;
    }

    public void setFechasOcupadas(List<Date> fechasOcupadas) {
        this.fechasOcupadas = fechasOcupadas;
    }
}
