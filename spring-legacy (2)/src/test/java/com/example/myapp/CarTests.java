package com.example.myapp;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// @RunWith도 @Component계열 애노테이션에 해당하므로
// CarTests 클래스도 실행되면 스프링 빈이 됨! (빈과 빈 끼리는 의존관계 주입이 가능함)

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CarTests {

	@Autowired
	private Car car;
	
//	@Before
//	public void setUp() {
//		HyundaiEngine hyundaiEngine = new HyundaiEngine();
//		car = new Car(hyundaiEngine);
//	}
	
	@Test
	public void testCar() {
		
		assertNotNull(car);
		
		car.drive();
	}
}




