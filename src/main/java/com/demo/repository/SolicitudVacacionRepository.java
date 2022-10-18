package com.demo.repository;

import com.demo.entity.SolicitudVacacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudVacacionRepository extends JpaRepository<SolicitudVacacion, Long> {



}
