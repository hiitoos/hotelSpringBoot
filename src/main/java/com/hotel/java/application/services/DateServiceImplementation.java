package com.hotel.java.application.services;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

@Service
public class DateServiceImplementation implements DateService {

    @Override
    public double calculateTotalPrice(Date checkIn, Date checkOut, double pricePerNight) {
        long nights = getDaysBetweenTwoDates(checkIn, checkOut);
        double totalPrice = nights * pricePerNight;
        return totalPrice;
    }

    @Override
    public long getDaysBetweenTwoDates(Date date1, Date date2) {
        return TimeUnit.DAYS.convert((date2.getTime() - date1.getTime()), TimeUnit.MILLISECONDS);
    }

}

