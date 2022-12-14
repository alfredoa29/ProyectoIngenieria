package com.demo.service;

import com.demo.dto.ChangePasswordForm;
import com.demo.entity.*;
import com.demo.repository.RoleRepository;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("UserService")
public class UserServiceImpl implements UserService {

    //inyeccion de dependencia
    @Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleRepository roleRepository; //QUITAR DESPUES

    @Autowired
    ISolicitudService solicitudService; //quitar
    @Override
    public Iterable<User> getAllUsers() {

        return repository.findAll();
    }

    private boolean checkUsernameAvailable(User user) throws Exception {
        Optional<User> userFound = repository.findByUsername(user.getUsername());
        if (userFound.isPresent()) {
            throw new Exception("Username no disponible");
        }
        return true;
    }

    private boolean checkPasswordValid(User user) throws Exception {
        if ( !user.getPassword().equals(user.getConfirmPassword())) {
            throw new Exception("Password y Confirm Password no son iguales");
        }
        return true;
    }

    @Override
    public User createUser(User user) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

        if(checkUsernameAvailable(user) && checkPasswordValid(user)){
            /*modificar el password para que sea seguro*/
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            /*modificar el password para que sea seguro*/
            user = repository.save(user);
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("El id no existe"));
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        return  repository.findByUsername(username).orElseThrow(()-> new Exception("El usuario no existe"));
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Override
    public User updateUser(User fromUser) throws Exception {
        User toUser = getUserById(fromUser.getId());
        mapUser(fromUser, toUser);

        return repository.save(toUser);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @Override
    public User deleteUser(Long id) throws Exception {
        User user = getUserById(id);
        repository.delete(user);
        return user;
    }


    @Override
    public User changePassword(ChangePasswordForm form) throws Exception {
        User user = getUserById(form.getId());

        /*encoder.matches("123456", passwd)*/
        if(!isLoggedUserADMIN() && ! passwordEncoder.matches(form.getCurrentPassword(), user.getPassword())) {
            throw new Exception("Current Password invalido.");
        }
            if (passwordEncoder.matches(form.getNewPassword(), user.getPassword())){
                throw new Exception ("Nuevo debe ser diferente al password actual.");
            }
            if (!form.getNewPassword().equals(form.getConfirmPassword())){
                throw new Exception ("Nuevo Password y Confirm Password no coinciden.");
            }
            String encodePassword = bCryptPasswordEncoder.encode(form.getNewPassword());
            user.setPassword(encodePassword);

        return repository.save(user);
    }

    @Override
    public void deleteUserByUsername(String username) {
         repository.deleteUserByUsername(username);
    }


    @Override
    public boolean isLoggedUserADMIN() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails loggedUser = null;
        Object roles = null;

        //Verify obj get user session
        if (principal instanceof UserDetails) {
            loggedUser = (UserDetails) principal;
            roles = loggedUser.getAuthorities().stream()
                    .filter(x -> "ROLE_ADMIN".equals(x.getAuthority()))
                    .findFirst()
                    .orElse(null);
        }

        return roles != null;
    }

    @Override
    public boolean getUserRole(String username) throws Exception{



        return false;
    }

    @Override
    public String findByRoles(String roleDesc) {

        return repository.findByRoles(roleDesc);
    }

    @Override
    public User getLoggedUser() throws Exception {
        //Obtener el usuario logeado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails loggedUser = null;

        //Verificar que ese objeto traido de sesion es el usuario
        if (principal instanceof UserDetails) {
            loggedUser = (UserDetails) principal;
        }

        User myUser = repository
                .findByUsername(loggedUser.getUsername()).orElseThrow(() -> new Exception(""));

        return myUser;
    }


    protected void mapUser(User from, User to){
        to.setUsername(from.getUsername());
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setEmail(from.getEmail());
        to.setRoles(from.getRoles());
    }



}
