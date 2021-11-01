package com.example.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// HandlerInterceptor 인터페이스 이름은 다른말로 하면 ControllerInterceptor

public class RememberMeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 컨트롤러 메서드가 실행되기 전 호출됨

		// 요청 사용자의 세션 가져오기
		HttpSession session = request.getSession();
		// 세션에 로그인 아이디가 있는 확인 (이미 로그인 되어있는지 확인)
		String id = (String) session.getAttribute("id");
		// 세션에 로그인 아이디가 없으면 쿠키에서 아이디 찾아서 세션에 저장(로그인 처리)
		if (id == null) {
			// 쿠키 배열객체 가져오기
			Cookie[] cookies = request.getCookies();

			// 로그인 상태유지용 쿠키정보를 찾기
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("loginId")) {
						id = cookie.getValue();

						// 세션에 저장 (로그인 인증 처리)
						session.setAttribute("id", id);
					}
				} // for
			}
		} // if

		// 리턴값이 true면 호출이 예정되있는 해당 컨트롤러 메소드를 호출함.
		// 리턴값이 false면 호출이 예정되있는 해당 컨트롤러 메소드를 호출 안함.
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 메서드 실행직후 view페이지 렌더링 되기 전 호출됨
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// view페이지가 렌더링 되고 난 후 호출됨
	}

}
