package com.example.demo;

import com.demo.entity.SolicitudVacacion;
import com.demo.repository.RoleRepository;
import com.demo.repository.SolicitudVacacionRepository;
import com.demo.service.NotificacionCorreoServiceImp;
import com.demo.service.SolicitudVacacionImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Mock
    private SolicitudVacacionRepository solicitudVacacionRepository;


    private SolicitudVacacion solicitud;

    @Mock
    RoleRepository roleRepository;
    @InjectMocks
    private SolicitudVacacionImp solicitudServiceImp;

    @InjectMocks
    private NotificacionCorreoServiceImp notificacionCorreoServiceImp;

    @Test
    void contextLoads() {
    }

}
