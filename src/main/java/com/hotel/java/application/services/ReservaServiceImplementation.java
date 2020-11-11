package com.hotel.java.application.services;

import com.hotel.java.application.domain.entities.ReservaEntity;
import com.hotel.java.application.domain.factories.ClienteFactory;
import com.hotel.java.application.domain.factories.HabitacionFactory;
import com.hotel.java.application.domain.factories.ReservaFactory;
import com.hotel.java.application.models.ReservaModel;
import com.hotel.java.application.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ReservaServiceImplementation implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final ReservaFactory reservaFactory;
    private final ClienteFactory clienteFactory;
    private final HabitacionFactory habitacionFactory ;

    @Autowired
    public ReservaServiceImplementation(ReservaRepository reservaRepository,
                                        ReservaFactory reservaFactory,
                                        ClienteFactory clienteFactory,
                                        HabitacionFactory habitacionFactory) {
        this.reservaRepository = reservaRepository;
        this.reservaFactory = reservaFactory;
        this.clienteFactory = clienteFactory;
        this.habitacionFactory = habitacionFactory;
    }

    @Override
    public List<ReservaModel> listReservas() {
        List<ReservaEntity> reservaEntities = this.reservaRepository.findAll();
        return this.reservaFactory.reservaListEntity2Model(reservaEntities);
    }

    @Override
    public ReservaModel listReservaById(long id) {
        ReservaEntity reservaEntity = this.reservaRepository.findById (id).orElseThrow (RuntimeException::new);
        return this.reservaFactory.reservaEntity2Model (reservaEntity);
    }

    @Override
    public long operateReserva(ReservaModel reservaModel, String modo) {
        long id=0;
        ReservaEntity reservaEntity = new ReservaEntity (
            reservaModel.getId (),
                reservaModel.getFechaIn (),
                reservaModel.getFechaOut (),
                reservaModel.getPrecioTotal (),
                this.clienteFactory.clienteModel2Entity (reservaModel.getCliente ()),
                this.habitacionFactory.habitacionModel2Entity (reservaModel.getHabitacion ())
        );
        switch (modo){
            case "new":
            case "update": {
                id = this.reservaRepository.save (reservaEntity).getId ();
                this.reservaRepository.flush ();
                if(id>0){
                    return id;
                }
                return 0;
            }
            case "delete": {
                try {
                    this.reservaRepository.delete (reservaEntity);
                    return reservaEntity.getId ();
                }
                catch (Exception e){
                    return 0;
                }
            }
            default: return 0;
        }
    }

    @Override
    public List<Date> listaDate(long id) {
        List<Date[]> datesGet = this.reservaRepository.dateBookingsByRoom (id);
        List<Date> dates = new ArrayList<> ();

        for (int i=0; i<datesGet.size (); i++){
            Date start = datesGet.get(i)[0];
            Date end = datesGet.get(i)[1];
            while(!start.equals (end)){
                dates.add (start);
                start = new Date(start.getTime () + TimeUnit.DAYS.toMillis (1));
            }
        }
        return dates;
    }
}
