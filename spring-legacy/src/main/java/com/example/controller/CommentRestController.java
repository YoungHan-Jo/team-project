package com.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.CommentVO;
import com.example.service.CommentService;

@RestController
@RequestMapping("/comment/*")
public class CommentRestController {

	@Autowired
	private CommentService commentService;

	// 댓글 전체 조회
	@GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<CommentVO>> getAll() {

		List<CommentVO> commentList = commentService.getCommentsAll();

		return new ResponseEntity<List<CommentVO>>(commentList, HttpStatus.OK);
	} // getAll
	
	// 해당 게시글에 있는 댓글 전체 
	@GetMapping(value = "/list/{boardNum}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Map<String, Object>> getCommentByBoardNum(@PathVariable("boardNum") int boardNum) {

		List<CommentVO> commentList = commentService.getCommentsByBoardNum(boardNum);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comment", commentList);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	} // getCommentByBoardNum

	// 댓글 조회
	@GetMapping(value = "/{num}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CommentVO> getComment(@PathVariable("num") int num) {

		CommentVO commentVO = commentService.getComment(num);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comment", commentVO);

		return new ResponseEntity<CommentVO>(commentVO, HttpStatus.OK);
	} // getComment
	
	// 댓글 쓰기
	@PostMapping(value = "/write", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Map<String, Object>> create(@RequestBody CommentVO commentVO) {

		int num = commentService.getNextnum();
		
		commentVO.setNum(num);
		commentVO.setRegDate(new Date());
		commentVO.setReRef(num);
		commentVO.setReLev(0);
		commentVO.setReSeq(0);
		
		commentService.addComment(commentVO);

		System.out.println(commentVO); 

		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("comment", commentVO);
	
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	} // write
	
	// 댓글 수정
	@PutMapping(value = "/{num}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@PathVariable("num") int num, @RequestBody CommentVO commentVO, @PathVariable("pageNum") int pageNum) {

		commentVO.setRegDate(new Date());
		
		commentService.updateComment(commentVO);
		
		System.out.println("commentVO: " + commentVO);
	
		return new ResponseEntity<String>("success", HttpStatus.OK);
	} // put
	
	// 댓글 삭제
	@DeleteMapping(value = "/{num}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> remove(@PathVariable("num") int num) {
		
		CommentVO commentVO = commentService.getComment(num);
		int bno = commentVO.getBoardNum();
		
		System.out.println("bno: " + bno);
		System.out.println("commentVO: " + commentVO);
		
		int count = commentService.deleteComment(num);
		
		return (count > 0) 
				? new ResponseEntity<String>("success", HttpStatus.OK) 
						: new ResponseEntity<String>("fail", HttpStatus.BAD_GATEWAY);
	} // remove
	
	// 대댓글 쓰기
	@PostMapping(value = "/reply", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Map<String, Object>> reply(@RequestBody CommentVO commentVO) {

		int num = commentService.getNextnum();
		
		commentVO.setNum(num);
		commentVO.setRegDate(new Date());
		
		commentService.addReply(commentVO);

		System.out.println(commentVO); 

		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("comment", commentVO);
	
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	} // reply
	

}
