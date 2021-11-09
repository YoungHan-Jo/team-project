package com.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.BoardVO;
import com.example.domain.Criteria;
import com.example.domain.NoticeVO;
import com.example.service.BoardService;
import com.example.service.NoticeService;
import com.mysql.cj.protocol.x.Notice;

@Controller
@RequestMapping("/api/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = "/notices", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<NoticeVO>> getAll() {
		
		List<NoticeVO> noticeList = noticeService.getNotices();
		
		return new ResponseEntity<List<NoticeVO>>(noticeList, HttpStatus.OK);
	}
	
	@PostMapping(value = "/notices", 
			consumes = "application/json",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Map<String, Object>> write(@RequestBody NoticeVO noticeVO) {
		
		noticeVO.setRegDate(new Date());
		
		noticeService.insertNotice(noticeVO);
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("notice", noticeVO);
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/notices/{num}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void remove(@PathVariable("num") int num) {
		noticeService.deleteNotice(num);
	}
	

	
}
