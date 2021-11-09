package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.MemberService;

@Controller
@RequestMapping("/admin/*")
public class AdminMemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("logmsmain")
	public void logmsmain() {
		System.out.println("logmsmain ?호출됨...");
	}
	
	@GetMapping("logmsSelectMember")
	public void logmsSelectMember() {
		System.out.println("logmsSelectMember ?호출됨...");
	}
	
	@GetMapping("logmsUpdateMember")
	public void logmsInsertMember() {
		System.out.println("logmsInsertMember 호출됨");
	}
	
	@GetMapping("logmsDeleteMember")
	public void logmsDeleteMember() {
		System.out.println("logmsmain ?호출됨...");
	}
	
	
	
	
	@GetMapping("logmsSelectBoards")
	public void logmsSelectBoards() {
		System.out.println("logmsSelectBoards ? 호출됨");
	}
	
	
	@GetMapping("logmsDeleteBoard")
	public void logmsDeleteBoard() {
		System.out.println("logmsDeleteBoard ? 호출됨");
	}
	
	
	
	@GetMapping("logmsSelectQuiz")
	public void logmsSelectQuiz() {
		System.out.println("logmsSelectQuiz ? 호출됨");
	}
	
	@GetMapping("logmsInsertNotice")
	public void logmsInsertNotice() {
		System.out.println("logmsInsertNotice ? 호출됨");
	}
	
	@GetMapping("logmsSelectNotice")
	public void logmsSelectNotice() {
		System.out.println("logmsSelectNotice ? 호출됨");
	}
	
	
}
