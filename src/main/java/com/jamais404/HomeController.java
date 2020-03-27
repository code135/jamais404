package com.jamais404;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	/**
	 * Home page
	 * @return
	 */
	@RequestMapping(value = "/")
	public String home() {
		return "home";
	}

	/**
	 * Search form
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search() {
		// TODO
		return "home";
	}

	@RequestMapping(value = "/user/{username}")
	public String user(Model model) {
		
		// TODO
		model.addAttribute("username", "admin");
		model.addAttribute("nbFound", "1");

		return "user";
	}
}