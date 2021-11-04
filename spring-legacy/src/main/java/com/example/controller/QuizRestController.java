package com.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BoardVO;
import com.example.domain.BunchVO;
import com.example.domain.Criteria;
import com.example.domain.MemberVO;
import com.example.domain.QuizVO;
import com.example.service.MemberService;
import com.example.service.QuizService;


/*
  REST API 방식 컨트롤러의 HTTP method 매핑 규칙
  POST   - Create (SQL Insert문) 
  GET    - Read   (SQL Select문)
  PUT    - Update (SQL Update문)
  DELETE - Delete (SQL Delete문)
*/
@RestController  // 이 컨트롤러 클래스의 모든 메소드의 리턴값이 JSON 또는 XML 응답으로 동작함
@RequestMapping("/api/*")
public class QuizRestController {
	
	@Autowired
	private QuizService quizService;
	
	@GetMapping(value = "/quizs", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<QuizVO>> getAll() {
		
		List<QuizVO> quizList = quizService.getQuiz();
		
		return new ResponseEntity<List<QuizVO>>(quizList,HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/quizs", consumes = "application/json", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Map<String, Object>> create(@RequestBody BunchVO bunchVO){
		
		quizService.addBunchAndQuizList(bunchVO);
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("bunch", bunchVO);
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	
	
}








