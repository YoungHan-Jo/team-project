package com.example.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO { // 게시판 게시글

	private int num;
	private String boardType; // 게시글 유형
	private String memberId;
	private String subject;
	private String content;
	private int viewCount; // 조회수
	private Date regDate;
	private int reRef;
	private int reLev;
	private int reSeq;

	private List<AttachVO> attachList;

}
