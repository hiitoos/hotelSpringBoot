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
    String precioHab;

    @NotNull
    @NotEmpty
    String habId;

    @NotNull
    @NotEmpty
    String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Float getPrecioHab() {
        return Float.parseFloat (precioHab);
    }

    public void setPrecioHab(Float precioHab) {
        this.precioHab = String.valueOf (precioHab);
    }

    public Long getHabId() {
        return Long.parseLong (habId);
    }

    public void setHabId(Long habId) {
        this.habId = String.valueOf (habId);
    }
}
