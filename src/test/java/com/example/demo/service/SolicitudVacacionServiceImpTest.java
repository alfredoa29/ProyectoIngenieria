package com.example.demo.service;

import com.demo.entity.*;
import com.demo.repository.RoleRepository;
import com.demo.repository.SolicitudVacacionRepository;
import com.demo.service.SolicitudVacacionImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SolicitudVacacionServiceImpTest {

    @Mock
    private SolicitudVacacionRepository solicitudVacacionRepository;


    private SolicitudVacacion solicitud;

    @Mock
    RoleRepository roleRepository;
    @InjectMocks
    private SolicitudVacacionImp solicitudServiceImp;



    @BeforeEach
    void setUp() {

        Role roleUser = roleRepository.findByName("USER");

        Set<Role> roles = new HashSet<Role>();

        roles.add(roleUser);

        Vacacion vacacion = new Vacacion();
        vacacion.setId(1L);
        vacacion.setFechaInicio("29-10-22");
        vacacion.setNumDias(3);
        vacacion.setFechaFinal("31-10-22");


       User user = new User();
       user.setId(117220056);
        user.setFirstName("Alfredo");
        user.setLastName("Guerrrro");
        user.setEmail("alfredo.guerrero@gmail.com");
        user.setUsername("3117220056");
        user.setPassword("alfredo1234");
        user.setConfirmPassword("alfredo1234");
        user.setRoles(roles);

        solicitud = new SolicitudVacacion();
        solicitud.setId(1L);
        solicitud.setUsuario(user);
        solicitud.setEstado("En espera");
        solicitud.setFecha("10-10-22");
        solicitud.setVacacion(vacacion);

    }

    @Test
    void crearSolicitudPersonal() {

        Role roleUser = roleRepository.findByName("USER");

        Set<Role> roles = new HashSet<Role>();

        roles.add(roleUser);

        Vacacion vacacion = new Vacacion();
        vacacion.setId(1L);
        vacacion.setFechaInicio("29-10-22");
        vacacion.setNumDias(3);
        vacacion.setFechaFinal("31-10-22");

        User user1 = new User();
        user1.setId(444444);
        user1.setFirstName("JUAN");
        user1.setLastName("Guerrrro");
        user1.setEmail("alfredo.guerrero.gonzalez@est.una.ac.cr");
        user1.setUsername("4444444");
        user1.setPassword("alfredo1234");
        user1.setConfirmPassword("alfredo1234");
        user1.setRoles(roles);

        SolicitudVacacion solicitud3 = new SolicitudVacacion();
        solicitud3.setId(1L);
        solicitud3.setUsuario(user1);
        solicitud3.setEstado("en Espera");
        solicitud3.setFecha("11-11-22");
        solicitud3.setVacacion(vacacion);


        when(solicitudVacacionRepository.save(ArgumentMatchers.any(SolicitudVacacion.class))).thenReturn(solicitud3);
        Solicitud created = solicitudServiceImp.crearSolicitudPersonal(solicitud3);
        assertThat(created.getId()).isSameAs(solicitud.getId());
        verify(solicitudVacacionRepository).save(solicitud3);

        //assertNotNull(solicitudServiceImp.crearSolicitudPersonal(solicitud));
    }


    @Test
    void encontrarSolicitudesPorUsuario() {


        Role roleUser = roleRepository.findByName("USER");

        Set<Role> roles = new HashSet<Role>();

        roles.add(roleUser);

        Vacacion vacacion = new Vacacion();
        vacacion.setId(1L);
        vacacion.setFechaInicio("29-10-22");
        vacacion.setNumDias(3);
        vacacion.setFechaFinal("31-10-22");

        Vacacion vacacion2 = new Vacacion();
        vacacion2.setId(2L);
        vacacion2.setFechaInicio("29-10-22");
        vacacion2.setNumDias(3);
        vacacion2.setFechaFinal("31-10-22");

        User user = new User();
        user.setId(117220056);
        user.setFirstName("Alfredo");
        user.setLastName("Guerrrro");
        user.setEmail("alfredo.guerrero@gmail.com");
        user.setUsername("117220056");
        user.setPassword("alfredo1234");
        user.setConfirmPassword("alfredo1234");
        user.setRoles(roles);

        User user1 = new User();
        user1.setId(444444);
        user1.setFirstName("JUAN");
        user1.setLastName("Guerrrro");
        user1.setEmail("alfredo.guerrero@gmail.com");
        user1.setUsername("4444444");
        user1.setPassword("alfredo1234");
        user1.setConfirmPassword("alfredo1234");
        user1.setRoles(roles);

        SolicitudVacacion solicitud1 = new SolicitudVacacion();
        solicitud1.setId(11L);
        solicitud1.setUsuario(user);
        solicitud1.setEstado("En espera");
        solicitud1.setFecha("10-10-22");
        solicitud1.setVacacion(vacacion);

        SolicitudVacacion solicitud2 = new SolicitudVacacion();
        solicitud2.setId(2L);
        solicitud2.setUsuario(user1);
        solicitud2.setEstado("En espera");
        solicitud2.setFecha("10-10-22");
        solicitud1.setVacacion(vacacion);

        SolicitudVacacion solicitud3 = new SolicitudVacacion();
        solicitud3.setId(3L);
        solicitud3.setUsuario(user);
        solicitud2.setEstado("En espera");
        solicitud3.setFecha("11-11-22");
        solicitud3.setVacacion(vacacion2);


        List<SolicitudVacacion> solicituds = new LinkedList<>();
        //solicituds.add(solicitud1);
        solicituds.add(solicitud2);
        solicituds.add(solicitud3);

        List<SolicitudVacacion> solicitudsExpec = new LinkedList<>();
        solicitudsExpec.add(solicitud3);

       // Iterable<Solicitud> solicituds2 = solicitudServiceImp.encontrarSolicitudesPorUsuario("117220056");


        //Long id = Long.valueOf(11);
        given(solicitudVacacionRepository.findAll()).willReturn(solicituds);

        List<SolicitudVacacion> givenFrom =  solicitudServiceImp.encontrarSolicitudesPorUsuario(user);

        assertEquals(solicitudsExpec, givenFrom);
        //verify(solicitudRepository).findByUsuario(user1);

    }


    @Test
    void encontrarSolicitudPorId() {

        Role roleUser = roleRepository.findByName("USER");

        Set<Role> roles = new HashSet<Role>();

        roles.add(roleUser);

        Vacacion vacacion = new Vacacion();
        vacacion.setId(1L);
        vacacion.setFechaInicio("29-10-22");
        vacacion.setNumDias(3);
        vacacion.setFechaFinal("31-10-22");

        User user1 = new User();
        user1.setId(444444);
        user1.setFirstName("JUAN");
        user1.setLastName("Guerrrro");
        user1.setEmail("alfredo.guerrero@gmail.com");
        user1.setUsername("4444444");
        user1.setPassword("alfredo1234");
        user1.setConfirmPassword("alfredo1234");
        user1.setRoles(roles);

/*        Optional<Solicitud> solicitud3 = Optional.of(new Solicitud());
        solicitud3.get().setId(1L);
        solicitud3.get().setUsuario(user1);
        solicitud3.get().setEstado(false);
        solicitud3.get().setFecha("11-11-22");
        solicitud3.get().setTipoSolicitud(vacacion);
        solicitud3.get().getFecha();
        solicitud3.get().getTipoSolicitud().getTipoId();*/

        SolicitudVacacion solicitud2 = new SolicitudVacacion();
        solicitud2.setId(2L);
        solicitud2.setUsuario(user1);
        solicitud2.setEstado("En espera");
        solicitud2.setFecha("10-10-22");
        solicitud2.setVacacion(vacacion);


        SolicitudVacacion solicitud3 = new SolicitudVacacion();
        solicitud3.setId(3L);
        solicitud3.setUsuario(user1);
        solicitud3.setEstado("En espera");
        solicitud3.setFecha("11-11-22");
        solicitud3.setVacacion(vacacion);

        solicitudVacacionRepository.save(solicitud2);

        when(solicitudVacacionRepository.findById(1L)).thenReturn(Optional.ofNullable(solicitud));

        Solicitud expected = solicitudServiceImp.encontrarSolicitudPorId(1L); //con el get se llama la iddentity
        assertThat(solicitud).isSameAs(expected);


    }

    @Test
    void encontrarSolicitudesPorEstadoTrue() {
        Role roleUser = roleRepository.findByName("USER");

        Set<Role> roles = new HashSet<Role>();

        roles.add(roleUser);

        Vacacion vacacion = new Vacacion();
        vacacion.setId(1L);
        vacacion.setFechaInicio("29-10-22");
        vacacion.setNumDias(3);
        vacacion.setFechaFinal("31-10-22");


        User user = new User();
        user.setId(117220056);
        user.setFirstName("Alfredo");
        user.setLastName("Guerrrro");
        user.setEmail("alfredo.guerrero@gmail.com");
        user.setUsername("117220056");
        user.setPassword("alfredo1234");
        user.setConfirmPassword("alfredo1234");
        user.setRoles(roles);

        User user1 = new User();
        user1.setId(444444);
        user1.setFirstName("JUAN");
        user1.setLastName("Guerrrro");
        user1.setEmail("alfredo.guerrero@gmail.com");
        user1.setUsername("4444444");
        user1.setPassword("alfredo1234");
        user1.setConfirmPassword("alfredo1234");
        user1.setRoles(roles);

        SolicitudVacacion solicitud1 = new SolicitudVacacion();
        solicitud1.setId(11L);
        solicitud1.setUsuario(user);
        solicitud1.setEstado("En espera");
        solicitud1.setFecha("10-10-22");
        solicitud1.setVacacion(vacacion);

        SolicitudVacacion solicitud2 = new SolicitudVacacion();
        solicitud2.setId(2L);
        solicitud2.setUsuario(user1);
        solicitud2.setEstado("En espera");
        solicitud2.setFecha("10-10-22");
        solicitud2.setVacacion(vacacion);

        SolicitudVacacion solicitud3 = new SolicitudVacacion();
        solicitud3.setId(3L);
        solicitud3.setUsuario(user1);
        solicitud3.setEstado("En espera");
        solicitud3.setFecha("11-11-22");
        solicitud3.setVacacion(vacacion);
        //solicitudRepository.save(solicitud2);
        solicitudVacacionRepository.save(solicitud2);

        List<SolicitudVacacion> solicituds = new LinkedList<>();
        solicituds.add(solicitud1);
        solicituds.add(solicitud2);
        solicituds.add(solicitud3);

        List<SolicitudVacacion> solicitudsExpec = new LinkedList<>();
        solicitudsExpec.add(solicitud1);
        solicitudsExpec.add(solicitud2);

        given(solicitudVacacionRepository.findAll()).willReturn(solicituds);

        List<SolicitudVacacion> givenFrom =  solicitudServiceImp.encontrarSolicitudesPorEstadoTrue();

        assertEquals(solicitudsExpec, givenFrom);
        //verify(solicitudRepository).findByUsuario(user1);

    }

    @Test
    void findSolicitudByEstadoTrue() {

        //when(solicitudVacacionRepository.findBy()).thenReturn(solicitud);
        Solicitud returned = solicitudServiceImp.findSolicitudByEstadoTrue(solicitud);
        assertThat(returned.isEstado()).isSameAs(true);
    }


}