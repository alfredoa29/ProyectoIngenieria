package com.demo.service;

import com.demo.entity.SolicitudVacacion;
import com.demo.entity.User;
import com.demo.repository.SolicitudVacacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class SolicitudVacacionImp implements ISolicitudService<SolicitudVacacion, SolicitudVacacion> {

    @Autowired
    SolicitudVacacionRepository solicitudVacacionRepository;

    @Autowired
    Editor editorNotication;

    @Autowired
    IEventListener notificacionCorreoServiceImp;


    @Autowired
    CorreoServiceImp correoServiceImp;



    @Override
    public SolicitudVacacion encontrarSolicitudPorId(Long id) {
        return solicitudVacacionRepository.findById(id).get();
    }

    @Override
    public List<SolicitudVacacion> encontrarSolicitudesPorUsuario(User user) {

        List<SolicitudVacacion>  encontrados = new LinkedList<>();
        List<SolicitudVacacion>  todasSolicitudes =  solicitudVacacionRepository.findAll();
        for (int i=0; i<todasSolicitudes.size(); i++){
            if (todasSolicitudes.get(i).getUsuario().equals(user)){
                encontrados.add(todasSolicitudes.get(i));
            }
        }



        return encontrados;
    }

    @Override
    public SolicitudVacacion crearSolicitudPersonal(SolicitudVacacion solicitudVacacion) {

        List<String> adminEmails = correoServiceImp.adminEmails();

        editorNotication.eventos.suscribirse("creada", this.notificacionCorreoServiceImp);
        editorNotication.notificacarCambioDeEstado("creada",adminEmails , "Administrador", 3L, "creada");
        return solicitudVacacionRepository.save(solicitudVacacion);

    }


    @Override
    public List<SolicitudVacacion> encontrarSolicitudesPorEstadoTrue() {
        List<SolicitudVacacion>  encontrados = new LinkedList<>();
        List<SolicitudVacacion>  todasSolicitudes =  solicitudVacacionRepository.findAll();
        for (int i=0; i<todasSolicitudes.size(); i++){
            if (todasSolicitudes.get(i).getEstado().equals("Aceptado")){
                encontrados.add(todasSolicitudes.get(i));
            }
        }

        return encontrados;
    }

    @Override
    public List<SolicitudVacacion> encontrarSolicitudesPorEstadoFalse() {
        List<SolicitudVacacion>  encontrados = new LinkedList<>();
        List<SolicitudVacacion>  todasSolicitudes =  solicitudVacacionRepository.findAll();
        for (int i=0; i<todasSolicitudes.size(); i++){
            if (todasSolicitudes.get(i).getEstado().equals("Rechazado")){
                encontrados.add(todasSolicitudes.get(i));
            }
        }

        return encontrados;
    }

    @Override
    public SolicitudVacacion findSolicitudByEstadoTrue(SolicitudVacacion solicitud) {
        SolicitudVacacion encontrada = new SolicitudVacacion();

        if (solicitud.getEstado().equals("Aceptado")){
            encontrada = solicitud;
        }


        return encontrada; ///metodo de prrueba, mejorar
    }

    @Override
    public List<SolicitudVacacion> encontrarTodos() {
        return solicitudVacacionRepository.findAll();
    }

    @Override
    public List<SolicitudVacacion> encontrarSolicitudPorTipo(String username, String nombreSolicitud) {
        return null;
    }

    @Override
    public void aceptarSolicitud(Long idSoli) throws Exception {

        List<String> emails = new ArrayList<>();

        try {
            SolicitudVacacion solicitud = solicitudVacacionRepository.findById(idSoli).get();
            solicitud.setEstado("Aceptado");
            emails.add(solicitud.getUsuario().getEmail());
            solicitudVacacionRepository.save(solicitud);
            editorNotication.eventos.suscribirse("actualizada", notificacionCorreoServiceImp);
            editorNotication.notificacarCambioDeEstado("actualizada",emails, solicitud.getUsuario().getUsername(),
                    solicitud.getId(), "aceptada");
        }catch (Exception e){
            throw new Exception("Problema al aceptar la solicitud");
        }

    }

    @Override
    public void rechazarSolicitud(Long idSoli) throws Exception {

        List<String> emails = new ArrayList<>();

        try {
            NotificacionCorreoServiceImp notificacionCorreoServiceImp = new NotificacionCorreoServiceImp();
            SolicitudVacacion solicitud = solicitudVacacionRepository.findById(idSoli).get();
            emails.add(solicitud.getUsuario().getEmail());
            solicitud.setEstado("rechazado");
            solicitudVacacionRepository.save(solicitud);
            editorNotication.eventos.suscribirse("actualizada", this.notificacionCorreoServiceImp);
            editorNotication.notificacarCambioDeEstado("actualizada",emails, solicitud.getUsuario().getUsername(),
                    solicitud.getId(), "rechazada");
        }catch (Exception e){
            throw new Exception("Problema al rechazar la solicitud");
        }
    }

    @Override
    public List<SolicitudVacacion> encontrarQuiosco(List<SolicitudVacacion> solicitudList) {
        return null;
    }


}
