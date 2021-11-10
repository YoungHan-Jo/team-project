package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.BoardVO;
import com.example.domain.BunchVO;
import com.example.domain.CommentVO;
import com.example.domain.Criteria;
import com.example.domain.MemberVO;
import com.example.domain.PageDTO;
import com.example.domain.ProfileImg;
import com.example.domain.SolveHistoryVO;
import com.example.service.BoardService;
import com.example.service.MemberService;
import com.example.service.ProfileService;
import com.example.service.QuizService;
import com.example.util.JScript;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private ProfileService profileImgService;
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private QuizService quizService;
	
	

	// 년/월/일 형식의 폴더명 리턴하는 메소드
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String str = sdf.format(new Date());
		return str;
	} // getFolder

	@GetMapping("/account")
	public String account() {
		System.out.println("account 호출됨...");

		return "member/account";
	}

	@PostMapping("/signUp")
	public ResponseEntity<String> signUp(MemberVO memberVO) {

		// 회원가입 날짜 설정
		memberVO.setRegDate(new Date());

		// 비밀번호를 jbcrypt 라이브러리 사용해서 암호화하여 저장하기
		String passwd = memberVO.getPasswd();
		String pwHash = BCrypt.hashpw(passwd, BCrypt.gensalt()); // 60글자로 암호화된 문자열 리턴함
		memberVO.setPasswd(pwHash); // 암호화된 비밀번호 문자열로 수정하기

		// 생년월일에서 하이픈(-) 제거
		String birthday = memberVO.getBirthday();
		birthday = birthday.replace("-", ""); // 하이픈 문자열을 빈문자열로 변경
		memberVO.setBirthday(birthday);

		memberService.register(memberVO);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");

		String str = JScript.href("회원가입 성공!", "/member/account");

		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	} // signUp

	@PostMapping("/login")
	public ResponseEntity<String> login(String id, String passwd, @RequestParam(required = false, defaultValue = "false") boolean rememberMe,
			HttpSession session, HttpServletResponse response) {

		MemberVO memberVO = memberService.getMemberById(id);

		boolean PW = false;
		String message = "";
		if (memberVO != null) {
			PW = BCrypt.checkpw(passwd, memberVO.getPasswd());

			if (PW == false) {
				message = "아이디 또는 비밀번호가 일치하지 않습니다.";
			}
		} else {
			message = "아이디 또는 비밀번호가 일치하지 않습니다.";
		}

		// 로그인 실패
		if (memberVO == null || PW == false) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");

			String str = JScript.back(message);

			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}

		// 로그인 성공
		session.setAttribute("id", id);
		
		ProfileImg profileImg = profileImgService.getProfileImg(id);
		session.setAttribute("profileImg", profileImg);

		// 로그인 상태유지 체크
		if (rememberMe == true) {
			Cookie cookie = new Cookie("loginId", id);

			cookie.setMaxAge(60 * 60 * 24);
			cookie.setPath("/");
			response.addCookie(cookie);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/");

		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		session.invalidate();

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginId")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}

		return "redirect:/member/account";
	} // logout

	@GetMapping("/info")
	public void info(HttpSession session, Model model) {
		System.out.println("information 호출됨...");
		String id = (String) session.getAttribute("id");

		// 회원 정보 조회
		MemberVO memberVO = memberService.getMemberById(id);

		// 회원 프로필 사진 조회
		ProfileImg profileImg = profileImgService.getProfileImg(id);
		System.out.println("프로필 나오는지 test 중입니다... " + profileImg);

		// 뷰에서 사용할 데이터를 Model 객체에 저장 -> requestScope로 옮겨줌
		model.addAttribute("member", memberVO);
		model.addAttribute("profileImg", profileImg);

	}

	

	@GetMapping("/passwd")
	public String passwd(HttpSession session, Model model) {
		System.out.println("비밀번호 수정 페이지 호출됨...");
		String id = (String) session.getAttribute("id");
		model.addAttribute("id", id);
		return "/member/passwd";
	} // passwd 비밀번호 변경 페이지 호출

	@PostMapping("/passwd")
	public ResponseEntity<String> passwdPro(String id, String passwd, String npasswd, MemberVO memberVO) {
		
		MemberVO memberVO1 = memberService.getMemberById(id);
		String message = "";

		boolean isPasswdSame = BCrypt.checkpw(passwd, memberVO1.getPasswd());

		if (isPasswdSame == false) { // 비밀번호 일치하지 않음
			message = "비밀번호가 일치하지 않습니다.";
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");

			String str = JScript.back(message);
			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}

		String pwHash = BCrypt.hashpw(npasswd, BCrypt.gensalt()); // 60글자로 암호화된 문자열 리턴함

		memberVO1.setPasswd(pwHash);

		memberService.updateOnlyPasswd(memberVO1);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");

		String str = JScript.href("비밀번호변경 성공!", "/member/passwd");

		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	} // passwdPro // 비밀번호를 수정, 업데이트 ( 내 기존 비번 확인 후에 진행)
	
	// ====================================================================================================
	// ====================================================================================================

	
	// 프로필 업로드 메소드
		private ProfileImg uploadProfile(MultipartFile multipartFile, String id, String isProfileImg) throws IllegalStateException, IOException {

			ProfileImg profileImg = null;

			// 업로드 할 파일이 없으면 메소드 종료
			if (multipartFile == null) {
				return profileImg;
			}
			
			profileImg = new ProfileImg();

			String uploadFolder = "C:/team/upload"; // 업로드 기준경로

			File uploadPath = new File(uploadFolder, getFolder());

			// 프로필 사진일 경우(경로 변경)
			if (isProfileImg != null) {
				uploadFolder = "C:/team/upload/profile/" + id;
				uploadPath = new File(uploadFolder);
			}

			if (!uploadPath.exists()) {
				uploadPath.mkdirs();
			}

			if (!multipartFile.isEmpty()) {
				String originalFilename = multipartFile.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				String uploadFilename = uuid.toString() + "_" + originalFilename;

				File proFile = new File(uploadPath, uploadFilename); // 생성할 파일이름 정보

				multipartFile.transferTo(proFile);
				System.out.println("프로필 사진 파일 업로드 성공!");

				// 현재 업로드한 파일이 이미지 파일이면 썸네일 이미지를 추가로 생성하기
				File outFile = new File(uploadPath, "s_" + uploadFilename);

				Thumbnailator.createThumbnail(proFile, outFile, 200, 200); // 썸네일 이미지 파일 생성하기

				profileImg.setUuid(uuid.toString());
				profileImg.setUploadpath((isProfileImg != null) ? "profile" : getFolder());
				profileImg.setFilename(originalFilename);
				profileImg.setMemberId(id);
			}

			return profileImg;
		} // uploadProfile

	// ====================================================================================================
	// ====================================================================================================
	
	@GetMapping("/modify") // GET - "/member/modify"
	public String modifyForm(HttpSession session, Model model) throws Exception {
		String id = (String) session.getAttribute("id");

		// 회원 정보 조회
		MemberVO memberVO = memberService.getMemberById(id);

		// 회원 프로필 사진 조회
		ProfileImg profileImg = profileImgService.getProfileImg(id);
		System.out.println("프로필 나오는지 test 중입니다... " + profileImg);

		// 뷰에서 사용할 데이터를 Model 객체에 저장 -> requestScope로 옮겨줌
		model.addAttribute("member", memberVO);
		model.addAttribute("profileImg", profileImg);
		
		// 날짜 설정은 jsp 페이지에서 jstl 형식으로 해주기!!!!

		return "member/modifyMember";
	} // modifyForm 회원수정 페이지로!

	@PostMapping("/modify")
	public ResponseEntity<String> modifyPro(MemberVO memberVO, MultipartFile multipartFile, HttpSession session) throws IllegalStateException, IOException {

		System.out.println("POST modify... file : " + multipartFile.isEmpty()); // 파일이 받아와 지는지 콘솔창에서 확인하기!!
		
		String id = (String) session.getAttribute("id");

		// DB 테이블에서 id에 해당하는 데이터 행 가져오기
		MemberVO dbMemberVO = memberService.getMemberById(id);

		boolean isPasswdSame = BCrypt.checkpw(memberVO.getPasswd(), dbMemberVO.getPasswd());
		if (isPasswdSame == false) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");

			String str = JScript.back("비밀번호 틀림");

			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}
		// 비밀번호 일치할 때
		
		// ======================= 프로필 설정하기 ========================
		// 첨부파일 업로드(썸네일 생성) 후 profilepicVO 리턴
		ProfileImg profileImg = uploadProfile(multipartFile, memberVO.getId(), "profileImg"); // 예외처리하기
		System.out.println("POST modify... profilepicVO : " + profileImg); //

		// 업로드 또는 변경할 이미지 파일이 있는 경우
		if (profileImg != null) {
			// 현재 해당 id의 회원정보 프로필 사진 조회
			ProfileImg profilepic = profileImgService.getProfileImg(memberVO.getId());

			if (profilepic != null) { // 프로필 사진이 있으면

				// 프로필 사진 테이블에서 정보 업데이트하기
				profileImgService.updateProfileImg(profileImg);

			} else {
				// 프로필 사진 테이블에서 정보 삽입하기
				profileImgService.insertProfileImg(profileImg);
			}

			session.setAttribute("profileImg", profileImg); // VO도 같이 가져오기
		}

		// ---------------------------- 여기까지가 프로필 관련 내용 ----------------------------
		// ---------------------------- 회원 정보 관련 -------------------------
		
		// 회원정보 수정날짜로 수정하기
		memberVO.setRegDate(new Date());

		// 생년월일 문자열에서 하이픈(-) 기호 제거하기
		String birthday = memberVO.getBirthday();
		birthday = birthday.replace("-", ""); // 하이픈 문자열을 빈문자열로 대체
		memberVO.setBirthday(birthday);

		System.out.println(memberVO); // 서버 콘솔 출력
		
		
		memberService.updateById(memberVO);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");

		String str = JScript.href("회원정보 수정성공", "/");

		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	}// 수정하는 페이지 : Pro 역할, 처리하는 곳.

	@GetMapping("/remove")
	public String removeForm() {
		System.out.println("removeForm() 호출됨...");
		return "member/removeMember";
	} // removeForm

	@PostMapping("/remove")
	public ResponseEntity<String> removePro(String passwd, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("remove() 호출됨...");

		// 세션에서 로그인 아이디 가져오기
		String id = (String) session.getAttribute("id");

		// DB에서 아이디로 자신의 정보를 VO로 가져오기
		MemberVO memberVO = memberService.getMemberById(id);

		// 비밀번호 비교하기
		boolean isPasswdSame = BCrypt.checkpw(passwd, memberVO.getPasswd());

		// 비밀번호가 일치하지 않을 때
		if (isPasswdSame == false) { // !isPasswdSame 과 같음
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");

			String str = JScript.back("비밀번호 틀림");
			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}

		// 비밀번호가 일치할 때
		memberService.deleteById(id); // DB 레코드 삭제

		// 해당 아이디 회원 프로필 사진 조회
		ProfileImg isProfileImg = profileImgService.getProfileImg(id);

		// 프로필 사진 존재하면 삭제하기
		if (isProfileImg != null) {
			// 프로필 사진 정보 삭제
			profileImgService.deleteProfileImg(id);
			// 프로필 사진 삭제
			deleteProfile(isProfileImg, "profileImg");
		}

		session.invalidate(); // 세션 비우기

		// 쿠키값 가져오기
		Cookie[] cookies = request.getCookies();

		// 특정 쿠키 삭제하기(브라우저가 삭제하도록 유효기간 0초로 설정해서 보내기)
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginId")) { // ""안에 들어갈 아이디 잘 보기!
					cookie.setMaxAge(0); // 쿠키 유효기간 0초 설정(삭제 의도)
					cookie.setPath("/");
					response.addCookie(cookie); // 응답객체에 추가하기
				}
			}
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");

		String str = JScript.href("회원탈퇴 하였습니다.", "/");

		return new ResponseEntity<String>(str, headers, HttpStatus.OK);

	} // remove -- 실제 삭제처리

	// 프로필 삭제 메소드 (회원수정 및 탈퇴에 사용)
	private void deleteProfile(ProfileImg profileImg, String isProfileImg) {

		if (profileImg == null) {
			return;
		}

		String basePath = "C:/team/upload";

		String uploadpath = basePath + "/" + profileImg.getUploadpath();

		if (isProfileImg != null) {
			basePath = "C:/team/upload/profile/" + profileImg.getMemberId();

		}

		String filename = profileImg.getUuid() + "_" + profileImg.getFilename();

		File file = new File(uploadpath, filename);

		if (file.exists() == true) { // 해당 경로에 첨부파일이 존재하면
			file.delete(); // 첨부파일 삭제하기
		}

		File thumbnailFile = new File(uploadpath, "s_" + filename);

		if (thumbnailFile.exists() == true) {
			thumbnailFile.delete();
		}

	} // deleteProfile
	
	
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	// ----------- 여기서부터는 내가 쓴 글 관련 -----------------------------
	
	@GetMapping("/myboardList")
	public String myboardlistpage(Criteria cri, Model model, HttpSession session) {
		System.out.println("myboardlistpage 화면 호출됨...");
		
		String id = (String) session.getAttribute("id");
		
		// cri.setAmount(1000); // 페이징을 안하면 페이지에 나오는 개수가 없으니까 임의의 높은 숫자로 글 개수를 가져온다. 
		
		// board 테이블에서 (검색어가 있으면)검색, 페이징 적용한 글 리스트 가져오기 
		List<BoardVO> boardList = boardService.getBoardsbyMemberIdwithPaging(cri, id);
		
		// 검색유형, 검색어가 있으면 적용하여 글개수 가져오기
		int totalCount = boardService.getCountSearchingbyMemberId(cri, id);
		
		// 페이지블록 정보 객체준비. 필요한 정보를 생성자로 전달.
		PageDTO pageDTO = new PageDTO(cri, totalCount);
		
		
		// 뷰에서 사용할 데이터를 Model 객체에 저장 →  스프링(dispathcer servlet)이 requestScope로 옯겨줌.
		model.addAttribute("myboardList", boardList);
		model.addAttribute("pageMaker",pageDTO);
		
		return "member/myboardList";
	} // myboardlistpage
	
	
	
	@GetMapping("/myCommentList")
	public String myreplyListpage(Criteria cri, Model model, HttpSession session){
		System.out.println("myCommentlistpage 화면 호출됨...");
		
		String id = (String) session.getAttribute("id");
		
		// board 테이블에서 (검색어가 있으면)검색, 페이징 적용한 글 리스트 가져오기 
		List<CommentVO> commentList = boardService.getCommentsByPaging(cri, id);
		
		// 검색유형, 검색어가 있으면 적용하여 글개수 가져오기
		int totalCount = boardService.getCommentCountSearchingforMemberId(cri, id);
		
		// 페이지블록 정보 객체준비. 필요한 정보를 생성자로 전달.
		PageDTO pageDTO = new PageDTO(cri, totalCount);
		
		
		// 뷰에서 사용할 데이터를 Model 객체에 저장 →  스프링(dispathcer servlet)이 requestScope로 옯겨줌.
		model.addAttribute("myComment", commentList);
		model.addAttribute("pageMaker",pageDTO);
		
		return "member/myCommentList";
	} // myboardlistpage
	
	
	

	
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	// -------------------------------------------- 여기서부터는 내가 만든 퀴즈 관련
	
	
	@GetMapping("/myQuizList")
	public String myQuizListpage(Criteria cri, Model model, HttpSession session){
		System.out.println("myQuizListpage 화면 호출됨...");
		
		System.out.println("cri : " + cri);
		String id = (String) session.getAttribute("id");
		
		// BunchVO 테이블에서 (검색어가 있으면)검색, 페이징 적용한 글 리스트 가져오기 
		List<BunchVO> myQuizList = quizService.getBunchesById(cri, id);
		
		// 검색유형, 검색어가 있으면 적용하여 글개수 가져오기
		int totalCount = quizService.getCountBunchesById(id);
		
		// 페이지블록 정보 객체준비. 필요한 정보를 생성자로 전달.
		PageDTO pageDTO = new PageDTO(cri, totalCount);
		
		
		// 뷰에서 사용할 데이터를 Model 객체에 저장 →  스프링(dispathcer servlet)이 requestScope로 옯겨줌.
		model.addAttribute("myQuiz", myQuizList);
		model.addAttribute("pageMaker",pageDTO);
		
		return "member/myQuizList";
	} // myQuizListpage

	
	
	@GetMapping("/myPrevQuizList")
	public String myPrevQuizListpage(Criteria cri, Model model, HttpSession session){
		System.out.println("myPrevQuizListpage 화면 호출됨...");
		
		String id = (String) session.getAttribute("id");
		
		// SolveHistoryVO 테이블에서 (검색어가 있으면)검색, 페이징 적용한 글 리스트 가져오기 
		List<SolveHistoryVO> myPrevQuizList = quizService.getSolveHistoryAndBunch(cri, id);
		
		// 검색유형, 검색어가 있으면 적용하여 글개수 가져오기
		int totalCount = myPrevQuizList.size();
		
		// 페이지블록 정보 객체준비. 필요한 정보를 생성자로 전달.
		PageDTO pageDTO = new PageDTO(cri, totalCount);
		
		
		// 뷰에서 사용할 데이터를 Model 객체에 저장 →  스프링(dispathcer servlet)이 requestScope로 옯겨줌.
		model.addAttribute("quizCheck", myPrevQuizList);
		model.addAttribute("pageMaker",pageDTO);
		
		return "member/myPrevQuizList";
	} // myquizCheckListpage
	
	
	
	
	
  
}
