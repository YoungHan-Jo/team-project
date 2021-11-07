package com.example.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.BoardVO;
import com.example.domain.CommentVO;
import com.example.service.CommentService;

@Controller
@RequestMapping("/comment/*")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/commentWrite")
	public String write(CommentVO commentVO, RedirectAttributes rttr, String pageNum) {
		System.out.println("c_write 호출됨...");
		
		int num = commentService.getNextnum();
		
		commentVO.setNum(num);
		commentVO.setRegDate(new Date());
		commentVO.setReRef(num);
		commentVO.setReLev(0);
		commentVO.setReSeq(0);
		
		commentService.addComment(commentVO);
		
		rttr.addAttribute("num", commentVO.getNum());
		rttr.addAttribute("pageNum", pageNum);
		
		System.out.println("commentVO: " + commentVO);
		return "redirect:/board/view?num=" + commentVO.getBoardNum();
	}
	
	@GetMapping("/commentModify")
	public String modify(int num, CommentVO commentVO, String pageNum, RedirectAttributes rttr) {
		System.out.println("c_modify() 호출됨...");
		
		commentService.updateComment(commentVO);
		
		rttr.addAttribute("num", commentVO.getNum());
		rttr.addAttribute("pageNum", pageNum);
		
		return "redirect:/board/view?num=" + commentVO.getBoardNum();
	}

	@GetMapping("/commentRemove")
	public String remove(int num, RedirectAttributes rttr) {
		System.out.println("c_remove 호출됨...");
		
		System.out.println("num: " + num);
		
		CommentVO commentVO = commentService.getBoard(num);
		int bno = commentVO.getBoardNum();
		
		System.out.println("bno: " + bno);
		System.out.println("commentVO: " + commentVO);
		
		commentService.deleteComment(num);
		
		rttr.addAttribute("bno", bno);
		rttr.addAttribute("pageNum", 1);
		
		return "redirect:/board/view?num=" + bno;
	} // remove

}
