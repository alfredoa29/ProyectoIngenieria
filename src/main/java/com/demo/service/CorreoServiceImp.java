package com.demo.service;

import com.demo.entity.NotificacionCorreoElectronico;
import com.demo.entity.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CorreoServiceImp implements IEventListener{


    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String toEmail,String  subject, String body){
        NotificacionCorreoElectronico notiCorreo = new NotificacionCorreoElectronico();
        notiCorreo.setCorreoRemitente("alfredoaguerrero1@gmail.com");
        notiCorreo.setCorreoDestinatario(toEmail);
        notiCorreo.setSubject(subject);
        notiCorreo.setBody(body);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(notiCorreo.getCorreoRemitente());
        message.setTo(notiCorreo.getCorreoDestinatario());
        message.setText(notiCorreo.getBody());
        message.setSubject(notiCorreo.getSubject());
        mailSender.send(message);
    }

    @Override
    public void enviarNotificacion(String tipoEvento, String correo, String mensaje, Long usuarioId, Long solicitudId, String estado) {
        String mensajeCompleto = "Hola " + usuarioId + " su solicitud con el # de id : " + solicitudId + "ha sido " + tipoEvento + " con el estado " + estado + " puede comprobarla en el quiosco informativo.";

        sendEmail(correo,"Notificacion " + tipoEvento, mensajeCompleto);
    }
}
