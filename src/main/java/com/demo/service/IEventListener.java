package com.demo.service;

public interface IEventListener {
    public void enviarNotificacion(String tipoEvento, String correo, String mensaje, String nombreUsuario, Long solicitudId, String estado);
}
