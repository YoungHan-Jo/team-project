package com.example.domain;

import lombok.Getter;
import lombok.ToString;

// DTO(Data Transfer Object)
@Getter
@ToString
public class PageDTO {

	private int startPage; // 시작페이지
	private int endPage;   // 끝페이지
	private boolean prev;  // 이전
	private boolean next;  // 다음
	private final int PAGE_BLOCK = 5; // 페이지블록 구성하는 최대 페이지갯수
	
	private int totalCount; // 전체 글갯수
	private Criteria cri;   // 요청 페이지번호, 한페이지당 글갯수
	
	public PageDTO(Criteria cri, int totalCount) {
		this.cri = cri;
		this.totalCount = totalCount;
		
		//  1페이지 요청 -> 끝페이지는 5
		//  2페이지 요청 -> 끝페이지는 5
		//  3페이지 요청 -> 끝페이지는 5
		//  4페이지 요청 -> 끝페이지는 5
		//  5페이지 요청 -> 끝페이지는 5
		
		//  6페이지 요청 -> 끝페이지는 10
		//  7페이지 요청 -> 끝페이지는 10
		//  8페이지 요청 -> 끝페이지는 10
		//  9페이지 요청 -> 끝페이지는 10
		// 10페이지 요청 -> 끝페이지는 10
		
		// 11페이지 요청 -> 끝페이지는 15
		// ...
		
		// 끝페이지 번호
		endPage = (int) Math.ceil((double) cri.getPageNum() / PAGE_BLOCK) * PAGE_BLOCK;
		// 시작페이지 번호
		startPage = endPage - (PAGE_BLOCK - 1);
		
		// 실제 끝페이지
		int realEnd = (int) Math.ceil((double) totalCount / cri.getAmount());
		
		// 실제 끝페이지 번호가 endPage보다 작으면
		if (realEnd < endPage) {
			endPage = realEnd; // endPage를 실제 끝페이지 번호로 수정
		}
		
		// 이전
		prev = startPage > 1;
		// 다음
		next = endPage < realEnd;
		
	} // 생성자
	
}








