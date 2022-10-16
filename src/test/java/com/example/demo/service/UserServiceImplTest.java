package com.example.demo.service;

import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.repository.RoleRepository;
import com.demo.repository.UserRepository;
import com.demo.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


@Mock
private UserRepository userRepository;

@Mock
private RoleRepository roleRepository;

@InjectMocks
private UserServiceImpl userService;
private User user;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        Role roleUser = roleRepository.findByName("USER");

        Set<Role> roles = new HashSet<Role>();

        roles.add(roleUser);

        user = new User();
        user.setFirstName("Alfredo");
        user.setLastName("Guerrrro");
        user.setEmail("alfredo.guerrero@gmail.com");
        user.setUsername("117220056");
        user.setPassword("alfredo1234");
        user.setConfirmPassword("alfredo1234");
        user.setRoles(roles);
    }

    @Test
    void getUserById() {
        when(userRepository.findByUsername("117220056")).thenReturn(Optional.ofNullable(user));
    }

    @Test
    void createUser() throws Exception {

        Role roleUser = roleRepository.findByName("USER");

        Set<Role> roles = new HashSet<Role>();

        roles.add(roleUser);

        User user1 = new User();
        user1.setFirstName("Alfredo");
        user1.setLastName("Guerrrro");
        user1.setEmail("alfredo.guerrero@gmail.com");
        user1.setUsername("117220056");
        user1.setPassword("alfredo1234");
        user1.setConfirmPassword("alfredo1234");
        user1.setRoles(roles);

            when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user1);

            User created = userService.createUser(user1);
            assertThat(created.getUsername()).isSameAs(user.getUsername());
            verify(userRepository).save(user1);

            //assertNotNull(userService.createUser(user));


    }

    @Test
    void getAllUsers() {
        Role roleUser = roleRepository.findByName("USER");

        Set<Role> roles = new HashSet<Role>();

        roles.add(roleUser);

        User user1 = new User();
        user1.setFirstName("Emanuel");
        user1.setLastName("Guerrrro");
        user1.setEmail("alfredo.guerrero@gmail.com");
        user1.setUsername("33333");
        user1.setPassword("emanuel03");
        user1.setConfirmPassword("emanuel03");
        user1.setRoles(roles);
        userRepository.save(user1);

        given(userRepository.findAll()).willReturn(List.of(user, user1));

        Iterable<User> userList = userService.getAllUsers();

        assertThat(userList).isNotNull();
        assertThat(userList.iterator().equals(2));
        //Assertions.assertThat(userList.iterator().);
    }

    @Test
    void deleteUserByUsername() throws Exception {
        String userUsername = "117220056";
        willDoNothing().given(userRepository).deleteUserByUsername(userUsername);

        userService.deleteUserByUsername(userUsername);
    }

    @Test
    void getUserByUsername() {

        Role roleUser = roleRepository.findByName("USER");

        Set<Role> roles = new HashSet<Role>();

        roles.add(roleUser);

        User user1 = new User();
        user1.setFirstName("Emanuel");
        user1.setLastName("Guerrrro");
        user1.setEmail("alfredo.guerrero@gmail.com");
        user1.setUsername("33333");
        user1.setPassword("emanuel03");
        user1.setConfirmPassword("emanuel03");
        user1.setRoles(roles);
        userRepository.save(user1);

        Iterable<User> userList = new ArrayList<>();


        given(userRepository.findByUsername("117220056")).willReturn(Optional.of(user));

        User expected = userRepository.findByUsername("117220056").get();
        assertThat(user).isSameAs(expected);

    }
}