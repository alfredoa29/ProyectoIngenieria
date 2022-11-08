package com.demo.service;

import com.demo.service.ControladorEventos;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
@Service
public class Editor {
    ControladorEventos eventos;

    public Editor() {

        LinkedList<String> operations = new LinkedList<>();
        operations.add("actualizada");

        this.eventos = new ControladorEventos(operations);
    }

    public Editor(ControladorEventos eventos) {
        this.eventos = eventos;
    }

    public ControladorEventos getEventos() {
        return eventos;
    }

    public void setEventos(ControladorEventos eventos) {
        this.eventos = eventos;
    }

    //metodo que va a ser llamado a la hora que se actualice el estado de la solicitud
    public void notificacarCambioDeEstado(String tipoEvento, String correo, String mensaje, String nombreUsuario, Long solicitudId, String estado){
        eventos.notificar(tipoEvento, correo,mensaje,nombreUsuario,solicitudId, estado);
    }


    @Override
    public String toString() {
        return "Editor{" +
                "eventos=" + eventos +
                '}';
    }
}
