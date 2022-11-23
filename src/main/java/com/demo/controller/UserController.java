package com.demo.controller;

import com.demo.dto.ChangePasswordForm;
import com.demo.entity.CorreoElectronico;
import com.demo.entity.SolicitudVacacion;
import com.demo.service.*;
import com.demo.entity.User;
import com.demo.entity.Vacacion;
import com.demo.repository.RoleRepository;

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

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    CorreoServiceImp correoServiceImp;


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
            User loggedUser = userService.getLoggedUser();
            model.addAttribute("usuario", loggedUser);
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

    @GetMapping("/ajustes")
    public String ajustesUser(Model model) {
        try {

            if (!userService.isLoggedUserADMIN()) {
                model.addAttribute("correoList", correoServiceImp.correoElectronicoList());
                model.addAttribute("lisTab", "active");

                return   "user-form/user-ajustes-view"; //en teoria si el usuario no es admin lo lleva a una ventana diferente
            } else {

                model.addAttribute("correoList", correoServiceImp.correoElectronicoList());
                model.addAttribute("lisTab", "active");
                return  "user-form/user-ajustes-view";
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

    @GetMapping("/informacion")
    public String postMostarUsuarioInformacion(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {

        User loggedUser = new User();
        try {
            loggedUser = userService.getLoggedUser();
        } catch (Exception e) {
            model.addAttribute("formErrorMessage",e.getMessage());
            model.addAttribute("userForm", loggedUser);
            model.addAttribute("formTab","active");
            model.addAttribute("roles",roleRepository.findAll());
            model.addAttribute("editMode", true);
            model.addAttribute("passwordForm", new ChangePasswordForm(user.getId()));
        }

        try {
            if (!userService.isLoggedUserADMIN()) {
                model.addAttribute("userForm", loggedUser);

                model.addAttribute("roles", roleRepository.findAll());
                model.addAttribute("formTab", "active");
                return   "user-form/user-user-informacion"; //en teoria si el usuario no es admin lo lleva a una ventana diferente
            } else {
                model.addAttribute("userForm", loggedUser);
                model.addAttribute("roles", roleRepository.findAll());
                model.addAttribute("formTab", "active");
                return   "user-form/user-user-informacion";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/ajustes/crearCorreo")
    public ResponseEntity correoCrearCorreo(@Valid @RequestBody CorreoElectronico correoElectrornico, Errors errors){


        CorreoElectronico electronico = correoElectrornico;
        electronico.setCorreoElectronico("administrador");

        try {


            //If error, just return a 400 bad request, along with the error message
            if (errors.hasErrors()){
                String result = errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(""));
                throw new Exception(result);
            }

            correoServiceImp.CrearCorreo(electronico);

        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("success");
    }



    @GetMapping("/userForm/cancel")
    public String cancelEditUser(ModelMap model){
        return "redirect:/userForm";
    }

    @GetMapping("/vacacionForm/cancel")
    public String cancelEnviarSolicitudVacacion(ModelMap model){

        return "redirect:/vacacionForm";
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

    @GetMapping("/eliminarCorreo/{id}")
    public String deleteCorreo(Model model, @PathVariable(name ="id") Long id){
        try {
            correoServiceImp.eliminarCorreo(id);


        }catch (Exception e){
            model.addAttribute("listErrorMessage", e.getMessage());
        }
        //retorna variable de arriba
        return "redirect::/informacion";
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


        Fecha fecha = new Fecha();
        User loggedUser =  new User();

        try {
            loggedUser = userService.getLoggedUser();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(result.hasErrors()){
            model.addAttribute("vacacionForm", vacacion);
            model.addAttribute("vacaFormTab", "active");
            model.addAttribute("vacacionList", solicitudVacacionImp.encontrarSolicitudesPorUsuario(loggedUser));
            //model.addAttribute("editMode", "true");

        }else {
            try {

                if(fecha.validarFechas(vacacion.getFechaInicio())== true || fecha.validarDias(vacacion.getNumDias())){
                    vacacion.setFechaFinal(fecha.calcularFecha(vacacion.getFechaInicio(),vacacion.getNumDias()));
                    model.addAttribute("vacacionForm", vacacion);
                    model.addAttribute("vacaFormTab","active");
                    model.addAttribute("vacacionList", solicitudVacacionImp.encontrarSolicitudesPorUsuario(loggedUser));

                    return  "vacacion-form/vacacion-view-despues-calcular";
                }



            }catch (Exception e) {
                model.addAttribute("formErrorMessage",e.getMessage());
                model.addAttribute("vacacionForm", vacacion);
                model.addAttribute("vacaFormTab","active");
                model.addAttribute("vacacionList", solicitudVacacionImp.encontrarSolicitudesPorUsuario(loggedUser));
            }

            model.addAttribute("vacacionList", solicitudVacacionImp.encontrarSolicitudesPorUsuario(loggedUser));
        }
        return "vacacion-form/vacacion-view";
    }

    @PostMapping("/vacacionForm/crearSolicitud")
    public ResponseEntity vacacionCrearSolicitudVacacion(@Valid @RequestBody Vacacion vacacion, Errors errors){


        User loggedUser =  new User();

        try {
            loggedUser = userService.getLoggedUser();

            //If error, just return a 400 bad request, along with the error message
            if (errors.hasErrors()){
                String result = errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(""));
                throw new Exception(result);
            }
            SolicitudVacacion solicitudVacacion = new SolicitudVacacion();
            solicitudVacacion.setVacacion(vacacion);
            solicitudVacacion.setUsuario(loggedUser);
            solicitudVacacion.setEstado("En espera");
            solicitudVacacionImp.crearSolicitudPersonal(solicitudVacacion);

        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("success");
    }




    @PostMapping("/vacacionForm/aceptar")
    public ResponseEntity aceptarSolicitud(Model model, @RequestBody  SolicitudVacacion solicitudVacacion, Errors errors){


            User loggedUser =  new User();

            try {
                loggedUser = userService.getLoggedUser();

                //If error, just return a 400 bad request, along with the error message
                if (errors.hasErrors()){
                    String result = errors.getAllErrors()
                            .stream().map(x -> x.getDefaultMessage())
                            .collect(Collectors.joining(""));
                    throw new Exception(result);
                }
                //Long   solicitudId = Long.parseLong(idSolicitud);
                solicitudVacacionImp.aceptarSolicitud(solicitudVacacion.getId());

            } catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }

            return ResponseEntity.ok("success");
        }



    @PostMapping("/vacacionForm/rechazar")
    public ResponseEntity rechazarSolicitud(Model model, @RequestBody  SolicitudVacacion solicitudVacacion, Errors errors){

        User loggedUser =  new User();

        try {
            loggedUser = userService.getLoggedUser();

            //If error, just return a 400 bad request, along with the error message
            if (errors.hasErrors()){
                String result = errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(""));
                throw new Exception(result);
            }

            solicitudVacacionImp.rechazarSolicitud(solicitudVacacion.getId());

        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("success");
    }

}
