package com.jamais404;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class Handle404Controller implements ErrorController {

    /**
     * Handles every route different from the ones registered in
     * the HomeController.
     * @param model
     * @return
     */
    @RequestMapping("/error")
    @ResponseStatus(HttpStatus.OK)
    public String handleError(Model model) {
        
        //FIXME: temporary
        boolean new404 = true;

        //FIXME: temporary
        model.addAttribute("url", "/test");
        model.addAttribute("username", "admin");

        // New 404 error found
        if (new404) {
            return "new404";
        }

        //FIXME: temporary
        model.addAttribute("datetime", "13.03.2020 11:06");

        // Already found 404 error
        return "already_found404";
    }

    @Override
    public String getErrorPath() {
        return "getErrorPath";
    }
}