package com.hotel.java.application.repositories;

import com.hotel.java.application.domain.entities.HabitacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository extends JpaRepository<HabitacionEntity, Long> {
}
