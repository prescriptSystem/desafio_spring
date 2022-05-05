package me.dio.academia.digital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import me.dio.academia.digital.entity.Matricula;

@Controller
public class IndexController {
	
	@RequestMapping(value="/", method = RequestMethod.GET )
	public ModelAndView form(Matricula matricula) 
	{
		
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

}
