package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.MemberService;

@Controller
@RequestMapping("/ajax/*")
public class AjaxMemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("logmsmain")
	public void logmsmain() {
		System.out.println("logmsmain 호출됨...");
	}
	
	
	@GetMapping("logmsDeleteMember")
	public void logmsDeleteMember() {
		System.out.println("logmsmain 호출됨...");
	}
	
	
	@GetMapping("logmsSelectMember")
	public void logmsSelectMember() {
		System.out.println("logmsSelectMember 호출됨...");
	}
	
	
	
}
