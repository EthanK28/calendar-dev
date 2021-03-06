package com.mycompany.myapp.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myapp.domain.CalendarUser;
import com.mycompany.myapp.service.CalendarService;
import com.mycompany.myapp.service.DefaultCalendarService;;

@Controller
@RequestMapping(value = "/users")
public class AdminController {
	
	@Qualifier("calendarService")
	
	@Autowired
	CalendarService userService;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView viewRegistration(Model model, ModelAndView mav) {
		mav.addObject("message", "signup 페이지입니다.");
		
		CalendarUser userForm = new CalendarUser();    
        model.addAttribute("userForm", userForm);              
        mav.setViewName("/registration/signup");
         
        //return "user/signup";
        return mav;
    }
	
	
     
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegistration(@ModelAttribute("userForm") CalendarUser user, ModelAndView mav) {
         
        this.userService.createUser(user);
         
        // for testing purpose:
        System.out.println("id: " + user.getId());
        System.out.println("name: " + user.getName());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
        
        mav.setViewName("/registration/registrationSuccess");
        return mav;
    }
    
	@RequestMapping(value = "/myinfo", method = RequestMethod.GET)
	public ModelAndView myinfo(ModelAndView model) {
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		
		//CalendarUser user = this.userService.get(userDetail.getUsername());
		CalendarUser user = this.userService.getUserByEmail(userDetail.getUsername());
		model.addObject("user", user);
	  }
	  
	  model.setViewName("myinfo");
	  return model;
 	}
	
	
    
    /*
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public String allUsers(Locale locale, Model model) {
		List<User> users = this.userService.getAll();
		model.addAttribute("users", users);
		
		return "admin/allUsers";
	}*/
}
