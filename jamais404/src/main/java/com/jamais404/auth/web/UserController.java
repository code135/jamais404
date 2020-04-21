package com.jamais404.auth.web;

import com.jamais404.model.User;

import javax.validation.Valid;

import com.jamais404.auth.service.SecurityService;
import com.jamais404.auth.service.UserService;
import com.jamais404.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(user);
        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Invalid login !");

        if (logout != null)
            model.addAttribute("success", "You have been logged out successfully.");

        return "login";
    }

    // login POST controller ; it is provided by Spring Security

}