package com.demo.service;

import com.demo.service.ControladorEventos;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class Editor {
    ControladorEventos eventos;

    public Editor() {

        LinkedList<String> operations = new LinkedList<>();
        operations.add("actualizada");
        operations.add("creada");

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
    public void notificacarCambioDeEstado(String tipoEvento, List<String> correo,  String nombreUsuario, Long solicitudId, String estado){
        eventos.notificar(tipoEvento, correo,nombreUsuario,solicitudId, estado);
    }



    @Override
    public String toString() {
        return "Editor{" +
                "eventos=" + eventos +
                '}';
    }
}
