package com.example.demo.service;

import com.demo.service.NotificacionCorreoServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotificacionCorreoServiceImpTest {



    @Mock
    private NotificacionCorreoServiceImp notificacionCorreoServiceImp;

    @BeforeEach
    void setUp() {


    }


    @Test
    void sendEmail() {


        String toEmail = "alfredoaguerrero1@gmail.com";
        String body = "cuerpo cel correo";
        String subject = "pruebaUnit";
        notificacionCorreoServiceImp.sendEmail(toEmail, subject, body);
        verify(notificacionCorreoServiceImp).sendEmail(toEmail, subject, body);

    }


}