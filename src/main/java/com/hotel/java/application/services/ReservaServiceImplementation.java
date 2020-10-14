package com.hotel.java.application.services;

import com.hotel.java.application.domain.entities.ReservaEntity;
import com.hotel.java.application.domain.factories.ClienteFactory;
import com.hotel.java.application.domain.factories.HabitacionFactory;
import com.hotel.java.application.domain.factories.ReservaFactory;
import com.hotel.java.application.models.ReservaModel;
import com.hotel.java.application.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean operateReserva(ReservaModel reservaModel, String modo) {
        long a=0;
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
                a = this.reservaRepository.save (reservaEntity).getClienteEntity_id ().getId ();
                this.reservaRepository.flush ();
                if(a>0){
                    return true;
                }
                return false;
            }
            case "delete": {
                try {
                    this.reservaRepository.delete (reservaEntity);
                    return true;
                }
                catch (Exception e){
                    return false;
                }
            }
            default: return false;
        }
    }
}
