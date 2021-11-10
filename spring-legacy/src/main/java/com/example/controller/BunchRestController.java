package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BunchVO;
import com.example.domain.Criteria;
import com.example.domain.MemberVO;
import com.example.service.QuizService;

@RestController
@RequestMapping("/api/*")
public class BunchRestController {
	
	@Autowired
	private QuizService quizService;
	
	@GetMapping(value = "/bunches", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<BunchVO>> getAll() {
		
		Criteria cri = new Criteria();
		
		List<BunchVO> bunchList = quizService.getBunchesByCri(cri);
		
		return new ResponseEntity<List<BunchVO>>(bunchList, HttpStatus.OK);
	} // getAll
	
	
	
}
