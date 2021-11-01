package com.example.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/*
AOP(Aspect Oriented Programming : 관점 지향 프로그래밍)
  - 관점 : 주변부 로직에 대한 관심사를 의미함
  - 주변부 로직(공통 기능)을 클래스단위로 주요 로직과 완전히 분리해서 개발하는 방식.
    스프링은 AOP기능을 타겟 메소드 호출 기준으로 주요 로직에 주변부 로직을 결합시켜줌.
 
 * Advice : 주변부 로직(공통기능)을 가진 클래스. (주변부 로직 + 적용될 대상 메소드 표현)
 * Target : 주요 로직을 가지고 있는, 주변부 로직이 적용될 대상 오브젝트.
 * Join Point : 주변부 로직이 적용될 수 있는 메소드 후보들.
 * Point Cut : 조인포인트(후보)들 중에 실제 결합시킬 메소드를 의미함.
*/

@Aspect  // 주변부 로직을 가진 어드바이스 클래스
@Component  // 스프링 빈으로 등록
public class LogAdvice {

	// AOP 애노테이션 값으로 포인트컷 표현식으로 타겟 오브젝트의 포인트컷을 표현함
	@Before("execution( * com.example.service.MemberService.*(..) )")
	public void logBefore() {
		System.out.println("===================== logBefore() ===========================");
	}
	
	// @Around 사용 시에는 반드시 매개변수를 ProceedingJoinPoint 타입을 선언하고
	// 리턴타입은 Object 가 되야 함에 주의!
	@Around("execution( * com.example.service.MemberService.*(..) )")
	public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("===================== logTime() ===========================");

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		String methodName = methodSignature.getName(); // 타깃 메소드의 이름
		
		Object[] args = joinPoint.getArgs(); // 타깃 메소드의 매개변수 정보
		List<Object> argsList = Arrays.asList(args);
		
		System.out.println("메소드명: " + methodName + ", 매개변수: " + argsList);
		
		
		long beginTime = System.currentTimeMillis();
		
		Object result = joinPoint.proceed(); // 실제 타깃 메소드를 호출함!
		System.out.println("@Around result : " + result);
		
		long endTime = System.currentTimeMillis();
		
		long diffTime = endTime - beginTime;
		System.out.println(methodName + " 메소드의 실행시간: " + diffTime + "ms");
		
		// proceed 메소드가 리턴한 결과는 리턴을 해줘야 함
		return result;
	} // logTime
	
	
	
	
	
}









