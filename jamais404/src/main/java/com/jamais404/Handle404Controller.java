package com.jamais404;

import com.jamais404.model.*;
import com.jamais404.tools.TimeStampTools;
import com.jamais404.auth.repository.*;

import java.sql.Timestamp;
import com.jamais404.repository.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class Handle404Controller implements ErrorController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PageRepository pageRepository;

    /**
     * Handles every route different from the ones registered in the HomeController.
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request,
        Authentication authentication,
        @RequestParam(value = "msg", defaultValue = "") String msg) {
        model.addAttribute("active", authentication.getName());

        // Gets the URI that triggered the 404 error (to avoid /error)
        String originalUri = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
        model.addAttribute("url", originalUri);

        Page page = pageRepository.findByName(originalUri);

        if (page != null) {
            String ownerUsername = page.getOwner().getUsername();
            Timestamp datetime = page.getDatetime();
            String stringDatetime = TimeStampTools.timeStampToString(datetime);
            
            model.addAttribute("datetime", stringDatetime);

            if (ownerUsername.equals(authentication.getName())) {
                model.addAttribute("username", "you");
                model.addAttribute("userLink", authentication.getName());
            } else {
                model.addAttribute("username", ownerUsername);
                model.addAttribute("userLink", ownerUsername);
            }

            List<Comment> comments = page.getComments()
                .stream()
                .parallel()
                .sorted((c1, c2) -> - c1.getDatetime().compareTo(c2.getDatetime()))
                .collect(Collectors.toList());
            
            model.addAttribute("comments", comments);

            // Message - from the submission of a comment - to display in a toast
            model.addAttribute("msg", msg);
            
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