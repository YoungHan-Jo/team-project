package com.example.aop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.MemberVO;
import com.example.mapper.MemberMapper;
import com.example.service.MemberService;

/*
public class MemberServiceProxy extends MemberService {
	
	@Autowired
	private LogAdvice logAdvice;
	@Autowired
	private MemberService memberService;
	
	public MemberServiceProxy(MemberMapper memberMapper) {
		super(memberMapper);
	}

	@Override
	public void register(MemberVO memberVO) {
		logAdvice.logBefore();
		super.register(memberVO);
	}

	@Override
	public int deleteById(String id) {
		return super.deleteById(id);
	}

	@Override
	public void updateById(MemberVO memberVO) {
		super.updateById(memberVO);
	}

	@Override
	public int getCountById(String id) {
		return super.getCountById(id);
	}

	public MemberVO getMemberById(String id) {
		return super.getMemberById(id);
	}

	@Override
	public List<MemberVO> getMembers() {
		return super.getMembers();
	}
}
*/