package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVO { // 관리자 공지사항

	private int num;
	private String subject;
	private String content;
	private int viewCount;
	private Date regDate;
	
}
