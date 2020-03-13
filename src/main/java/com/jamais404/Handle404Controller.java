package com.jamais404;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class Handle404Controller implements ErrorController {

    @RequestMapping("/error")
    @ResponseStatus(HttpStatus.OK)
    public String handleError(Model model) {
        boolean new404 = false;

        model.addAttribute("url", "/test");

        if (new404) {
            return "new404";
        }

        model.addAttribute("username", "admin");
        model.addAttribute("datetime", "13.03.2020 11:06");

        return "already_found404";
    }

    @Override
    public String getErrorPath() {
        return "getErrorPath";
    }
}