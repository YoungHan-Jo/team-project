package com.example.myapp;

import org.springframework.stereotype.Component;

@Component // 스프링이 관리하는 객체(스프링 빈, 빈)
public class HyundaiEngine implements Engine {

	@Override
	public void start() {
		System.out.println("현대 엔진이 동작: 부릉부릉~~");
	}
}
