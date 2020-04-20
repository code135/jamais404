package com.jamais404;

import com.jamais404.models.*;
import com.jamais404.repositories.*;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

	/**
	 * Home page
	 * @return
	 */
	@RequestMapping(value = "/")
	public String home(Model model) {
        
        //TODO

        model.addAttribute("active", "TODO");

		return "home";
	}

	/**
	 * Search form
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam String query) {
		String redirectUrl = "redirect:/" + query;
		
		return new ModelAndView(redirectUrl);
	}

	/**
	 * User profile page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user")
	public String user(Model model, @RequestParam String username) {
        
        User user = userRepository.findByName(username);
        String name = user.getName();

        // TODO

        model.addAttribute("active", name);
        
        List<String> pages = List.of(
            "page1",
            "page2",
            "page3"
        );

		model.addAttribute("username", "euuuuuuh");
        model.addAttribute("nbFound", pages.size());
        model.addAttribute("pages", pages);

		return "user";
	}
}