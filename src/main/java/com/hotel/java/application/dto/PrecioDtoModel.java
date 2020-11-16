package com.hotel.java.application.dto;

import java.sql.Date;

public class PrecioDtoModel {
    public long id;
    public Date checkIn;
    public Date checkOut;

    public PrecioDtoModel(long id, Date checkIn, Date checkOut) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


}
