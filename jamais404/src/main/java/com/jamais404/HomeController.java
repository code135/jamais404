package com.jamais404;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		
		// TODO
		model.addAttribute("username", username);
		model.addAttribute("nbFound", "1");

		return "user";
	}
}