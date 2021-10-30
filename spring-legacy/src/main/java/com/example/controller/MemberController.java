package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.MemberVO;
import com.example.service.MemberService;
import com.example.service.ProfileService;
import com.example.util.JScript;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private ProfileService profileImgService;

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

}
