package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class SolveHistoryVO { // 내가 푼 문제 내역

	private String memberId; // 누가
	private int bunchNum; // 무슨 퀴즈꾸러미를
	private Date solveDate; // 언제 풀었는지
	
	private String jsonCorrectList;
	private String jsonIncorrectList;
	
}
