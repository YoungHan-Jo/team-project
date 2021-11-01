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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.AttachVO;
import com.example.domain.BoardVO;
import com.example.service.AttachService;
import com.example.service.BoardService;

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

		String uploadFolder = "C:/ksw/upload"; // 업로드 기준경로

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
			// ==============================================

			// 현재 업로드한 파일이 이미지 파일이면 썸네일 이미지를 추가로 생성하기
			boolean isImage = checkImageType(file); // 이미지 파일여부 true/false 로 리턴

			if (isImage == true) {
				File outFile = new File(uploadPath, "s_" + uploadFilename);

				Thumbnailator.createThumbnail(file, outFile, 100, 100); // 썸네일 이미지파일 생성하기
			}
			// ==============================================
			// insert할 주글 AttachVO 객체 준비 및 데이터 저장
			AttachVO attachVO = new AttachVO();
			attachVO.setUuid(uuid.toString());
			attachVO.setUploadpath(getFolder());
			attachVO.setFilename(originalFilename);
			attachVO.setFiletype((isImage == true) ? "I" : "O");
			attachVO.setBno(bno);

			attachList.add(attachVO); // 리스트에 추가
		} // for

		return attachList;
	} // uploadFilesAndGetAttachList

	
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
	
	
	// 첨부파일 업로드와 함께 주글쓰기 처리
	@PostMapping(value = "/boards", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public BoardVO write(List<MultipartFile> files, BoardVO boardVO,
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
		
		BoardVO dbBoardVO = boardService.getBoardAndAttaches(num);
		
		return dbBoardVO; // 클라이언트에 보내줄 응답 문자열을 리턴
	} // write
	
	
	
	
}





















