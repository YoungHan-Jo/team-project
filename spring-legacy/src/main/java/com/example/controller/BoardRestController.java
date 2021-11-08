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

	// "�뀈/�썡/�씪" �삎�떇�쓽 �뤃�뜑紐낆쓣 由ы꽩�븯�뒗 硫붿냼�뱶
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String str = sdf.format(new Date());
		return str;
	}

	
	@GetMapping(value = "/boards/{num}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<BoardVO> getOne(@PathVariable("num") int num){
		// code
		
		// �듅�젙 湲� 踰덊샇�뿉 �빐�떦�븯�뒗 寃뚯떆湲� �븳 媛쒖� 寃뚯떆湲��뿉 �룷�븿�맂 泥⑤��뙆�씪 �젙蹂� 媛��졇�삤湲�
		BoardVO dbBoardVO = boardService.getBoardAndAttaches(num);

		return new ResponseEntity<BoardVO>(dbBoardVO, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/boards/pages/{pageNum}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<BoardVO>> getListWithPage(@PathVariable("pageNum") int pageNum) {
		// code
		
		Criteria cri = new Criteria();
		cri.setPageNum(pageNum);

		// �듅�젙 �럹�씠吏� 踰덊샇�뿉 �빐�떦�븯�뒗 寃뚯떆湲� 紐⑸줉�쓣 媛��졇�삤湲�
		List<BoardVO> boardList = boardService.getBoards(cri);

		return new ResponseEntity<List<BoardVO>>(boardList, HttpStatus.OK);
	}

	
	@PostMapping(value = "/boards", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public void write(List<MultipartFile> files, BoardVO boardVO, 
			HttpServletRequest request, RedirectAttributes rttr) {
		
		boardService.addBoard(boardVO);
		
	}
	
	@DeleteMapping(value = "/boards/{num}",produces = MediaType.TEXT_PLAIN_VALUE)
	public void remove(@PathVariable("num") int num){
		
	
		boardService.deleteBoard(num);

	}
	
	
	
	
	
}





















