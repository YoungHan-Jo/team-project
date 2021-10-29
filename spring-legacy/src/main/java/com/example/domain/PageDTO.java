package com.example.domain;

import lombok.Data;

@Data
public class PageDTO {

	private int startPage; // 페이징 블럭 안에서의 시작페이지
	private int endPage; // 페이징 블럭 안에서의 끝페이지
	private boolean prev; // 이전 페이지 유무
	private boolean next; // 다음 페이지 유무

	private final int PAGE_BLOCK = 5; // 페이지블록 구성하는 최대 페이지 개수

	private int totalCount; // 전체 글 개수
	private Criteria cri; // 요청 페이지번호, 한 페이지당 글 개수

	public PageDTO(Criteria cri, int totalCount) {
		this.cri = cri;
		this.totalCount = totalCount;

		// 1~5 페이지 중의 어느 번호를 요청해도 항상 끝페이지는 5
		// 6~10 페이지 요청시 끝페이지는 10으로 바뀜
		// getPageNum 이 3 이라고 할때. 5로 실수나눗셈 0.6 올림 -> 1 곱하기 5 -> endPage 5
		endPage = (int) Math.ceil((double) cri.getPageNum() / PAGE_BLOCK) * PAGE_BLOCK;

		startPage = endPage - (PAGE_BLOCK - 1);

		// 실제 끝페이지가 PAGE_BLOCK 만큼 딱 안떨어질 때
		int realEnd = (int) Math.ceil((double) totalCount / cri.getAmount());

		if (realEnd < endPage) {
			endPage = realEnd;
		}

		// 이전
		prev = startPage > 1;
		// 다음
		next = endPage < realEnd;

	} // 생성자

}
