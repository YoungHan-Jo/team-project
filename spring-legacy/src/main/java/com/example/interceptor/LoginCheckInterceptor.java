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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");

		if (id == null) {
			response.sendRedirect("/member/account");
			return false;
		}

		MemberVO memberVO = memberService.getMemberById(id);
		System.out.println(memberVO);

		return true;
	}

}
