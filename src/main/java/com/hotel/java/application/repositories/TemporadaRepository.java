package com.hotel.java.application.repositories;

import com.hotel.java.application.domain.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface TemporadaRepository extends JpaRepository<LoginEntity, Long> {
    @Query(value = "select descuento  from TemporadaEntity where :fecha BETWEEN fecha_ini AND fecha_fin")
    float descuento(@Param("fecha") Date fecha);
}