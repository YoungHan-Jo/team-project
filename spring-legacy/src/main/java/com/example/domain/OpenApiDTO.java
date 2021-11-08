package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OpenApiDTO { // api 는 제공해주는것과 이름이 같아야한다. 대소문자 구분도 철저!!
	
	private String implYyDTO; 			// 시행년도
	private String implSeq;			// 시행회차
	private String qualgbCdDTO;		// 자격구분코드
	private String qualgbNm;		// 자격구분명
	private String docRegStartDt;		// 필기시험 원서접수 시작일자
	private String docRegEndDt;		// 필기시험 원서접수 종료일자
	private Date docExamStartDt;	// 필기시험 시작일자
	private Date docExamEndDt;		// 필기시험 종료일자
	private Date docPassDt;			// 필기시험 합격(예정)자 발표일자
	private Date pracRegStartDt;	// 실기(작업)/면접시험 원서접수 시작일자
	private Date pracRegEndDt;		// 실기(작업)/면접시험 원서접수 종료일자
	private Date pracExamStartDt;	// 실기(작업)/면접시험 시작일자
	private Date pracExamEndDt;		// 실기(작업)/면접시험 종료일자
	private Date pracPassDt;		// 실기(작업)/면접시험 발표일자

}
