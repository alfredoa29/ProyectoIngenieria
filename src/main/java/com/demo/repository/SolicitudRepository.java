package com.demo.repository;

import com.demo.entity.Solicitud;
import com.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    public List<Solicitud> findByUsuario(User usuario);
    //public List<Solicitud> findSolicitudByUsuario(User usuario);
    public  List<Solicitud> findByEstadoTrue();
    public  Solicitud findAllByEstadoTrue();



}
