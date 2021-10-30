package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Criteria;
import com.example.domain.PackageVO;
import com.example.domain.PageDTO;
import com.example.service.QuizService;

@Controller
@RequestMapping("/quiz/*")
public class QuizController {
	
	private QuizService quizService;
	
	public QuizController(QuizService quizService) {
		super();
		this.quizService = quizService;
	}

	@GetMapping("/list")
	public String list(Criteria cri, Model model) {
		
		System.out.println("/list() 호출됨 ... ");
		
		List<PackageVO> packageList = quizService.getPackagesByCri(cri);
		
		int totalCount = packageList.size();
		
		PageDTO pageDTO = new PageDTO(cri, totalCount);
		
		model.addAttribute("packageList", packageList);
		model.addAttribute("pageMaker", pageDTO);
		
		return "quiz/quizList";
	}
	
	@GetMapping("/write")
	public String writeForm() {
		
		return "quiz/quizWrite";
	}
	
	

}
