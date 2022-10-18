package com.demo.service;

import com.demo.entity.User;

import java.util.List;

public interface SolicitudService<T,V> {

    public T encontrarSolicitudPorId(Long id);
    public List<T> encontrarSolicitudesPorUsuario(User user);

    public T crearSolicitudPersonal(T solicitud);

    public List<T> encontrarSolicitudesPorEstadoTrue();
    public List<T> encontrarSolicitudesPorEstadoFalse();
    public  T findSolicitudByEstadoTrue(T solicitud);

    public List<T> encontrarTodos();
    public  List<T> encontrarSolicitudPorTipo(String username, String nombreSolicitud);

    public void aceptarSolicitud(Long idSoli) throws Exception;
    public  void rechazarSolicitud(Long idSoli) throws Exception;
    public List<T> encontrarQuiosco(List<V> solicitudList);

}
