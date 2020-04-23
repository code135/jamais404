package com.jamais404;

import com.jamais404.model.*;
import com.jamais404.auth.repository.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

	/**
	 * Home page
	 * @return
	 */
	@GetMapping(value = "/")
	public String home(Model model, Authentication authentication) {
        model.addAttribute("active", authentication.getName());

		return "home";
	}

	/**
	 * Search form
	 * @return
	 */
	@PostMapping(value = "/search")
	public ModelAndView search(@RequestParam String query, Authentication authentication) {
        String redirectUrl = "redirect:/" + query;
		
		return new ModelAndView(redirectUrl);
	}

	/**
	 * User profile page
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/user")
	public String user(Model model, @RequestParam String username, Authentication authentication) {
        model.addAttribute("active", authentication.getName());
        
        User user = userRepository.findByUsername(username);

        //FIXME: alphabetic order
        Set<String> pagesNames = user.getPages()
            .stream()
            .parallel()
            .map(Page::getName)
            .collect(Collectors.toSet());
        
        model.addAttribute("username", username);
        model.addAttribute("pages", pagesNames);
        model.addAttribute("nbFound", pagesNames.size());

		return "user";
	}
}