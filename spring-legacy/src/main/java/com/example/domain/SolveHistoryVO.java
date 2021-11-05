package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class SolveHistoryVO { // 내가 푼 문제 내역

	private int num;
	private String memberId; // 누가
	private int bunchNum; // 무슨 퀴즈꾸러미를
	private Date solveDate; // 언제 풀었는지
	private int point; // 점수
	private String answerList; // 제출한 답 리스트 json String
	private String correctList; // 맞힌 문제 번호 리스트 json String 
	private String incorrectList; // 틀린 문제 번호 리스트 json String
	
	private BunchVO bunchVO;
}
