package com.demo.service;

import com.demo.entity.Solicitud;
import com.demo.entity.User;

import java.util.List;
import java.util.Optional;


public interface SolicitudService {

    public Optional<Solicitud> encontrarSolicitudPorId(Long id);
    public List<Solicitud> encontrarSolicitudesPorUsuario(User user);

    public Solicitud crearSolicitudPersonal(Solicitud solicitud);

    public List<Solicitud> encontrarSolicitudesPorEstadoTrue();
    public List<Solicitud> encontrarSolicitudesPorEstadoFalse();
    public  Solicitud findSolicitudByEstadoTrue(Solicitud solicitud);

    public List<Solicitud> encontrarTodos();
    public  List<Solicitud> encontrarSolicitudPorTipo(String username, String nombreSolicitud);

    public void aceptarSolicitud(Long idSoli) throws Exception;
    public  void rechazarSolicitud(Long idSoli);

}
