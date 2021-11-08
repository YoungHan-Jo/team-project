package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.NoticeVO;
import com.example.service.NoticeService;
import com.mysql.cj.protocol.x.Notice;

@Controller
@RequestMapping("/api/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping(value = "/notices", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void getAll() {
		
		Notice noticeVO = noticeService.getNotice(0);
		
	}
	
	@PostMapping(value = "/notices", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void write(NoticeVO noticeVO) {
		noticeService.insertNotice(noticeVO);
	}
	
	@DeleteMapping(value = "/notices/{num}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void remove(@PathVariable("num") int num) {
		noticeService.deleteNotice(num);
	}
	
	
	
}
