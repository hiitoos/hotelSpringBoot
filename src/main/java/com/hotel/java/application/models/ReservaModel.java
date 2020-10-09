package com.hotel.java.application.models;

public class ReservaModel {
    private long id;
    private java.sql.Date fechaIn;
    private java.sql.Date fechaOut;
    private float precioTotal;
    ClienteModel cliente;
    HabitacionModel habitacion;

    public ReservaModel(java.sql.Date fechaIn, java.sql.Date fechaOut, float precioTotal, ClienteModel cliente, HabitacionModel habitacion) {
        setFechaIn (fechaIn);
        setFechaOut (fechaOut);
        setPrecioTotal (precioTotal);
        setCliente (cliente);
        setHabitacion (habitacion);
    }

    public ReservaModel(long id, java.sql.Date fechaIn, java.sql.Date fechaOut, float precioTotal, ClienteModel cliente, HabitacionModel habitacion) {
        setId (id);
        setFechaIn (fechaIn);
        setFechaOut (fechaOut);
        setPrecioTotal (precioTotal);
        setCliente (cliente);
        setHabitacion (habitacion);
    }

    public ReservaModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public java.sql.Date getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(java.sql.Date fechaIn) {
        this.fechaIn = fechaIn;
    }

    public java.sql.Date getFechaOut() {
        return fechaOut;
    }

    public void setFechaOut(java.sql.Date fechaOut) {
        this.fechaOut = fechaOut;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel clienteModel) {
        this.cliente = clienteModel;
    }

    public HabitacionModel getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(HabitacionModel habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public String toString() {
        return "ReservaModel{" +
                "fechaIn=" + fechaIn +
                ", fechaOut=" + fechaOut +
                ", precioTotal=" + precioTotal +
                ", clienteModel=" + cliente +
                ", habitacionModels=" + habitacion +
                '}';
    }
}
