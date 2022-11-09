package com.demo.service;

import com.demo.entity.NotificacionCorreoElectronico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class CorreoServiceImp implements IEventListener{


    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String toEmail,String  subject, String body)  {

        try {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        NotificacionCorreoElectronico notiCorreo = new NotificacionCorreoElectronico();
        notiCorreo.setCorreoRemitente("alfredoaguerrero1@gmail.com");
        notiCorreo.setCorreoDestinatario(toEmail);
        notiCorreo.setSubject(subject);
        notiCorreo.setBody(body);

        //SimpleMailMessage message = new SimpleMailMessage();
         helper.setFrom(notiCorreo.getCorreoRemitente());
        helper.setTo(notiCorreo.getCorreoDestinatario());
        helper.setText(notiCorreo.getBody());
        helper.setSubject(notiCorreo.getSubject());
        boolean html = true;
        helper.setText("<h3>Mensaje de Kiosco Coarsa</h3>,<br><big>"+body+"</big>", html);

        mailSender.send(message);


        }catch (MailSendException | MessagingException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void enviarNotificacion(String tipoEvento, String correo, String mensaje, String nombreUsuario, Long solicitudId, String estado) {
        String mensajeCompleto =  "Hola " + nombreUsuario + " su solicitud con el # de id : " + solicitudId + " ha sido " + tipoEvento + " con el estado " + estado + " puede comprobarla en el quiosco informativo.";

        sendEmail(correo,"Notificacion " + tipoEvento, mensajeCompleto);
    }
}
