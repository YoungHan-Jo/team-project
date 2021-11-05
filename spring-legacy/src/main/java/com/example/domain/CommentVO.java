package com.example.domain;



import java.util.Date;

import lombok.Data;

@Data
public class CommentVO { // 댓글

	private int num;		// 단순히 구분하기위한 기본키 용
	private int boardNum;	// 어느 게시글에 포함되어있는지
	private String memberId; // 누가 쓴 댓글인지
	private String content; // 댓글 내용
	private Date regDate; // 댓글 쓴 날짜
	private int reRef;
	private int reLev;
	private int reSeq;
	
	
}
