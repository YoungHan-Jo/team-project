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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.AttachVO;
import com.example.domain.BoardVO;
import com.example.domain.CommentVO;
import com.example.domain.Criteria;
import com.example.domain.PageDTO;
import com.example.service.AttachService;
import com.example.service.BoardService;
import com.example.service.CommentService;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private AttachService attachService;
	@Autowired
	private CommentService commentService;

	@GetMapping("/list")
	public String list(Criteria cri, Model model) {
		System.out.println("list() 호출됨...");

		List<BoardVO> boardList = boardService.getBoards(cri);
		int totalCount = boardService.getCountBySearch(cri);
		PageDTO pageDTO = new PageDTO(cri, totalCount);

		model.addAttribute("boardList", boardList);
		model.addAttribute("pageMaker", pageDTO);

		System.out.println("boardList:" + boardList);

		return "board/boardList";
	} // list

	@GetMapping("/view")
	public String content(int num, String pageNum, Model model) {
		System.out.println("view() 호출됨...");
		System.out.println("pageNum: " + pageNum);

		boardService.updateReadcount(num);

		BoardVO boardVO = boardService.getBoardAndAttaches(num);
		System.out.println("num: " + num);
		System.out.println("boardVO: " + boardVO);

		List<CommentVO> commentList = commentService.getCommentsByBoardNum(boardVO.getNum());
		System.out.println("commentList " + commentList);

		model.addAttribute("board", boardVO);
		model.addAttribute("attachList", boardVO.getAttachList());
		model.addAttribute("attachCount", boardVO.getAttachList().size());
		model.addAttribute("commentList", commentList);
		model.addAttribute("pageNum", pageNum);

		return "board/boardView";
	} // view

	@GetMapping("/write")
	public String write(String pageNum, Model model, HttpSession session) {
		System.out.println("write() 호출됨...");
		model.addAttribute("pageNum", pageNum);
		return "board/boardWrite";
	} // write

	@PostMapping("/write")
	public String write(List<MultipartFile> files, BoardVO boardVO, HttpServletRequest request, RedirectAttributes rttr)
			throws IllegalStateException, IOException {
		System.out.println("write() 호출됨...");

		int num = boardService.getNextnum();

		List<AttachVO> attachList = uploadFilesAndGetAttachList(files, num);

		boardVO.setNum(num);
		boardVO.setViewCount(0);
		boardVO.setRegDate(new Date());
		boardVO.setReRef(num);
		boardVO.setReLev(0);
		boardVO.setReSeq(0);

		boardVO.setAttachList(attachList);
		
		System.out.println("attachList");

		boardService.addBoardAndAttaches(boardVO);

		rttr.addAttribute("num", boardVO.getNum());
		rttr.addAttribute("pageNum", 1);
		
		return "redirect:/board/view";
	} // write

	@GetMapping("/modify")
	public String modifyForm(int num, String pageNum, Model model) {
		System.out.println("modify() 호출됨...");

		BoardVO boardVO = boardService.getBoardAndAttaches(num);

		System.out.println("boardVO:" + boardVO);

		model.addAttribute("board", boardVO);
		model.addAttribute("attachList", boardVO.getAttachList());
		model.addAttribute("pageNum", pageNum);

		return "board/boardModify";
	} // modify

	@PostMapping("/modify")
	public String modify(List<MultipartFile> files, BoardVO boardVO,
			@RequestParam(required = false, defaultValue = "1") int pageNum,
			@RequestParam(name = "delfile", required = false) List<String> delUuidList, HttpServletRequest request,
			RedirectAttributes rttr) throws IllegalStateException, IOException {
		System.out.println("modify() 호출됨...");

		List<AttachVO> newAttachList = uploadFilesAndGetAttachList(files, boardVO.getNum());
		System.out.println("modify 신규 첨부파일 업로드...");

		List<AttachVO> delAttachList = null;
		if (delUuidList != null && delUuidList.size() > 0) {
			delAttachList = attachService.getAttachesByUuids(delUuidList);

			deleteAttachFiles(delAttachList);
		}
		System.out.println("modify 기존 첨부파일 삭제...");

		boardService.updateBoardAndInsertAttachesAndDeleteAttaches(boardVO, newAttachList, delUuidList);
		System.out.println("boardVO: " + boardVO);
		System.out.println("modify 완료...");

		rttr.addAttribute("num", boardVO.getNum());
		rttr.addAttribute("pageNum", pageNum);

		return "redirect:/board/view";
	} // modify

	@GetMapping("/reply")
	public String replyForm(String reRef, String reLev, String reSeq, String pageNum, Model model) {

		model.addAttribute("reRef", reRef);
		model.addAttribute("reLev", reLev);
		model.addAttribute("reSeq", reSeq);
		model.addAttribute("pageNum", pageNum);

		return "board/replyWrite";
	} // replyForm

	// 답글쓰기
	@PostMapping("/reply")
	public String reply(List<MultipartFile> files, BoardVO boardVO, String pageNum, HttpServletRequest request,
			RedirectAttributes rttr) throws IllegalStateException, IOException {

		int num = boardService.getNextnum();

		List<AttachVO> attachList = uploadFilesAndGetAttachList(files, num);

		boardVO.setNum(num);
		boardVO.setViewCount(0);
		boardVO.setRegDate(new Date());
		boardVO.setAttachList(attachList);

		boardService.addReplyAndAttaches(boardVO);

		rttr.addAttribute("num", boardVO.getNum());
		rttr.addAttribute("pageNum", pageNum);

		return "redirect:/board/view";
	} // reply

	@GetMapping("/remove")
	public String remove(int num, String pageNum) {
		System.out.println("remove() 호출됨...");
		System.out.println("num: " + num);

		List<AttachVO> attachList = attachService.getAttachesByBoardNum(num);

		deleteAttachFiles(attachList);

		System.out.println("첨부파일 삭제 완료...");

		boardService.deleteBoardAndAttachesAndComments(num);

		return "redirect:/board/list?pageNum=" + pageNum;
	} // remove

	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String str = sdf.format(new Date());
		return str;
	} // getFolder

	private boolean checkImageType(File file) throws IOException {
		boolean isImage = false;

		String contentType = Files.probeContentType(file.toPath());
		System.out.println("contentType : " + contentType);

		isImage = contentType.startsWith("image");
		return isImage;
	} // checkImageType

	private List<AttachVO> uploadFilesAndGetAttachList(List<MultipartFile> files, int boardNum)
			throws IllegalStateException, IOException {

		List<AttachVO> attachList = new ArrayList<AttachVO>();

		if (files == null || files.size() == 0) {
			System.out.println("첨부파일 없음....");
			return attachList;
		}

		System.out.println("첨부파일 갯수: " + files.size());

		String uploadFolder = "C:/team/upload";

		File uploadPath = new File(uploadFolder, getFolder());
		System.out.println("uploadPath : " + uploadPath.getPath());

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		for (MultipartFile multipartFile : files) {
			if (multipartFile.isEmpty() == true) {
				continue;
			}

			String originalFilename = multipartFile.getOriginalFilename();
			System.out.println("originalFilename : " + originalFilename);

			UUID uuid = UUID.randomUUID();
			String uploadFilename = uuid.toString() + "_" + originalFilename;

			File file = new File(uploadPath, uploadFilename);

			multipartFile.transferTo(file);

			boolean isImage = checkImageType(file);

			if (isImage == true) {
				File outFile = new File(uploadPath, "s_" + uploadFilename);

				Thumbnailator.createThumbnail(file, outFile, 100, 100);
			}

			AttachVO attachVO = new AttachVO();
			attachVO.setUuid(uuid.toString());
			attachVO.setUploadpath(getFolder());
			attachVO.setFilename(originalFilename);
			attachVO.setFiletype((isImage == true) ? "I" : "O");
			attachVO.setBoardNum(boardNum);

			attachList.add(attachVO);
		}

		return attachList;
	} // uploadFilesAndGetAttachList

	private void deleteAttachFiles(List<AttachVO> attachList) {

		if (attachList == null || attachList.size() == 0) {
			System.out.println("삭제할 첨부파일 정보가 없습니다...");
			return;
		}

		String basePath = "C:/team/upload";

		for (AttachVO attachVO : attachList) {
			String uploadpath = basePath + "/" + attachVO.getUploadpath();
			String filename = attachVO.getUuid() + "_" + attachVO.getFilename();

			File file = new File(uploadpath, filename);

			if (file.exists() == true) {
				file.delete();
			}

			if (attachVO.getFiletype().equals("I")) {
				File thumbnailFile = new File(uploadpath, "s_" + filename);
				if (thumbnailFile.exists() == true) {
					thumbnailFile.delete();
				}
			}
		}
	} // deleteAttachFiles

}
