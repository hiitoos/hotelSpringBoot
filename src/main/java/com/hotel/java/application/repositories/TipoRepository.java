package com.hotel.java.application.repositories;

import com.hotel.java.application.domain.entities.TipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<TipoEntity, Long> {
}
