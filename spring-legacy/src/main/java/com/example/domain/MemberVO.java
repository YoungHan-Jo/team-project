package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {

	private String id;
	private String passwd;
	private String name;
	private String birthday; // 8자리 'yyyyMMdd' 형식
	private String gender; // 남자 = 'M' , 여자 ='F'
	private String email;
	private String recvEmail; // 이메일 수신여부 / 예 ='Y' , 아니오 ='N'
	private Date regDate;
	
	private ProfileImg profileImg; // 1:1 관계로 조인해서 저장
}
