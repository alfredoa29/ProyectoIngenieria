package com.demo.service;

import com.demo.entity.CorreoElectronico;

import com.demo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.LinkedList;
import java.util.List;

@Component
public class NotificacionCorreoServiceImp implements IEventListener{


    @Autowired
    private JavaMailSender mailSender;



    public void sendEmail(List<String> toEmail,String  subject, String body)  {

        try {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

/*        NotificacionCorreoElectronico notiCorreo = new NotificacionCorreoElectronico();
        notiCorreo.setCorreoRemitente("alfredoaguerrero1@gmail.com");
        notiCorreo.setCorreoDestinatario(toEmail);
        notiCorreo.setSubject(subject);
        notiCorreo.setBody(body);*/

            String[] emailIds = toEmail.toArray(new String[0]);
            //emailIds[0] = notiCorreo.getCorreoRemitente();
            //emailIds[1] = "alfredo.guerrero@cgr.go.cr";

        //SimpleMailMessage message = new SimpleMailMessage();
         helper.setFrom("alfredoaguerrero1@gmail.com");
        helper.setTo(emailIds);
            helper.setSubject(subject);
        helper.setText(body);

        boolean html = true;
        helper.setText("<h3>Mensaje de Kiosco Coarsa</h3>,<br><big>"+body+"</big>", html);

        mailSender.send(message);


        }catch (MailSendException | MessagingException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void enviarNotificacion(String tipoEvento, List<String> correo, String nombreUsuario, Long solicitudId, String estado) {
        String mensajeCompleto =  "Hola " + nombreUsuario + " la solicitud con el numero de id: #" + solicitudId + " ha sido " + tipoEvento + " con el estado " + estado + " puede comprobarla en el quiosco informativo.";

        sendEmail(correo,"Notificacion " + tipoEvento, mensajeCompleto);
    }
}
