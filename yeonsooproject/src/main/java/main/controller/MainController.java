package main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.controller.BoardController;

@Controller
public class MainController{
	
	protected static final Logger logger = LoggerFactory.getLogger(MainController.class);
	protected static final Logger filelogger = LoggerFactory.getLogger("fileLogger");
	
	public static void main(String[] args){
		logger.debug("MainController");
		filelogger.debug("fileLogger logger print");
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(required=false, defaultValue="1") int pg) {
		ModelAndView mav = new ModelAndView();
		//System.out.println(pg);
		mav.addObject("pg", pg);
		mav.setViewName("/display");
		return mav;
	}
}
