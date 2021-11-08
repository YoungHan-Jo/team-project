package com.example.domain;

import lombok.Data;

@Data
public class OpenApiDTO {
	
	private String implYyDTO; 			// 시행년도
	private String implSeqDTO;			// 시행회차
	private String docRegStartDt;		// 필기시험 원서접수 시작일자
	private String docRegEndDt;		// 필기시험 원서접수 종료일자
	private String docExamStartDt;	// 필기시험 시작일자
	private String docExamEndDt;		// 필기시험 종료일자
	private String docPassDt;			// 필기시험 합격(예정)자 발표일자
	private String pracRegStartDt;	// 실기(작업)/면접시험 원서접수 시작일자
	private String pracRegEndDt;		// 실기(작업)/면접시험 원서접수 종료일자
	private String pracExamStartDt;	// 실기(작업)/면접시험 시작일자
	private String pracExamEndDt;		// 실기(작업)/면접시험 종료일자
	private String pracPassDt;		// 실기(작업)/면접시험 발표일자

}
