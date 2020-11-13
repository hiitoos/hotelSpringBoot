package com.hotel.java.application.repositories;

import com.hotel.java.application.domain.entities.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends CrudRepository<ReservaEntity, Long> {
    @Query(value = "select e.fechaIn, e.fechaOut  from ReservaEntity e where  e.fechaOut > current_date AND e.habitacion.id = :id")
    List<Date[]> dateBookingsByRoom(@Param("id") long id);
}
