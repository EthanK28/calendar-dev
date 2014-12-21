package com.mycompany.myapp.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myapp.dao.CalendarUserDao;
import com.mycompany.myapp.dao.EventAttendeeDao;
import com.mycompany.myapp.dao.EventDao;
import com.mycompany.myapp.domain.CalendarUser;
import com.mycompany.myapp.domain.Event;
import com.mycompany.myapp.domain.EventAttendee;
import com.mycompany.myapp.service.CalendarService;




/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/events")
public class EventController {
	@Autowired
	private CalendarService calendarService;	
	
	@Autowired
	CalendarUserDao calendarUserDao;
	
	@Autowired
	EventDao eventDao;
	
	@Autowired
	EventAttendeeDao eventAttendeeDao;
	
	private CalendarUser[] calendarUsers = null;
	private Event[] events = null;
	private EventAttendee[] eventAttendees = null;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView createEvent(Locale locale, Model model, ModelAndView mav) {
		mav.addObject("message", "event를 생성하세요.");
		//, @ModelAttribute("createEvent") Event event
		//@ModelAttribute("userForm") CalendarUser use
		//CalendarUser userForm = new CalendarUser();
		Event eventForm = new Event();    
        model.addAttribute("eventForm", eventForm);
		mav.setViewName("/event/createEvent");			
		
		return mav;
	}
	
	@RequestMapping(value = "/eventcreation", method = RequestMethod.POST)
    public String processCreateEvent(@ModelAttribute("eventForm") Event event , Model model) {
        //@ModelAttribute("userForm") User user, Model model
        //this.calendarService.createEvent(event);
        System.out.println(event.getId());
        System.out.println(event.getWhen());
        System.out.println(event.getDescription());
        System.out.println(event.getSummary());        
        System.out.println(event.getEventLevel());
         
        //mav.setViewName("/event/createEventSucces");
        return "event/createEventSuccess";
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView findAllEvents(Locale locale, ModelAndView mav) {
		mav.addObject("message", "event를 생성하세요.");
		
		
		//List<CalendarUser> listCalendarUsers = calendarUserDao.findAllusers();
		List<Event> listEvents = eventDao.findAllEvents();
		//List<Date> datelist = new ArrayList<Date>();
		
		//mav.addObject("calendarUsers", listCalendarUsers);		
		mav.addObject("events", listEvents);
		//mav.addObject("eventAttendees", eventAttendees);
		
		mav.setViewName("/event/allevents");
		
		return mav;
	}
	
	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public ModelAndView myEvent(Locale locale, ModelAndView mav) {
		mav.addObject("message", "event를 생성하세요.");
		
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  
		  if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			//CalendarUser user = this.userService.get(userDetail.getUsername());
			int userId = calendarService.getUserByEmail(userDetail.getUsername()).getId();
			System.out.println("userID: "+userId+" userName: "+userDetail.getUsername());
			
			
			List<Event> myEventList= calendarService.getEventForOwner(userId);
			System.out.println("myEventList: "+ myEventList.get(0).getId()+"Description: "
					+myEventList.get(0).getDescription());
			for (Event e : myEventList)
			{
				System.out.println(e.getId());
				System.out.println(e.getOwner());
				System.out.println(e.getDescription());
				System.out.println(e.getSummary());
				
			}	
				
			mav.addObject("userId", userId);
			mav.addObject("myEventList", myEventList);
		  }
		  
		mav.setViewName("event/myinfo");
			
		return mav;
		  
		
/*		
		List<CalendarUser> listCalendarUsers = calendarUserDao.findAllusers();
		List<Event> listEvents = calendarService.getEventForOwner(8);
		
		List<Date> datelist = new ArrayList<Date>();
		
		
		mav.addObject("calendarUsers", listCalendarUsers);		
		mav.addObject("events", listEvents);
		mav.addObject("eventAttendees", eventAttendees);
		*/
	
	}
}
