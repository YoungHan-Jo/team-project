package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {

	private String id;
	private String passwd;
	private String name;
	private String birthday;
	private String gender;
	private String email;
	private String recvEmail;
	private Date regDate;
	
	private ProfilepicVO profilepicVO; // 1:1 관계로 조인해서 저장
}




