package com.example.demo.service;

import com.demo.entity.Role;
import com.demo.entity.Solicitud;
import com.demo.entity.User;
import com.demo.entity.Vacacion;
import com.demo.repository.RoleRepository;
import com.demo.repository.SolicitudRepository;
import com.demo.service.SolicitudServiceImp;
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
class SolicitudServiceImpTest {

    @Mock
    private SolicitudRepository solicitudRepository;


    private  Solicitud  solicitud;

    @Mock
    RoleRepository roleRepository;
    @InjectMocks
    private SolicitudServiceImp solicitudServiceImp;



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

        solicitud =  new Solicitud();
        solicitud.setId(1L);
        solicitud.setUsuario(user);
        solicitud.setEstado(true);
        solicitud.setFecha("10-10-22");
        solicitud.setQuioscoPersonal(vacacion);
        solicitud.getFecha();
        solicitud.getQuioscoPersonal().getId();

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
        user1.setEmail("alfredo.guerrero@gmail.com");
        user1.setUsername("4444444");
        user1.setPassword("alfredo1234");
        user1.setConfirmPassword("alfredo1234");
        user1.setRoles(roles);

        Solicitud solicitud3 = new Solicitud();
        solicitud3.setId(1L);
        solicitud3.setUsuario(user1);
        solicitud3.setEstado(false);
        solicitud3.setFecha("11-11-22");
        solicitud3.setQuioscoPersonal(vacacion);
        solicitud3.getFecha();
        solicitud3.getQuioscoPersonal().getId();


        when(solicitudRepository.save(ArgumentMatchers.any(Solicitud.class))).thenReturn(solicitud3);
        Solicitud created = solicitudServiceImp.crearSolicitudPersonal(solicitud3);
        assertThat(created.getId()).isSameAs(solicitud.getId());
        verify(solicitudRepository).save(solicitud3);

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

        Solicitud solicitud1 = new Solicitud();
        solicitud1.setId(11L);
        solicitud1.setUsuario(user);
        solicitud1.setEstado(false);
        solicitud1.setFecha("10-10-22");
        solicitud1.setQuioscoPersonal(vacacion);
        solicitud1.getFecha();
        solicitud1.getQuioscoPersonal().getId();

        Solicitud solicitud2 = new Solicitud();
        solicitud2.setId(2L);
        solicitud2.setUsuario(user1);
        solicitud2.setEstado(false);
        solicitud2.setFecha("10-10-22");
        solicitud2.setQuioscoPersonal(vacacion);
        solicitud2.getFecha();
        solicitud2.getQuioscoPersonal().getId();

        Solicitud solicitud3 = new Solicitud();
        solicitud3.setId(3L);
        solicitud3.setUsuario(user1);
        solicitud3.setEstado(false);
        solicitud3.setFecha("11-11-22");
        solicitud3.setQuioscoPersonal(vacacion);
        solicitud3.getFecha();
        solicitud3.getQuioscoPersonal().getId();
        //solicitudRepository.save(solicitud2);
        solicitudRepository.save(solicitud2);

        List<Solicitud> solicituds = new LinkedList<>();
        solicituds.add(solicitud1);
        solicituds.add(solicitud2);
        solicituds.add(solicitud3);

        List<Solicitud> solicitudsExpec = new LinkedList<>();
        solicitudsExpec.add(solicitud1);

       // Iterable<Solicitud> solicituds2 = solicitudServiceImp.encontrarSolicitudesPorUsuario("117220056");


        //Long id = Long.valueOf(11);
        given(solicitudRepository.findAll()).willReturn(solicituds);

        List<Solicitud> givenFrom =  solicitudServiceImp.encontrarSolicitudesPorUsuario(user);

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

        Solicitud solicitud2 = new Solicitud();
        solicitud2.setId(2L);
        solicitud2.setUsuario(user1);
        solicitud2.setEstado(false);
        solicitud2.setFecha("10-10-22");
        solicitud2.setQuioscoPersonal(vacacion);
        solicitud2.getFecha();
        solicitud2.getQuioscoPersonal().getId();

        Solicitud solicitud3 = new Solicitud();
        solicitud3.setId(3L);
        solicitud3.setUsuario(user1);
        solicitud3.setEstado(false);
        solicitud3.setFecha("11-11-22");
        solicitud3.setQuioscoPersonal(vacacion);
        solicitud3.getFecha();
        solicitud3.getQuioscoPersonal().getId();
        solicitudRepository.save(solicitud2);

        when(solicitudRepository.findById(1L)).thenReturn(Optional.ofNullable(solicitud));

        Solicitud expected = solicitudServiceImp.encontrarSolicitudPorId(1L).get(); //con el get se llama la iddentity
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

        Solicitud solicitud1 = new Solicitud();
        solicitud1.setId(11L);
        solicitud1.setUsuario(user);
        solicitud1.setEstado(true);
        solicitud1.setFecha("10-10-22");
        solicitud1.setQuioscoPersonal(vacacion);
        solicitud1.getFecha();
        solicitud1.getQuioscoPersonal().getId();

        Solicitud solicitud2 = new Solicitud();
        solicitud2.setId(2L);
        solicitud2.setUsuario(user1);
        solicitud2.setEstado(true);
        solicitud2.setFecha("10-10-22");
        solicitud2.setQuioscoPersonal(vacacion);
        solicitud2.getFecha();
        solicitud2.getQuioscoPersonal().getId();

        Solicitud solicitud3 = new Solicitud();
        solicitud3.setId(3L);
        solicitud3.setUsuario(user1);
        solicitud3.setEstado(false);
        solicitud3.setFecha("11-11-22");
        solicitud3.setQuioscoPersonal(vacacion);
        solicitud3.getFecha();
        solicitud3.getQuioscoPersonal().getId();
        //solicitudRepository.save(solicitud2);
        solicitudRepository.save(solicitud2);

        List<Solicitud> solicituds = new LinkedList<>();
        solicituds.add(solicitud1);
        solicituds.add(solicitud2);
        solicituds.add(solicitud3);

        List<Solicitud> solicitudsExpec = new LinkedList<>();
        solicitudsExpec.add(solicitud1);
        solicitudsExpec.add(solicitud2);

        given(solicitudRepository.findAll()).willReturn(solicituds);

        List<Solicitud> givenFrom =  solicitudServiceImp.encontrarSolicitudesPorEstadoTrue();

        assertEquals(solicitudsExpec, givenFrom);
        //verify(solicitudRepository).findByUsuario(user1);

    }

    @Test
    void findSolicitudByEstadoTrue() {

        when(solicitudRepository.findAllByEstadoTrue()).thenReturn(solicitud);
        Solicitud returned = solicitudServiceImp.findSolicitudByEstadoTrue(solicitud);
        assertThat(returned.isEstado()).isSameAs(true);
    }

    @Test
    void encontrarSolicitudesPorEstadoFalse() {
    }
}