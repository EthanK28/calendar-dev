package com.mycompany.myapp.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public ModelAndView createEvent(Locale locale, ModelAndView mav) {
		mav.addObject("message", "event를 생성하세요.");
		mav.setViewName("createEvent");
				
		
		return mav;
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
		
		
		List<CalendarUser> listCalendarUsers = calendarUserDao.findAllusers();
		List<Event> listEvents = eventDao.findAllEvents();
		List<Date> datelist = new ArrayList<Date>();
		
		mav.addObject("calendarUsers", listCalendarUsers);		
		mav.addObject("events", listEvents);
		mav.addObject("eventAttendees", eventAttendees);
		
		mav.setViewName("myEvent");
		
		return mav;
	}
}
