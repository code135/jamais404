package com.jamais404;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Handle404Controller implements ErrorController {

    /**
     * Handles every route different from the ones registered in
     * the HomeController.
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request) {
        // Gets the URI that triggered the 404 error (to avoid /error)
        String originalUri = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();

        //FIXME: temporary
        boolean new404 = true;

        model.addAttribute("active", "TODO");

        //FIXME: temporary
        model.addAttribute("url", originalUri);
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