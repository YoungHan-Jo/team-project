package com.example.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 메소드(생성자 포함)의 매개변수를 통해 외부로부터 필요한 의존객체를 주입받으면
// 객체 간의 결합도를 낮출수 있음 -> 유지보수가 용이한 구조가 됨.


@Component // 스프링이 관리하는 객체(스프링 빈, 빈)
           // 스프링은 빈과 빈 끼리만 서로 의존관계를 주입해준다.
public class Car {
	
	//@Autowired
	private Engine engine;
	
//	public Car() {
//	}
	
	// @Autowired 없이 생성자 방식으로 의존객체를 주입받으려면
	// 의존객체를 매개변수로 받는 생성자 정의 한개만 존재해야 함.
	public Car(Engine engine) {
		super();
		this.engine = engine;
	}
	
	//@Autowired
	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void drive() {
		engine.start();
		System.out.println("자동차가 움직입니다.");
	}
}
