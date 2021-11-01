package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = { "/", "/index" })
	public String home() {
		System.out.println("home() 호출됨...");

		return "index";
	}

}
