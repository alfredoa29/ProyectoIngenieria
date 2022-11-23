package com.demo.service;

import java.util.List;

public interface IEventListener {
    public void enviarNotificacion(String tipoEvento, List<String> correos, String nombreUsuario, Long solicitudId, String estado);
}
