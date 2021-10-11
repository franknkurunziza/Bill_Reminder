package com.example.demo.Controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Models.Bill;
import com.example.demo.Models.User;
import com.example.demo.Services.BillService;
import com.example.demo.Services.EmailService;
import com.example.demo.Services.UserService;
import com.example.demo.Validator.UserValidator;

@Controller
public class MainController {
	
	@Autowired
	private UserService uServ;
	
	@Autowired
	private BillService bServ;
	
	@Autowired
	private UserValidator uValid;
	
	@Autowired
	private EmailService eServ;
	
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
		uValid.validate(user, result);
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
	
	@RequestMapping("/dashboard")
	public String home(RedirectAttributes redirectAttributes,HttpSession session, Model viewModel) {
		if (session.getAttribute("userId") == null) {            
			redirectAttributes.addFlashAttribute("error", "Must be logged in");  
		return "redirect:/login";     
		}
		Long userId=(Long) session.getAttribute("userId");
		User thisUser=uServ.findUserById(userId);
		viewModel.addAttribute("user",thisUser);
		return "dashboard.jsp";
	}
	
	@RequestMapping("/logout")
	public String logOutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping("/bill")
	public String billPage(@ModelAttribute("bill") Bill bill, HttpSession session,Model viewModel) {
		Long userId=(Long) session.getAttribute("userId");
		User userLoggedIn=this.uServ.findUserById(userId);
		viewModel.addAttribute("user", userLoggedIn);
		return "bill.jsp";
	}
	
	@RequestMapping("/createBill")
	public String createBill(@Valid @ModelAttribute("bill") Bill bill,BindingResult result,RedirectAttributes redirectAttributes, HttpSession session,Model viewModel) throws ParseException {
		if (session.getAttribute("userId") == null) {            
			redirectAttributes.addFlashAttribute("error", "Must be logged in");  
		return "redirect:/";     
		}
		Long userId=(Long) session.getAttribute("userId");
		User userLoggedIn=this.uServ.findUserById(userId);
//		System.out.println(bill.isPayed());
		System.out.println(bill.getDate());
		if(result.hasErrors()) {
			return "bill.jsp";
		}
		//set a user to a bill
		bill.setUser(userLoggedIn);
		Bill thisBill=bServ.createBill(bill);
//		System.out.println(bill.getPayDate());
		// send an email to the user
		String userEmail=userLoggedIn.getEmail();
		String billName=thisBill.getName();
		eServ.sendSimpleMail(userEmail, "You have created a reminder bill For "+billName, "Bill Reminder ");
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/pay/{id}")
	public String payBill(@PathVariable("id") Long Id,RedirectAttributes redirectAttributes, HttpSession session) {
		if (session.getAttribute("userId") == null) {            
			redirectAttributes.addFlashAttribute("error", "Must be logged in");  
		return "redirect:/";     
		}
		Bill thisbill=this.bServ.findBillbyId(Id);
		this.bServ.paybill(thisbill);
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/unpayed")
	public String unpayedBill(RedirectAttributes redirectAttributes,HttpSession session,Model viewModel) {
		if (session.getAttribute("userId") == null) {            
			redirectAttributes.addFlashAttribute("error", "Must be logged in");  
		return "redirect:/login";     
		}
		Long userId=(Long) session.getAttribute("userId");
		User userLoggedIn=this.uServ.findUserById(userId);
		viewModel.addAttribute("user", userLoggedIn);
		return "unpayed.jsp";
	}
	
	@RequestMapping("/edit/{id}")
	public String editBill(@PathVariable("id") Long Id, Model viewModel,RedirectAttributes redirectAttributes,  HttpSession session) {
		
		if (session.getAttribute("userId") == null) {            
			redirectAttributes.addFlashAttribute("error", "Must be logged in");  
		return "redirect:/login";     
		}
		
		Bill thisbill=this.bServ.findBillbyId(Id);
		Long userId=(Long) session.getAttribute("userId");
		User loggedIn=this.uServ.findUserById(userId);
		viewModel.addAttribute("bill", thisbill);
		viewModel.addAttribute("userLogged", loggedIn);
		return "edit.jsp";
	}
	
	@RequestMapping("/update/{id}")
	public String updateBill(@ModelAttribute("bill")Bill bill,@PathVariable("id") Long Id,RedirectAttributes redirectAttributes, HttpSession session)  {
		if (session.getAttribute("userId") == null) {            
			redirectAttributes.addFlashAttribute("error", "Must be logged in");  
		return "redirect:/login";     
		}
		Long userId=(Long) session.getAttribute("userId");
		User loggedIn=this.uServ.findUserById(userId);
		bill.setUser(loggedIn);
		bServ.updateBill(bill);
		
		return "redirect:/dashboard";
	}
	@RequestMapping("/delete/{id}")
	public String deleteBill(@PathVariable("id") Long Id,HttpSession session) {
		Bill thisBill=this.bServ.findBillbyId(Id);
		this.bServ.deleteBill(thisBill);
		Long userId=(Long) session.getAttribute("userId");
		User userLoggedIn=this.uServ.findUserById(userId);
		String userEmail=userLoggedIn.getEmail();
		String billName=thisBill.getName();
		eServ.sendSimpleMail(userEmail, "You have Removed a reminder bill "+billName, "Bill Reminder ");
		return "redirect:/dashboard";
	}
	
	

}
