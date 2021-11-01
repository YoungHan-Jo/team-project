package com.example.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {

	private Integer num;
	private String mid;
	private String subject;
	private String content;
	private Integer readcount;
	private Date regDate;
	private String ipaddr;
	private Integer reRef;
	private Integer reLev;
	private Integer reSeq;
	
	//private AttachVO attachVO;          // SQL JOIN 쿼리에서 1:1 관계
	private List<AttachVO> attachList;  // SQL JOIN 쿼리에서 1:N 관계
	
}










