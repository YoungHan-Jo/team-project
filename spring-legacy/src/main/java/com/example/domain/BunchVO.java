package com.example.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BunchVO { // 퀴즈꾸러미

	private int num;// 단순 꾸러미 구분하기 위한 번호 [기본키]
	private String title; // 꾸러미 제목
	private int quizCount;// 퀴즈개수
	private String memberId; // 누가 만든건지
	private Date regDate;
	
	private List<QuizVO> quizList;
	
	
}
