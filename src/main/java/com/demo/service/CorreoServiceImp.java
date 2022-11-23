package com.demo.service;

import com.demo.entity.CorreoElectronico;
import com.demo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
@Component
public class CorreoServiceImp {


    @Autowired
    EmailRepository emailRepository;


    public void CrearCorreo(CorreoElectronico correoElectronico){
        try {

            emailRepository.save(correoElectronico);

        }catch (Exception e) {
            throw new RuntimeException("Problema al guardar el correo electronico");
        }
    }


    public LinkedList<String> adminEmails() {

        List<CorreoElectronico> correoElectronicos = new LinkedList<>();
        LinkedList<String> correosAdmin = new LinkedList<>();
        String admin = "administrador";
        try {


            correoElectronicos = emailRepository.findAll();

            for (int i=0; i<correoElectronicos.size(); i++){
                if (correoElectronicos.get(i).getTipoDeCorreo().equals(admin)){
                    correosAdmin.add(correoElectronicos.get(i).getCorreoElectronico());
                }
            }

            return correosAdmin;

        } catch (MailSendException e) {
            throw new RuntimeException("Problema al encontrar el correo del administrador");
        }
    }

    public List<CorreoElectronico> correoElectronicoList() {

        List<CorreoElectronico> correoElectronicos = new LinkedList<>();
        LinkedList<String> correosAdmin = new LinkedList<>();
        String admin = "administrador";
        try {


            correoElectronicos = emailRepository.findAll();

            for (int i=0; i<correoElectronicos.size(); i++){
                if (correoElectronicos.get(i).getTipoDeCorreo().equals(admin)){
                    correosAdmin.add(correoElectronicos.get(i).getCorreoElectronico());
                }
            }

            return correoElectronicos;

        } catch (MailSendException e) {
            throw new RuntimeException("Problema al encontrar el correo del administrador");
        }
    }


    public void eliminarCorreo(Long idCorreo){
        try {

                emailRepository.deleteById(idCorreo);

        } catch (MailSendException e) {
            throw new RuntimeException("Problema al eliminar el correo del administrador");
        }
    }


}
