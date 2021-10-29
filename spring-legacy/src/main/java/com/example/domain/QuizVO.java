package com.example.domain;

import lombok.Data;

@Data
public class QuizVO {

	private int num;	//퀴즈 구분하기위한 기본키
	private String question; // 질문
	private String numOne;
	private String numTwo;
	private String numThree;
	private String numFour;
	private String answer;	//정답 '1 ~ 4'
	private int packageNum; // 어느꾸러미에 포함된 퀴즈인지[외래키]
	
	
}
