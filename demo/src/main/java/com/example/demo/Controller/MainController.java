package com.example.demo.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Models.User;
import com.example.demo.Services.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService uServ;
	
	@RequestMapping("/")
	public String Register(@ModelAttribute("user") User user) {
		return "index.jsp";
	}
	
	@RequestMapping("/login")
	public String loginpage() {
		return "login.jsp";
	}
	
	@RequestMapping(value="registration", method=RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result,HttpSession session) {
		if(result.hasErrors()) {
			return "index.jsp";
		}
		User thisUser=uServ.registerUser(user);
		session.setAttribute("userId", thisUser.getId());
		
		return "redirect:/dashboard";	
	}
	
	@RequestMapping(value="authanticate", method=RequestMethod.POST)
	public String loginUser(@RequestParam("email") String email , @RequestParam("password") String password , RedirectAttributes errors,HttpSession session) {
		boolean isLoggedIn=uServ.authenticateUser(email, password);
		if(isLoggedIn) {
			User user=uServ.findByEmail(email);
			session.setAttribute("userId", user.getId());
			return "redirect:/dashboard";
		}
		else {
			errors.addFlashAttribute("error", "Email or PassWord is Invalid ,Try Again!");
			return "redirect:/login";
		}
	}
	
	
	
}
