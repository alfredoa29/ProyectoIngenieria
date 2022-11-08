package com.demo.service;

import com.demo.service.IEventListener;

import java.util.*;

public class ControladorEventos {
    Map<String, List<IEventListener>> listeners = new HashMap<>();

    public ControladorEventos(LinkedList<String> operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new LinkedList<>());
        }
    }


    public Map<String, List<IEventListener>> getListeners() {
        return listeners;
    }

    public void setListeners(Map<String, List<IEventListener>> listeners) {
        this.listeners = listeners;
    }

    public void suscribirse(String eventType, IEventListener listener){

        List<IEventListener> users = listeners.get(eventType);
        users.add(listener);


    }

    public void desuscribirse(String eventType, IEventListener listener){
        List<IEventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notificar(String tipoEvento, String correo, String mensaje, String nombreUsuario, Long solicitudId, String estado){
        List<IEventListener> users = listeners.get(tipoEvento);
        for (IEventListener listener : users) {
              listener.enviarNotificacion(tipoEvento, correo,mensaje,nombreUsuario,solicitudId,  estado);
        }
    }


    @Override
    public String toString() {
        return "ControladorEventos{" +
                "listeners=" + listeners +
                '}';
    }
}
