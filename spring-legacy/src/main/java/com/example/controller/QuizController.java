package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz/*")
public class QuizController {
	
	@GetMapping("/list")
	public String list() {
		
		return "quiz/quizList";
	}
	
	@GetMapping("/write")
	public String writeForm() {
		
		return "quiz/quizWrite";
	}

}
