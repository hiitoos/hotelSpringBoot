package com.hotel.java.application.services;

import java.sql.Date;

public interface DateService {
    double calculateTotalPrice(Date checkIn, Date checkOut, double pricePerNight);
    long getDaysBetweenTwoDates(Date date1, Date date2);
}
