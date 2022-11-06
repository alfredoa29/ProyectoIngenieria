package com.example.demo.service;

import com.demo.entity.Role;
import com.demo.entity.SolicitudVacacion;
import com.demo.entity.User;
import com.demo.entity.Vacacion;
import com.demo.repository.RoleRepository;
import com.demo.repository.SolicitudVacacionRepository;
import com.demo.service.CorreoServiceImp;
import com.demo.service.SolicitudVacacionImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

class CorreoServiceImpTest {


    @Mock
    private SolicitudVacacionRepository solicitudVacacionRepository;


    private SolicitudVacacion solicitud;



    @Mock
    private CorreoServiceImp correoServiceImp;

    @BeforeEach
    void setUp() {


    }

    //utilizar el test que ests en solicitudVacacion
    @Test
    void sendEmail() {


        String toEmail = "alfredoaguerrero1@gmail.com";
        String body = "cuerpo cel correo";
        String subject = "pruebaUnit";
        correoServiceImp.sendEmail(toEmail, subject, body);
        verify(correoServiceImp).sendEmail(toEmail, subject, body);

    }


}