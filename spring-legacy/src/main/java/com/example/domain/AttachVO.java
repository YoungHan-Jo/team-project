package com.example.domain;

import lombok.Data;

@Data
public class AttachVO { // 게시글 첨부파일

	private String uuid;
	private String uploadpath;
	private String filename;
	private String filetype;
	private int boardNum;
	
}
