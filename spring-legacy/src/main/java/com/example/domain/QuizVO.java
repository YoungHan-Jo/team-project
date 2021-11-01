package com.example.domain;

import lombok.Data;

@Data
public class QuizVO {

	private int bunchNum; // PK 복합키
	private int questionNum; // PK 복합키
	private String question; // 질문
	private String numOne;
	private String numTwo;
	private String numThree;
	private String numFour;
	private String answer;	//정답 '1 ~ 4'
	
}
