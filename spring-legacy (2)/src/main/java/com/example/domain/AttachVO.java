package com.example.domain;

import lombok.Data;

@Data
public class AttachVO {

	private String uuid;
	private String uploadpath; // 업로드 경로
	private String filename;   // 파일명
	private String filetype;   // 파일타입. 이미지 "I", 이미지 아니면 "O"
	private int bno;           // 첨부파일이 포함되는 게시글 번호
}
