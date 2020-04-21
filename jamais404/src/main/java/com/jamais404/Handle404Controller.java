package com.jamais404;

import com.jamais404.model.*;
import com.jamais404.auth.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
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
    public String handleError(Model model, HttpServletRequest request, Authentication authentication) {
        model.addAttribute("active", authentication.getName());

        // Gets the URI that triggered the 404 error (to avoid /error)
        String originalUri = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
        model.addAttribute("url", originalUri);

        Page page = pageRepository.findByName(originalUri);
        
        if (page != null)
        {
            String ownerUsername = page.getOwner().getUsername();
            String datetime = page.getDatetime().toString();

            try {
                //FIXME
                Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.'SSSZ'").parse(datetime);
                datetime = new SimpleDateFormat("dd/MM/yyyy, Ka").format(date);
            } catch (ParseException e) { }
            
            model.addAttribute("datetime", datetime);

            if (ownerUsername.equals(authentication.getName())) {
                model.addAttribute("username", "you");
                model.addAttribute("userLink", authentication.getName());
            } else {
                model.addAttribute("username", ownerUsername);
                model.addAttribute("userLink", ownerUsername);
            }
            
            // Already found 404 error
            return "already_found404";
        }

        User owner = userRepository.findByUsername(authentication.getName());

        page = new Page();
        page.setName(originalUri);
        page.setOwner(owner);
        pageRepository.save(page);

        return "new404";
    }

    @Override
    public String getErrorPath() {
        return "getErrorPath";
    }
}