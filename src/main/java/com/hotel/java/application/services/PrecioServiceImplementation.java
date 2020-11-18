package com.hotel.java.application.services;

import com.hotel.java.application.repositories.TemporadaRepository;
import org.springframework.aop.AopInvocationException;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PrecioServiceImplementation implements PrecioService{
    private TemporadaRepository temporadaRepository;

    public PrecioServiceImplementation(TemporadaRepository temporadaRepository) {
        this.temporadaRepository = temporadaRepository;
    }

    @Override
    public double calculaDescuento(long dias) {

        if (dias>=10 && dias <15) return 0.05; //5% discount
        if (dias>=15 && dias <20) return 0.10; //10% discount
        if (dias>=20) return 0.2; //20% discount
        return 0; //0% discount
    }

    @Override
    public float calculaTemporada(Date in, Date out){
        float descuentoFi=0;
        float descuentoFo=0;
        try {
            descuentoFi = this.temporadaRepository.descuento (in);
            descuentoFo = this.temporadaRepository.descuento (out);
        } catch(AopInvocationException e){ }

        if (descuentoFi == descuentoFo)
            return descuentoFi;
        if (Math.abs (descuentoFi) < Math.abs (descuentoFo))
            return (descuentoFo/2);
        return (descuentoFi/2);
    }

}
