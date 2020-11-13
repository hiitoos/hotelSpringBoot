package com.hotel.java.application.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class ReservaDtoModel {

    @NotNull
    @NotEmpty
    Date checkIn;

    @NotNull
    @NotEmpty
    Date checkOut;

    @NotNull
    @NotEmpty
    double precioHab;

    @NotNull
    @NotEmpty
    long habId;

    @NotNull
    @NotEmpty
    long id_cliente;

    public long getId_cliente() {
        return 1;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public double getPrecioHab() {
        return precioHab;
    }

    public void setPrecioHab(double precioHab) {
        this.precioHab=precioHab;
    }

    public Long getHabId() {
        return habId;
    }

    public void setHabId(Long habId) {
        this.habId = habId;
    }

    public ReservaDtoModel(@NotNull @NotEmpty Date checkIn, @NotNull @NotEmpty Date checkOut, @NotNull @NotEmpty double precioHab, @NotNull @NotEmpty long habId, @NotNull @NotEmpty long id_cliente) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.precioHab = precioHab;
        this.habId = habId;
        this.id_cliente = id_cliente;
    }
}
