package com.jamais404;

import com.jamais404.models.*;
import com.jamais404.repositories.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class Handle404Controller implements ErrorController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PageRepository pageRepository;

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
        model.addAttribute("url", originalUri);

        Page page = pageRepository.findByName(originalUri);
        
        if (page != null)
        {
            model.addAttribute("datetime", page.getDatetime());
            model.addAttribute("username", page.getOwner().getName());

            // Already found 404 error
            return "already_found404";
        }

        page = new Page();
        page.setName(originalUri);

        long id = 1;
        User user = userRepository.findById(id).get();
        page.setOwner(user);
        pageRepository.save(page);

        return "new404";
    }

    @Override
    public String getErrorPath() {
        return "getErrorPath";
    }
}