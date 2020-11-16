package com.hotel.java.application.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reserva")
public class ReservaEntity implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private java.sql.Date fechaIn;
    private java.sql.Date fechaOut;
    private float precioTotal;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="cliente_id")
    private ClienteEntity cliente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_habitacion")
    private HabitacionEntity habitacion;

    public ReservaEntity(long id, java.sql.Date fechaIn, java.sql.Date fechaOut, float precioTotal, ClienteEntity clienteEntity_id, HabitacionEntity habitacion) {
        setId (id);
        setFechaIn (fechaIn);
        setFechaOut (fechaOut);
        setPrecioTotal (precioTotal);
        setClienteEntity_id (clienteEntity_id);
        setHabitacionEntity_id (habitacion);
    }

    public ReservaEntity(java.sql.Date fechaIn, java.sql.Date fechaOut, float precioTotal, ClienteEntity clienteEntity_id, HabitacionEntity habitacionEntity) {
        setFechaIn (fechaIn);
        setFechaOut (fechaOut);
        setPrecioTotal (precioTotal);
        setClienteEntity_id (clienteEntity_id);
        setHabitacionEntity_id (habitacionEntity);
    }

    public ReservaEntity() {
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

    public ClienteEntity getClienteEntity_id() {
        return cliente;
    }

    public void setClienteEntity_id(ClienteEntity clienteEntity) {
        this.cliente = clienteEntity;
    }

    public HabitacionEntity getHabitacion() {
        return habitacion;
    }

    public void setHabitacionEntity_id(HabitacionEntity habitacion) {
        this.habitacion = habitacion;
    }


    @Override
    public String toString() {
        return "ReservaEntity{" +
                "fechaIn=" + fechaIn +
                ", fechaOut=" + fechaOut +
                ", precioTotal=" + precioTotal +
                ", clienteEntity=" + cliente +
                //", habitacionEntity=" + habitacionEntity_id +
                '}';
    }

}
