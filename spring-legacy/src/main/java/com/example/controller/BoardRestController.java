package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.AttachVO;
import com.example.domain.BoardVO;
import com.example.domain.Criteria;
import com.example.service.AttachService;
import com.example.service.BoardService;
import com.example.service.MemberService;

import net.coobird.thumbnailator.Thumbnailator;

@RestController
@RequestMapping("/api/*")
public class BoardRestController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private AttachService attachService;

	// "년/월/일" 형식의 폴더명을 리턴하는 메소드
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String str = sdf.format(new Date());
		return str;
	}

	
	@GetMapping(value = "/boards/{num}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<BoardVO> getOne(@PathVariable("num") int num){
		// code
		
		// 특정 글 번호에 해당하는 게시글 한 개와 게시글에 포함된 첨부파일 정보 가져오기
		BoardVO dbBoardVO = boardService.getBoardAndAttaches(num);

		return new ResponseEntity<BoardVO>(dbBoardVO, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/boards/pages/{pageNum}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<BoardVO>> getListWithPage(@PathVariable("pageNum") int pageNum) {
		// code
		
		Criteria cri = new Criteria();
		cri.setPageNum(pageNum);

		// 특정 페이지 번호에 해당하는 게시글 목록을 가져오기
		List<BoardVO> boardList = boardService.getBoards(cri);

		return new ResponseEntity<List<BoardVO>>(boardList, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/boards/{num}",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<BoardVO> remove(@PathVariable("num") int num){
		
		
		BoardVO dbboardVO = boardService.deleteBoard(num);
		
		
		return new ResponseEntity<BoardVO>(dbboardVO,HttpStatus.OK);
	}
	
}





















