package com.demo.entity;

import com.demo.service.IEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class NotificacionCorreoElectronico  {


    private  String correoRemitente;
    private  String correoDestinatario;
    private String subject;
    private String body;

    public NotificacionCorreoElectronico() {
    }

    public NotificacionCorreoElectronico(String correoRemitente, String correoDestinatario, String subject, String body) {
        this.correoRemitente = correoRemitente;
        this.correoDestinatario = correoDestinatario;
        this.subject = subject;
        this.body = body;
    }

    public String getCorreoRemitente() {
        return correoRemitente;
    }

    public void setCorreoRemitente(String correoRemitente) {
        this.correoRemitente = correoRemitente;
    }

    public String getCorreoDestinatario() {
        return correoDestinatario;
    }

    public void setCorreoDestinatario(String correoDestinatario) {
        this.correoDestinatario = correoDestinatario;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NotificacionCorreoElectronico{" +
                "correoRemitente='" + correoRemitente + '\'' +
                ", correoDestinatario='" + correoDestinatario + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    /*    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarNotificacion(String tipoEvento, Long usuarioId, Long solicitudId) {

        String mensajeCompleto = "Hola " + usuarioId + "su solicitud #: " + solicitudId + "ha sido " + tipoEvento;

        sendEmail("","Notificacion" + tipoEvento, mensajeCompleto);

    }

    public void sendEmail(String toEmail,String  subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
    }*/
}
