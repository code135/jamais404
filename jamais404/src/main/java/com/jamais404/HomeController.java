package com.jamais404;

import com.jamais404.model.*;
import com.jamais404.repository.*;
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

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private CommentRepository commentRepository;

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
        // Trims the slashes ; they cause troubles with the URLs
		String redirectUrl = "redirect:/" + query.replace("/", "");
		
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

        // Fetches the user pages names (sorted by alphabetical order)
        Set<String> pagesNames = user.getPages()
            .stream()
            .parallel()
            .map(Page::getName)
            .sorted(String::compareTo)
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
        // If the user has modified the HTML or JS before submitting a comment
        String msg = "Do not modify the code !";

        // The page must exist (be found) in order to add a comment to it
        if (page != null) {
            Comment comment = new Comment();
            comment.setText(text);
            comment.setUser(user);
            comment.setPage(page);
            commentRepository.save(comment);

            msg = "Comment added !";
        }
        
        // Refreshes the page and shows a message in a toast
        String redirectUrl = "redirect:" + url + "?msg=" + msg;
		
		return new ModelAndView(redirectUrl);
	}
}