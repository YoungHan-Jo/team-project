package com.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.BoardVO;
import com.example.domain.Criteria;
import com.example.domain.PageDTO;
import com.example.service.AttachService;
import com.example.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private AttachService attachService;

	// 글목록
	@GetMapping("/list")
	public String list(Criteria cri, Model model) {
		System.out.println("list() 호출됨...");

		List<BoardVO> boardList = boardService.getBoards(cri);
		int totalCount = boardService.getCountBySearch(cri);
		PageDTO pageDTO = new PageDTO(cri, totalCount);

		model.addAttribute("boardList", boardList);
		model.addAttribute("pageMaker", pageDTO);

		return "board/boardList";
	} // list

	// 게시글 글 보기
	@GetMapping("/view")
	public String content(int num, String pageNum, Model model) {
		System.out.println("content 호출됨...");
		// 조회수 1증가
		boardService.updateReadcount(num);

		
		// join 쿼리문으로 게시판글과 첨부파일 리스트 정보 가져오기
		BoardVO boardVO = boardService.getBoardAndAttaches(num);
		System.out.println(boardVO);

		model.addAttribute("board", boardVO);
		model.addAttribute("attachList", boardVO.getAttachList());
		model.addAttribute("attachCount", boardVO.getAttachList().size());
		model.addAttribute("pageNum", pageNum);

		return "board/boardView";
	} // view (/content)

	// 글쓰기
	@GetMapping("/write")
	public String write(@ModelAttribute("pageNum") String pageNum) {
		System.out.println("write 호출됨...");
		return "board/boardWrite";
	}

	@PostMapping("write")
	public String write(BoardVO boardVO, RedirectAttributes rttr){
		
		int num = boardService.getNextnum();
		
		boardVO.setNum(num);
		boardVO.setViewCount(0);
		boardVO.setRegDate(new Date());
		boardVO.setReRef(num);
		boardVO.setReLev(0);
		boardVO.setReSeq(0);

		boardService.addBoard(boardVO);
		rttr.addAttribute("num", boardVO.getNum());
		rttr.addAttribute("pageNum", 1);

		return "redirect:/board/view";
	}
	
	
	@GetMapping("/modify")
	public String modify(Model model, int num, String pageNum) {
		BoardVO boardVO = boardService.getBoardAndAttaches(num); // JOIN 으로 모두 가져옴

		model.addAttribute("board", boardVO);
		model.addAttribute("attachList", boardVO.getAttachList());
		model.addAttribute("pageNum", pageNum);

		return "board/boardModify";
	}
	
	@GetMapping("/remove")
	public String remove(int num, String pageNum) {
		boardService.deleteBoard(num);

		return "redirect:/board/list";
	} // remove
	
}
