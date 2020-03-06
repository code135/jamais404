package com.jamais404;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HomeController {

	@RequestMapping(value = { "/home", "/" })
	public String sayHello2(@RequestParam(name="name", required=false, defaultValue="MyName") String name, Model model) {
		model.addAttribute("name", name);
	
		return "home";
	}
}