package com.demo.controller;

import com.demo.dto.ChangePasswordForm;
import com.demo.entity.SolicitudVacacion;
import com.demo.service.Fecha;
import com.demo.entity.User;
import com.demo.entity.Vacacion;
import com.demo.repository.RoleRepository;
import com.demo.service.RoleService;

import com.demo.service.ISolicitudService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

//@Validated
@Controller
@Transactional
public class UserController {

    //autowired es para inyeccion de independencias

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ISolicitudService solicitudVacacionImp;


    @GetMapping({"/", "/login"})
    public String index(){
        return "index";
    }


    @GetMapping("/usuario")
    public String redirectUsuarioMoule(ModelMap model){
        return "redirect:/userForm";

    }

    @GetMapping("/vacaciones")
    public String redirectVacacionModule(ModelMap model){
        return "redirect:/vacacionForm";

    }


    @GetMapping({ "/principal"})
    public String principal(Model model){
        try {

            return "header";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/userForm")
    public String userForm(Model model) {
        try {
            if (!userService.isLoggedUserADMIN()) {
                model.addAttribute("userForm", new User());
                model.addAttribute("userList", userService.getAllUsers());
                model.addAttribute("roles", roleRepository.findAll());
                model.addAttribute("lisTab", "active");
                return   "user-form/user-view"; //en teoria si el usuario no es admin lo lleva a una ventana diferente
            } else {
                model.addAttribute("userForm", new User());
                model.addAttribute("userList", userService.getAllUsers());
                model.addAttribute("roles", roleRepository.findAll());
                model.addAttribute("lisTab", "active");
                return  "user-form/user-view";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/userForm")
    public String postUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            model.addAttribute("userForm", user);
            model.addAttribute("formTab","active");
        }else {
            try {
                userService.createUser(user);
                model.addAttribute("userForm", new User());
                model.addAttribute("listTab","active");
            } catch (Exception e) {
                model.addAttribute("formErrorMessage",e.getMessage());
                model.addAttribute("userForm", user);
                model.addAttribute("formTab","active");
                model.addAttribute("userList", userService.getAllUsers());
                model.addAttribute("roles",roleRepository.findAll());
            }
        }

        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("roles",roleRepository.findAll());
        return "user-form/user-view";
    }
    //https://www.appsdeveloperblog.com/spring-security-preauthorize-annotation-example/
    @PreAuthorize("hasRole('ADMIN') or principal.username == #username")
    @GetMapping("/editUser/{username}")
    public String getEditUserForm(Model model, @PathVariable(name = "username") String username) throws Exception{

        User userToEdit = userService.getUserByUsername(username);

        if( userService.isLoggedUserADMIN()==true) {
            model.addAttribute("userForm", userToEdit);
            model.addAttribute("userList", userService.getAllUsers());
            model.addAttribute("roles", roleRepository.findAll());
            model.addAttribute("formTab", "active");
            model.addAttribute("editMode", true);
            model.addAttribute("passwordForm", new ChangePasswordForm(userToEdit.getId()));
            return "user-form/user-view";
        } else {
            return "redirect:/userForm";
        }
    }



    @PostMapping("/editUser")
    public String postEditUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model){

        if(result.hasErrors()){
            model.addAttribute("userForm", user);
            model.addAttribute("formTab", "active");
            model.addAttribute("editMode", "true");
            model.addAttribute("passwordForm", new ChangePasswordForm(user.getId()));
        }else {
            try {
                userService.updateUser(user);
                model.addAttribute("userForm", new User() );
                model.addAttribute("listTab", "active");

            } catch (Exception e) {
                model.addAttribute("formErrorMessage",e.getMessage());
                model.addAttribute("userForm", user);
                model.addAttribute("formTab","active");
                model.addAttribute("userList", userService.getAllUsers());
                model.addAttribute("roles",roleRepository.findAll());
                model.addAttribute("editMode", true);
                model.addAttribute("passwordForm", new ChangePasswordForm(user.getId()));
            }
            model.addAttribute("userList", userService.getAllUsers());
            model.addAttribute("roles", roleRepository.findAll());
        }
        return "user-form/user-view";
    }

    @GetMapping("/userForm/cancel")
    public String cancelEditUser(ModelMap model){
        return "redirect:/userForm";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(Model model, @PathVariable(name ="id") Long id){
        try {
            userService.deleteUser(id);


        }catch (Exception e){
            model.addAttribute("listErrorMessage", e.getMessage());
        }
        //retorna variable de arriba
        return userForm(model);
    }
    //Response ayuda a manejar una respuesta adecuada http"
    //Valid para validarr el formulario
    //RequestBody es objeto que se esta pasando como request asincrono
    @PostMapping("/editUser/changePassword")
    public ResponseEntity postEditUserChangePassword(@Valid @RequestBody ChangePasswordForm form, Errors errors){
        try {
            //If error, just return a 400 bad request, along with the error message
            if (errors.hasErrors()){
                String result = errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(""));
                throw new Exception(result);
            }
            userService.changePassword(form);

        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("success");
    }




    @GetMapping("/vacacionForm")
    public String vacacionForm(Model model) {
        try {
            User loggedUser = userService.getLoggedUser();
            //solicitudService = new SolicitudVacacionImp();
            model.addAttribute("vacacionForm", new Vacacion());
            model.addAttribute("vacacionList", solicitudVacacionImp.encontrarSolicitudesPorUsuario(loggedUser));
            model.addAttribute("vacacionAllSolicitudes",solicitudVacacionImp.encontrarTodos());
            model.addAttribute("vacaLisTab", "active");
            return "vacacion-form/vacacion-view";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/vacacionForm")
    public String postVacacionForm(@Valid @ModelAttribute("vacacionForm") Vacacion vacacion, BindingResult result, ModelMap model){

        User loggedUser =  new User();
        if(result.hasErrors()){
            model.addAttribute("vacacionForm", vacacion);
            model.addAttribute("vacaFormTab", "active");
            //model.addAttribute("editMode", "true");

        }else {
            try {


                loggedUser = userService.getLoggedUser();
                SolicitudVacacion solicitud = new SolicitudVacacion();
                solicitud.setEstado("En espera");
                solicitud.setUsuario(loggedUser);
                solicitud.setVacacion(vacacion);
                solicitudVacacionImp.crearSolicitudPersonal(solicitud);
                model.addAttribute("vacacionForm", new Vacacion() );
                model.addAttribute("vacacionList", "active");
                model.addAttribute("vacacionList", solicitudVacacionImp.encontrarSolicitudesPorUsuario(loggedUser));

            } catch (Exception e) {
                model.addAttribute("formErrorMessage",e.getMessage());
                model.addAttribute("vacacionForm", vacacion);
                model.addAttribute("vacaFormTab","active");
                model.addAttribute("vacacionList", solicitudVacacionImp.encontrarSolicitudesPorUsuario(loggedUser));


            }
            model.addAttribute("vacacionList", solicitudVacacionImp.encontrarSolicitudesPorUsuario(loggedUser));

        }
        return "vacacion-form/vacacion-view";
    }


    @GetMapping("/vacacionForm/aceptar/{id}")
    public String aceptarSolicitud(Model model, @PathVariable(name ="id") Long id){
        try {
            solicitudVacacionImp.aceptarSolicitud(id);


        }catch (Exception e){
            model.addAttribute("listErrorMessage", e.getMessage());
        }

        return "redirect:/vacacionForm";
    }

    @GetMapping("/vacacionForm/rechazar/{id}")
    public String rechazarSolicitud(Model model, @PathVariable(name ="id") Long id){
        try {
            solicitudVacacionImp.rechazarSolicitud(id);


        }catch (Exception e){
            model.addAttribute("listErrorMessage", e.getMessage());
        }

        return "redirect:/vacacionForm";
    }

}
