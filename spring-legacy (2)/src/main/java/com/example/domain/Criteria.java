package com.example.domain;

import lombok.Data;

@Data
public class Criteria {

	private int pageNum;  // 페이지번호
	private int amount;   // 한 페이지당 글개수
	
	private int startRow; // 가져올 글의 시작 행번호
	
	private String type = "";     // 검색유형
	private String keyword = "";  // 검색어
	
	public Criteria() {
		this(1, 10);
	}

	public Criteria(Integer pageNum, Integer amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	} // 생성자
	
}





