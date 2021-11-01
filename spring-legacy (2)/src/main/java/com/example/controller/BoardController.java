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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.AttachVO;
import com.example.domain.BoardVO;
import com.example.domain.Criteria;
import com.example.domain.PageDTO;
import com.example.service.AttachService;
import com.example.service.BoardService;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private AttachService attachService;
	
	
	@GetMapping("/list")
	public String list(Criteria cri, Model model) {
		System.out.println("list() 호출됨...");
		
		// board 테이블에서 검색, 페이징 적용한 글 리스트 가져오기 
		List<BoardVO> boardList = boardService.getBoards(cri);
		
		// 검색유형, 검색어가 있으면 적용하여 글개수 가져오기
		int totalCount = boardService.getCountBySearch(cri);
		
		// 페이지블록 정보 객체준비. 필요한 정보를 생성자로 전달.
		PageDTO pageDTO = new PageDTO(cri, totalCount);
		
		// 뷰에서 사용할 데이터를 Model 객체에 저장 → 스프링이 requestScope 로 옮겨줌.
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageMaker", pageDTO);
		
		return "board/boardList";
	} // list
	
	
	@GetMapping("/content")
	public String content(int num, @ModelAttribute("pageNum") String pageNum, Model model) {
		
		// 조회수 1 증가시키기
		boardService.updateReadcount(num);
		
		// 상세보기할 글 한개 가져오기
		//BoardVO boardVO = boardService.getBoard(num);
		// 첨부파일 정보 리스트 가져오기
		//List<AttachVO> attachList = attachService.getAttachesByBno(num);
		
		// join 쿼리문으로 게시판 글과 첨부파일 리스트 정보 가져오기
		BoardVO boardVO = boardService.getBoardAndAttaches(num);
		System.out.println(boardVO);
		
		
		model.addAttribute("board", boardVO);
		model.addAttribute("attachList", boardVO.getAttachList());
		model.addAttribute("attachCount", boardVO.getAttachList().size());
		//model.addAttribute("pageNum", pageNum);
		
		return "board/boardContent";
	} // content
	
	
	// 새로운 주글쓰기 폼 화면 요청
	@GetMapping("/write")
	public String write(@ModelAttribute("pageNum") String pageNum, 
			HttpSession session) {
		// 세션에서 사용자 로그인 권한 확인
//		if (session.getAttribute("id") == null) { // 로그인 안한 사용자일 경우
//			return "redirect:/member/login";
//		}
		
		return "board/boardWrite";
	} // write
	
	
	
	// "년/월/일" 형식의 폴더명을 리턴하는 메소드
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String str = sdf.format(new Date());
		return str;
	}
	
	
	// 이미지 파일여부 리턴하는 메소드
	private boolean checkImageType(File file) throws IOException {
		boolean isImage = false;
		
		String contentType = Files.probeContentType(file.toPath()); // "image/jpg" "image/gif" "image/png"
		System.out.println("contentType : " + contentType);
		
		isImage = contentType.startsWith("image");
		return isImage;
	} // checkImageType
	
	
	
	// 첨부파일 업로드(썸네일 이미지 생성) 후 attachList 리턴하는 메소드
	private List<AttachVO> uploadFilesAndGetAttachList(List<MultipartFile> files, int bno)
			throws IllegalStateException, IOException {
		
		List<AttachVO> attachList = new ArrayList<AttachVO>(); // 리턴할 리스트 컬렉션 객체 준비
		
		// 업로드 처리로 생성해야할 파일정보가 없으면 메소드 종료
		if (files == null || files.size() == 0) {
			System.out.println("첨부파일 없음....");
			return attachList;
		}
				
		
		
		System.out.println("첨부파일 갯수: " + files.size());
		
		String uploadFolder = "C:/ksw/upload";  // 업로드 기준경로
		
		File uploadPath = new File(uploadFolder, getFolder()); // "C:/ksw/upload/2021/10/19"
		System.out.println("uploadPath : " + uploadPath.getPath());
		
		if (uploadPath.exists() == false) { // !uploadPath.exists()
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
			
			File file = new File(uploadPath, uploadFilename); // 생성할 파일경로 파일명 정보
			
			// 파일1개 업로드(파일 생성) 완료
			multipartFile.transferTo(file);
			//==============================================
			
			// 현재 업로드한 파일이 이미지 파일이면 썸네일 이미지를 추가로 생성하기
			boolean isImage = checkImageType(file); // 이미지 파일여부 true/false 로 리턴
			
			if (isImage == true) {
				File outFile = new File(uploadPath, "s_" + uploadFilename);
				
				Thumbnailator.createThumbnail(file, outFile, 100, 100); // 썸네일 이미지파일 생성하기
			}
			//==============================================
			// insert할 주글 AttachVO 객체 준비 및 데이터 저장
			AttachVO attachVO = new AttachVO();
			attachVO.setUuid(uuid.toString());
			attachVO.setUploadpath(getFolder());
			attachVO.setFilename(originalFilename);
			attachVO.setFiletype( (isImage == true) ? "I" : "O" );
			attachVO.setBno(bno);
			
			attachList.add(attachVO); // 리스트에 추가
		} // for
		
		return attachList;
	} // uploadFilesAndGetAttachList
	
	
	
	// 첨부파일 업로드와 함께 주글쓰기 처리
	@PostMapping("/write")
	public String write(List<MultipartFile> files, BoardVO boardVO,
			HttpServletRequest request, RedirectAttributes rttr)
			throws IllegalStateException, IOException {
		// 스프링 웹에서는 클라이언트로부터 넘어오는 file 타입의 input 요소의 갯수만큼
		// MultipartFile 타입의 객체로 전달받게 됨. 
		
		// insert할 새 글번호 가져오기
		int num = boardService.getNextnum();
		
		// 첨부파일 업로드(썸네일 이미지 생성) 후 attachList 리턴
		List<AttachVO> attachList = uploadFilesAndGetAttachList(files, num);
		
		//======= insert 할 BoardVO 객체의 데이터 설정 ===========
		boardVO.setNum(num);
		boardVO.setReadcount(0);
		boardVO.setRegDate(new Date());
		boardVO.setIpaddr(request.getRemoteAddr());
		boardVO.setReRef(num);  // 주글일 경우 글그룹번호는 글번호와 동일함
		boardVO.setReLev(0);    // 주글일 경우 들여쓰기 레벨은 0
		boardVO.setReSeq(0);    // 주글일 경우 글그룹 안에서의 순번은 0
		
		boardVO.setAttachList(attachList); // 첨부파일 리스트 저장
		
		// 주글 한개(boardVO)와 첨부파일 여러개(attachList)를 한개의 트랜잭션으로 insert 처리함
		boardService.addBoardAndAttaches(boardVO);
		//==============================================================
		
		// 리다이렉트 시 쿼리스트링으로 서버에 전달할 데이터를
		// RedirectAttributes 타입의 객체에 저장하면,
		// 스프링이 자동으로 쿼리스트링으로 변환해서 처리해줌
		rttr.addAttribute("num", boardVO.getNum());
		rttr.addAttribute("pageNum", 1);
		
		return "redirect:/board/content";
	} // write
	
	
	// 첨부파일 삭제하는 메소드
	private void deleteAttachFiles(List<AttachVO> attachList) {
		// 삭제할 파일정보가 없으면 메소드 종료
		if (attachList == null || attachList.size() == 0) {
			System.out.println("삭제할 첨부파일 정보가 없습니다...");
			return;
		}
		
		String basePath = "C:/ksw/upload";
		
		for (AttachVO attachVO : attachList) {
			String uploadpath = basePath + "/" + attachVO.getUploadpath(); // "C:/ksw/upload/2021/10/20"
			String filename = attachVO.getUuid() + "_" + attachVO.getFilename(); // "uuid_커비.png"
			
			File file = new File(uploadpath, filename); // "C:/ksw/upload/2021/10/20/uuid_커비.png"
			
			if (file.exists() == true) { // 해당 경로에 첨부파일이 존재하면
				file.delete();  // 첨부파일 삭제하기
			}
			
			// 첨부파일이 이미지일 경우 썸네일 이미지 파일도 삭제하기
			if (attachVO.getFiletype().equals("I")) {  // "Image" 파일이면
				// 썸네일 이미지 파일 존재여부 확인 후 삭제하기
				File thumbnailFile = new File(uploadpath, "s_" + filename);
				if (thumbnailFile.exists() == true) {
					thumbnailFile.delete();
				}
			}
		} // for
	} // deleteAttachFiles
	
	
	@GetMapping("/remove")
	public String remove(int num, String pageNum) {
		
		// ============ 첨부파일 삭제 ============
		
		List<AttachVO> attachList = attachService.getAttachesByBno(num);
		
		deleteAttachFiles(attachList); // 첨부파일(썸네일도 삭제) 삭제하기
		
		System.out.println("============ 첨부파일 삭제 완료 ============");
		
		// attach와 board 테이블 내용 삭제 - 한개의 트랜잭션 단위로 처리
		boardService.deleteBoardAndAttaches(num);
		
		// 글목록으로 리다이렉트 이동
		return "redirect:/board/list?pageNum=" + pageNum;
	} // remove
	
	
	@GetMapping("/modify")
	public String modifyForm(int num, @ModelAttribute("pageNum") String pageNum, Model model) {
		
//		BoardVO boardVO = boardService.getBoard(num);
//		List<AttachVO> attachList = attachService.getAttachesByBno(num);
		
		BoardVO boardVO = boardService.getBoardAndAttaches(num); // JOIN 으로 모두 가져옴
		
		model.addAttribute("board", boardVO);
		model.addAttribute("attachList", boardVO.getAttachList());
		//model.addAttribute("pageNum", pageNum); // @ModelAttribute 애노테이션 처리함과 동일
		
		return "board/boardModify";
	} // modifyForm
	
	
	@PostMapping("/modify")
	public String modify(List<MultipartFile> files, BoardVO boardVO, 
			@RequestParam(required = false, defaultValue = "1") int pageNum,
			@RequestParam(name = "delfile", required = false) List<String> delUuidList,
			HttpServletRequest request, RedirectAttributes rttr)
					throws IllegalStateException, IOException {
		
		// 1) 신규 첨부파일 업로드하기. 신규파일정보 리스트 가져오기.
		List<AttachVO> newAttachList = uploadFilesAndGetAttachList(files, boardVO.getNum());
		System.out.println("========== POST modify - 신규 첨부파일 업로드 완료 ==========");
		
		
		// 2) 삭제할 첨부파일 삭제하기(썸네일 이미지도 삭제).
		List<AttachVO> delAttachList = null;
		if (delUuidList != null && delUuidList.size() > 0) {
			delAttachList = attachService.getAttachesByUuids(delUuidList);
			
			deleteAttachFiles(delAttachList); // 첨부파일(썸네일도 삭제) 삭제하기
		}
		System.out.println("========== POST modify - 기존 첨부파일 삭제 완료 ==========");
		
		
		// 3) 테이블 작업
		//    boardVO 게시글 수정
		//    attach 테이블에 신규 파일정보(newAttachList)를 insert, 삭제할 정보(delUuidList)를 delete
		
		// update 할 BoardVO 객체의 데이터 설정
		boardVO.setIpaddr(request.getRemoteAddr()); // 사용자 IP주소 저장
		
		// 글번호에 해당하는 게시글 수정, 첨부파일정보 수정(insert, delete) - 한개의 트랜잭션으로 처리
		boardService.updateBoardAndInsertAttachesAndDeleteAttaches(boardVO, newAttachList, delUuidList);
		System.out.println("========== POST modify - 테이블 내용 수정 완료 ==========");
		
		
		// 리다이렉트 쿼리스트링 정보 설정
		rttr.addAttribute("num", boardVO.getNum());
		rttr.addAttribute("pageNum", pageNum);
		
		// 상세보기 화면으로 리다이렉트 이동 
		return "redirect:/board/content";
	} // modify
	
	
	@GetMapping("/reply")
	public String replyForm(@ModelAttribute("reRef") String reRef, 
			@ModelAttribute("reLev") String reLev, 
			@ModelAttribute("reSeq") String reSeq, 
			@ModelAttribute("pageNum") String pageNum, 
			Model model) {
		/*
		model.addAttribute("reRef", reRef);
		model.addAttribute("reLev", reLev);
		model.addAttribute("reSeq", reSeq);
		model.addAttribute("pageNum", pageNum);
		*/
		return "board/replyWrite";
	} // replyForm
	
	
	// 답글쓰기
	@PostMapping("/reply")
	public String reply(List<MultipartFile> files, BoardVO boardVO, String pageNum,
			HttpServletRequest request, RedirectAttributes rttr) 
			throws IllegalStateException, IOException {
		
		// insert할 새 글번호 가져오기
		int num = boardService.getNextnum();
		
		// 첨부파일 업로드(썸네일 생성) 후 attachList 리턴
		List<AttachVO> attachList = uploadFilesAndGetAttachList(files, num);
		
		// ========= insert 할 답글 BoardVO 객체 데이터 설정 =========
		boardVO.setNum(num);
		boardVO.setReadcount(0);
		boardVO.setRegDate(new Date());
		boardVO.setIpaddr(request.getRemoteAddr());  // 사용자 IP 주소 저장
		boardVO.setAttachList(attachList); // 첨부파일 정보 리스트 저장
		
		// ※ 현재 boardVO에 들어있는 reRef, reLev, reSeq는 바로 insert될 답글 정보가 아님에 주의!
		//   답글을 남길 대상글에 대한 reRef, reLev, reSeq 정보임에 주의!
		
		/*
		주글, 답글 데이터

		num    subject              re_ref    re_lev    re_seq
		===========================================================
		 6     주글3                  6         0         0
		 4     주글2                  4         0         0
		 5       답글2                4         1         1
		 1     주글1                  1         0         0
		 7       답글2 (*)            1         1         1
		 2       답글1                1         1         1+1=2
		 3         답글1_1            1         2         2+1=3  
		*/
		
		//boardService.addReplyAndAttaches(boardVO, attachList);
		boardService.addReplyAndAttaches(boardVO);
		//=========================================================
		
		// 리다이렉트 시 서버에 전달할 데이터를 rttr에 저장하면
		// 스프링이 자동으로 쿼리스트링으로 변환하여 처리해줌
		rttr.addAttribute("num", boardVO.getNum());
		rttr.addAttribute("pageNum", pageNum);
		
		return "redirect:/board/content";
	} // reply
	
	
	
}








