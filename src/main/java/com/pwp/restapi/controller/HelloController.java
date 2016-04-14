package com.pwp.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping(value="/greeting")
	public String sayHello (Model model) {
		model.addAttribute("greeting", "This is hello world");
		
		return "hello";
	}
	
}
