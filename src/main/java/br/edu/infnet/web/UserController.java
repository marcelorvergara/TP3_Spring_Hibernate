/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.infnet.web;

import br.edu.infnet.model.Role;
import br.edu.infnet.model.User;
import br.edu.infnet.service.RoleService;
import br.edu.infnet.service.SecurityService;
import br.edu.infnet.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author marcelo
 */
@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registro")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        //carregar a lista de roles
        List<Role> roles = roleService.findAll();

        List<Role> perfis = new ArrayList<>();

        for (Role perfil : roles) {
            log.info("perfil: " + perfil.getName());
            perfis.add(perfil);
        }

//        model.addAttribute("listaPerfis", perfis);
        model.addAttribute("userForm", new User("", "", perfis));

        return "registro";
    }

    @RequestMapping(value = "/registro")
    public String regsitration(@ModelAttribute("userForm") User userForm, @RequestParam("roles") String role, BindingResult bindingResult) {

        log.info("roles Id selecioado: " + role);
        log.warn("passei aqui");

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registro";
        }

        //pegar a role escolhida
        Role roleObj = roleService.findRoleById(Long.valueOf(role));

        log.warn("role escolhida: " + roleObj.getName());

        //criar um array, pois a relação é many to many
        List<Role> roleLst = new ArrayList<>();
        roleLst.add(new Role(Long.valueOf(role), roleObj.getName()));

        //gravar o user
        userService.save(new User(userForm.getUsername(), userForm.getPasswordConfirm(), roleLst));

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("login")
    public String login(Model model, String error, String logout) {
        System.out.println(securityService.isAuthenticated());
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null) {
            model.addAttribute("error", "Senha ou usuário inválidos");
        }

        if (logout != null) {
            model.addAttribute("message", "Sessão encerrada com sucesso");
        }

        return "login";

    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

}
