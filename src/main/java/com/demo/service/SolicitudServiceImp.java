package com.demo.service;

import com.demo.entity.Solicitud;
import com.demo.entity.User;
import com.demo.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudServiceImp implements SolicitudService{

    @Autowired
    SolicitudRepository solicitudRepository;


    @Override
    public Optional<Solicitud> encontrarSolicitudPorId(Long id) {

        return solicitudRepository.findById(id);
    }



    @Override
    public List<Solicitud> encontrarSolicitudesPorUsuario(User user) {
        List<Solicitud>  encontrados = new LinkedList<>();
        List<Solicitud>  todasSolicitudes =  solicitudRepository.findAll();
        for (int i=0; i<todasSolicitudes.size(); i++){
            if (todasSolicitudes.get(i).getUsuario().equals(user)){
                encontrados.add(todasSolicitudes.get(i));
            }
        }



        return encontrados;
    }


    @Override
    public Solicitud crearSolicitudPersonal(Solicitud solicitud) {

        return solicitudRepository.save(solicitud);
    }

    @Override
    public List<Solicitud> encontrarSolicitudesPorEstadoTrue() {
        List<Solicitud>  encontrados = new LinkedList<>();
        List<Solicitud>  todasSolicitudes =  solicitudRepository.findAll();
        for (int i=0; i<todasSolicitudes.size(); i++){
            if (todasSolicitudes.get(i).isEstado()==true){
                encontrados.add(todasSolicitudes.get(i));
            }
        }

        return encontrados;
    }

    @Override
    public List<Solicitud> encontrarSolicitudesPorEstadoFalse() {
        List<Solicitud>  encontrados = new LinkedList<>();
        List<Solicitud>  todasSolicitudes =  solicitudRepository.findAll();
        for (int i=0; i<todasSolicitudes.size(); i++){
            if (todasSolicitudes.get(i).isEstado()==false){
                encontrados.add(todasSolicitudes.get(i));
            }
        }

        return encontrados;
    }

    @Override
    public Solicitud findSolicitudByEstadoTrue(Solicitud solicitud) {
        Solicitud encontrada = new Solicitud();

        if (!solicitud.isEstado()== false){
            encontrada = solicitud;
        }


        return encontrada; ///metodo de prrueba, mejorar
    }

    @Override
    public List<Solicitud> encontrarTodos() {
        return solicitudRepository.findAll();
    }

    @Override
    public List<Solicitud> encontrarSolicitudPorTipo(String username, String nombreSolicitud) {
        List<Solicitud>  encontrados = new LinkedList<>();
        List<Solicitud>  todasSolicitudes =  solicitudRepository.findAll();
        for (int i=0; i<todasSolicitudes.size(); i++) {
            if ( username.equals(todasSolicitudes.get(i).getUsuario().getUsername()) && todasSolicitudes.get(i).getQuioscoPersonal().getNombreSolicitud().equals(nombreSolicitud)) {
                encontrados.add(todasSolicitudes.get(i));
            }
        }



        return encontrados;
    }

    @Override
    public void aceptarSolicitud(Long idSoli) throws Exception {

        try {
            Solicitud solicitud = solicitudRepository.findById(idSoli).get();
            solicitud.setEstado(true);
            solicitudRepository.save(solicitud);
        }catch (Exception e){
            throw new Exception("Problema al aceptar la solicitud");
        }


    }

    @Override
    public void rechazarSolicitud(Long idSoli) {

    }


}
