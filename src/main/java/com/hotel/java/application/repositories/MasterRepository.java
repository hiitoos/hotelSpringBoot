package com.hotel.java.application.repositories;

import com.hotel.java.application.domain.entities.HabitacionEntity;

import java.util.List;

public interface MasterRepository {
    void newObject(Object object, boolean saveOrUpdate);
    List<Object> listarTodo(Class classEntity);
    Object listarById(long id, Class classEntity);
    List<Object> listCampoGT(Class classEntity, int valor, String campo);
    List<Object> listCampoLT(Class classEntity, int valor, String campo);
    long newSignUp(Object object);
    List<Object> showByType(Class classEntity, String campo);
    List<Object> showByTypeID(Class classEntity, long valor, String campo);
    Object listarCampo(String campo, Class classEntity, String valor);

}
