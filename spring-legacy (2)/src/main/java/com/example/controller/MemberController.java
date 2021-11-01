package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.MemberVO;
import com.example.mapper.MemberMapper;
import com.example.service.MemberService;
import com.example.util.JScript;

// @Component 계열 애노테이션이므로 스프링 빈이 됨
// 스프링의 프론트컨트롤러인 DispatcherServlet 객체가 사용할 컨트롤러가 됨

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	//@Autowired
	private MemberService memberService;
	
	// 생성자로 의존객체를 받도록 선언하면 @Autowired 애노테이션 생략 가능
	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	@GetMapping("/join")   // GET - "/member/join"
	public void joinForm() {
		System.out.println("join 호출됨...");
		
		//return "member/join";
		
		// 컨트롤러 메소드의 리턴타입이 void일 경우는
		// 요청 URL주소(여기서는 "/member/join")를 
		// 실행할 JSP 경로명("member/join")으로 사용함.
		// URL 요청 경로명과 JSP 경로명이 같을경우 사용할 수 있음.
	}
	
	
	@PostMapping("/join")  // POST - "/member/join"
	public ResponseEntity<String> join(MemberVO memberVO) {
		// 스프링의 프론트 컨트롤러(DispatcherServlet)는
		// 호출하는 컨트롤러의 매개변수 타입이 VO에 해당(getter/setter가 존재)하면
		// VO객체를 생성후 사용자 요청 파라미터값을 자동으로 채워서 넣어줌.
		
		// 회원가입날짜 설정
		memberVO.setRegDate(new Date());
		
		// 비밀번호를 jbcrypt 라이브러리 사용해서 암호화하여 저장하기
		String passwd = memberVO.getPasswd();
		String pwHash = BCrypt.hashpw(passwd, BCrypt.gensalt()); // 60글자로 암호화된 문자열 리턴함
		memberVO.setPasswd(pwHash); // 암호화된 비밀번호 문자열로 수정하기
		
		// 생년월일 문자열에서 하이픈(-) 기호 제거하기
		String birthday = memberVO.getBirthday();
		birthday = birthday.replace("-", ""); // 하이픈 문자열을 빈문자열로 대체
		memberVO.setBirthday(birthday);
		
		System.out.println(memberVO); // 서버 콘솔 출력
		
		// 회원가입 insert 처리하기
		memberService.register(memberVO);
		
		
		// 리다이렉트 방법 1.
		//return "redirect:/member/login";  // 리다이렉트 요청경로를 문자열로 리턴
		
		// 리다이렉트 방법 2.
		// 스프링에서는 너무 많은 권한을 가진 request, response 객체에 대해서 직접적인 접근을 최소화하길 권장함.
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		String str = JScript.href("회원가입 성공!", "/member/login");
		
		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	} // join
	
	
	@GetMapping("/joinIdDupChk")  // GET - "/member/joinIdDupChk"
	public void joinIdDupChk(@ModelAttribute("id") String id, Model model) {
		// 아이디 중복여부 확인
		int count = memberService.getCountById(id);
		
		model.addAttribute("count", count);
		//model.addAttribute("id", id);
		
		// 스프링에서는 request에 직접 데이터를 쓰지않는다.
		// Model 타입 객체에 데이터를 쓰면 request 영역객체에 데이터를 자동으로 옮겨줌.
		// request.setAttribute("count", count);
		// request.setAttribute("id", id);
		
		//return "/member/joinIdDupChk";
	} // joinIdDupChk
	
	
	@GetMapping("/login")   // GET - "/member/login"
	public void login() {
		System.out.println("login 호출됨...");
		//return "member/login";
	}
	
	@PostMapping("/login")  // POST - "/member/login"
	public ResponseEntity<String> login(String id, String passwd, 
			@RequestParam(required = false, defaultValue = "false") boolean rememberMe,
			HttpSession session, HttpServletResponse response) {
		// 콘트롤러 메소드의 매개변수 형식이 기본자료형(String형 포함)에 해당하면
		// 해당 매개변수 이름으로 사용자 입력 파라미터값을 자동으로 찾아서 넣어줌.
		
		MemberVO memberVO = memberService.getMemberById(id);
		
		boolean isPasswdSame = false;
		String message = "";
		
		if (memberVO != null) { // id 일치
			isPasswdSame = BCrypt.checkpw(passwd, memberVO.getPasswd());
			
			if (isPasswdSame == false) { // 비밀번호 일치하지 않음
				message = "비밀번호가 일치하지 않습니다.";
			}
		} else { // id 불일치
			message = "존재하지 않는 아이디 입니다.";
		}
		
		// 로그인 실패인 경우 (없는 아이디거나 비밀번호가 틀렸을때)
		if (memberVO == null || isPasswdSame == false) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			
			String str = JScript.back(message);
			
			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}
		
		// 로그인 성공인 경우
		// 로그인 인증 처리
		// 사용자 당 유지되는 세션객체에 기억할 데이터를 저장
		session.setAttribute("id", id);
		
		// 로그인 상태유지가 체크되었으면(사용자가 로그인 유지를 원하면)
		if (rememberMe == true) {
			// 쿠키 생성
			Cookie cookie = new Cookie("loginId", id);
			// 쿠키 유효시간(유통기한) 설정
			//cookie.setMaxAge(60 * 10); // 초단위로 설정. 10분 = 60초 * 10
			cookie.setMaxAge(60 * 60 * 24 * 7); // 1주일 설정.

			// 쿠키 경로설정
			cookie.setPath("/"); // 프로젝트 모든 경로에서 쿠키 받도록 설정
			
			// 클라이언트로 보낼 쿠키를 response 응답객체에 추가하기. -> 응답시 쿠키도 함께 보냄.
			response.addCookie(cookie);
		}
		
		// 리다이렉트 방식 1. - String
		//return "redirect:/";
		
		// 리다이렉트 방식 2. - ResponseEntity
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/");  // redirect 경로를 "/"로 지정
		// 리다이렉트일 경우는 응답코드로 HttpStatus.FOUND 를 지정해야함에 주의!
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	} // login

	
	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		// 세션 비우기
		session.invalidate();
		
		// 로그인 상태유지용 쿠키가 있으면 삭제처리하기
		// 쿠키값 가져오기
		Cookie[] cookies = request.getCookies();

		// 특정 쿠키 삭제하기(브라우저가 삭제하도록 유효기간 0초로 설정해서 보내기)
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginId")) {
					cookie.setMaxAge(0); // 쿠키 유효기간 0초 설정(삭제 의도)
					cookie.setPath("/");
					response.addCookie(cookie); // 응답객체에 추가하기
				}
			}
		}
		
		// 로그인 화면으로 리다이렉트 이동
		return "redirect:/member/login";
	} // logout
	
	
	@GetMapping("/modify")  // GET - "/member/modify"
	public String modifyForm(HttpSession session, Model model) throws Exception {
		
		// 세션에서 로그인 아이디 가져오기
		String id = (String) session.getAttribute("id");
		
		// 아이디에 해당하는 자신의 정보를 DB에서 가져오기
		MemberVO memberVO = memberService.getMemberById(id);
		
		// input type="date" 태그에 설정가능한 값이 되도록 생년월일 문자열을 변경하기
		// "20020127" -> "2002-01-27"
		
		// 문자열 -> Date 객체 변환
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		Date date = sdf.parse(memberVO.getBirthday()); // 생년월일 문자열("20020127")을 Date 객체로 변환

		// Date 객체 -> 문자열 변환
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//		String strBirthday = sdf2.format(date); // Date 객체를 생년월일 문자열("2002-01-27")로 변환
//		memberVO.setBirthday(strBirthday);  // VO에 생일 문자열("2002-01-27") 수정
		
		model.addAttribute("member", memberVO);
		
		return "/member/modifyMember";
	} // modifyForm
	
	
	@PostMapping("/modify")
	public ResponseEntity<String> modify(MemberVO memberVO) {
		// 회원정보 수정날짜로 수정하기
		memberVO.setRegDate(new Date());
		
		// 생년월일 문자열에서 하이픈(-) 기호 제거하기
		String birthday = memberVO.getBirthday();
		birthday = birthday.replace("-", ""); // 하이픈 문자열을 빈문자열로 대체
		memberVO.setBirthday(birthday);

		System.out.println(memberVO); // 서버 콘솔 출력
		
		// DB 테이블에서 id에 해당하는 데이터 행 가져오기
		MemberVO dbMemberVO = memberService.getMemberById(memberVO.getId());
		
		boolean isPasswdSame = BCrypt.checkpw(memberVO.getPasswd(), dbMemberVO.getPasswd());
		
		// 비밀번호 일치하지 않을 때
		if (isPasswdSame == false) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			
			String str = JScript.back("비밀번호 틀림");
			
			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}
		
		// 비밀번호 일치할 때
		memberService.updateById(memberVO); // 회원정보 수정하기
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		String str = JScript.href("회원정보 수정성공", "/");
		
		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	} // modify

	
	@GetMapping("/remove")
	public String removeForm() {
		System.out.println("removeForm() 호출됨...");
		
		return "member/removeMember";
	} // removeForm
	
	
	@PostMapping("/remove")
	public ResponseEntity<String> remove(String passwd, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("remove() 호출됨...");
		
		// 세션에서 로그인 아이디 가져오기
		String id = (String) session.getAttribute("id");
		
		// DB에서 아이디로 자신의 정보를 VO로 가져오기
		MemberVO memberVO = memberService.getMemberById(id);
		
		// 비밀번호 비교하기
		boolean isPasswdSame = BCrypt.checkpw(passwd, memberVO.getPasswd());
		
		// 비밀번호가 일치하지 않을때 
		if (isPasswdSame == false) {  // !isPasswdSame
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			
			String str = JScript.back("비밀번호 틀림");
			
			return new ResponseEntity<String>(str, headers, HttpStatus.OK);
		}
		
		// 비밀번호가 일치할 때
		memberService.deleteById(id); // DB 레코드 삭제
		
		session.invalidate(); // 세션 비우기
		
		// 쿠키값 가져오기
		Cookie[] cookies = request.getCookies();

		// 특정 쿠키 삭제하기(브라우저가 삭제하도록 유효기간 0초로 설정해서 보내기)
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginId")) {
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
	} // remove
	
	
}










