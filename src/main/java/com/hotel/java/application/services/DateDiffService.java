package com.hotel.java.application.services;

import java.sql.Date;

public interface DateDiffService {
    double calculateTotalPrice(Date checkIn, Date checkOut, double pricePerNight);
    Date stringToDate(String date);
    long getDaysBetweenTwoDates(Date date1, Date date2);
}
