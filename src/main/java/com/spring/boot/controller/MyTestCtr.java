package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/retailmanager")
public class MyTestCtr {

	@GetMapping(value="/")
	public ModelAndView init()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping(value="/home")
	public ModelAndView home()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Home");
		return mv;
	}
}
