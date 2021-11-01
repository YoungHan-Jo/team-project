package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.domain.MemberVO;
import com.example.service.MemberService;

public class LoginCheckInterceptor implements HandlerInterceptor {
	
	@Autowired
	private MemberService memberService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("LoginCheckInterceptor.............");
		
		// 요청 객체로부터 세션 참조 가져오기
		HttpSession session = request.getSession();
		//로그인 검증하기. 세션값 가져오기
		String id = (String) session.getAttribute("id");
		//로그인 세션값 없으면, "/member/login" 으로 이동
		if (id == null) {
			response.sendRedirect("/member/login");
			return false; // false를 리턴하여 호출 예정이었던 컨트롤러 메소드를 호출 안함!
		}
		
		
		MemberVO memberVO = memberService.getMemberById(id);
		System.out.println(memberVO);
		
		return true;
	}
	
}
