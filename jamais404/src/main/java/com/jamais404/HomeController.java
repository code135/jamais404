package com.jamais404;

import com.jamais404.model.*;
import com.jamais404.repository.*;
import com.jamais404.auth.repository.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private CommentRepository commentRepository;

	/**
	 * Home page
	 * @return
	 */
	@RequestMapping(value = "/")
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
	@RequestMapping(value = "/user")
	public String user(Model model, @RequestParam String username, Authentication authentication) {
        model.addAttribute("active", authentication.getName());
        
        User user = userRepository.findByUsername(username);

        //FIXME: alphabetic order
        Set<String> pagesNames = user.getPages()
            .stream()
            .parallel()
            .map(p -> p.getName())
            .collect(Collectors.toSet());
        
        model.addAttribute("username", username);
        model.addAttribute("pages", pagesNames);
        model.addAttribute("nbFound", pagesNames.size());

		return "user";
    }
    
    @PostMapping(value = "/comment")
	public ModelAndView comment(Model model, @RequestParam String text, @RequestParam String url, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        Page page = pageRepository.findByName(url);
        String msg = "Do not modify the code !";

        if (page != null) {
            Comment comment = new Comment();
            comment.setText(text);
            comment.setUser(user);
            comment.setPage(page);
            commentRepository.save(comment);

            msg = "Comment added !";
        }
        
        String redirectUrl = "redirect:" + url + "?msg=" + msg;
		
		return new ModelAndView(redirectUrl);
	}
}