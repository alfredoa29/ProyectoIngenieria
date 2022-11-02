package com.demo.service;

public interface IEventListener {
    public void enviarNotificacion(String tipoEvento, String correo, String mensaje, Long usuarioId, Long solicitudId, String estado);
}
