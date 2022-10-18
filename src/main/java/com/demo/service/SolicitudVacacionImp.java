package com.demo.service;

import com.demo.entity.SolicitudVacacion;
import com.demo.entity.User;
import com.demo.repository.SolicitudVacacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SolicitudVacacionImp implements SolicitudService<SolicitudVacacion, SolicitudVacacion> {

        @Autowired
    SolicitudVacacionRepository solicitudVacacionRepository;


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
    public SolicitudVacacion crearSolicitudPersonal(SolicitudVacacion solicitud) {
        return solicitudVacacionRepository.save(solicitud);
    }

    @Override
    public List<SolicitudVacacion> encontrarSolicitudesPorEstadoTrue() {
        List<SolicitudVacacion>  encontrados = new LinkedList<>();
        List<SolicitudVacacion>  todasSolicitudes =  solicitudVacacionRepository.findAll();
        for (int i=0; i<todasSolicitudes.size(); i++){
            if (todasSolicitudes.get(i).isEstado()==true){
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
            if (todasSolicitudes.get(i).isEstado()==false){
                encontrados.add(todasSolicitudes.get(i));
            }
        }

        return encontrados;
    }

    @Override
    public SolicitudVacacion findSolicitudByEstadoTrue(SolicitudVacacion solicitud) {
        SolicitudVacacion encontrada = new SolicitudVacacion();

        if (!solicitud.isEstado()== false){
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
        try {
            SolicitudVacacion solicitud = solicitudVacacionRepository.findById(idSoli).get();
            solicitud.setEstado(true);
            solicitudVacacionRepository.save(solicitud);
        }catch (Exception e){
            throw new Exception("Problema al aceptar la solicitud");
        }

    }

    @Override
    public void rechazarSolicitud(Long idSoli) throws Exception {
        try {
            SolicitudVacacion solicitud = solicitudVacacionRepository.findById(idSoli).get();
            solicitud.setEstado(true);
            solicitudVacacionRepository.save(solicitud);
        }catch (Exception e){
            throw new Exception("Problema al aceptar la solicitud");
        }
    }

    @Override
    public List<SolicitudVacacion> encontrarQuiosco(List<SolicitudVacacion> solicitudList) {
        return null;
    }


}
