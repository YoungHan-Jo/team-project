package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.MemberVO;
import com.example.mapper.MemberMapper;

@Service  // @Component 계열 애노테이션. 트랜잭션 처리 기능 가짐
//@Transactional  // 클래스에 선언하면 이 클래스에 있는 모든 메소드가 트랜잭션으로 동작함
public class MemberService {

	//@Autowired
	private MemberMapper memberMapper;

	// 생성자로 의존객체를 받도록 선언하면 @Autowired 애노테이션 생략 가능
	public MemberService(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	
	public void register(MemberVO memberVO) {
//		long beginTime = System.currentTimeMillis();
		
		memberMapper.insert(memberVO);
		
//		long endTime = System.currentTimeMillis();
//		long diffTime = endTime - beginTime;
//		System.out.println("메소드 실행시간: " + diffTime + "ms");
	}
	
	public int deleteById(String id) {
		return memberMapper.deleteById(id);
	}
	
	public void updateById(MemberVO memberVO) {
		memberMapper.updateById(memberVO);
	}
	
	public int getCountById(String id) {
		return memberMapper.getCountById(id);
	}
	
	public MemberVO getMemberById(String id) {
		return memberMapper.getMemberById(id);
	}
	
	public List<MemberVO> getMembers() {
		return memberMapper.getMembers();
	}
	
}




