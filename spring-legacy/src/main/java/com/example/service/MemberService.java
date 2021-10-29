package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.MemberVO;
import com.example.mapper.MemberMapper;

@Service // @Component 계열 애노테이션. 트랜잭션 처리 기능 가짐
public class MemberService {

	private MemberMapper memberMapper;

	public MemberService(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	public void register(MemberVO memberVO) {
		memberMapper.insert(memberVO);
	}

	public int deleteById(String id) {
		return memberMapper.deleteById(id);
	}

	public void updateById(MemberVO memberVO) {
		memberMapper.updateById(memberVO);
	}

	public void updateOnlyPasswd(MemberVO memberVO) {
		memberMapper.updateOnlyPasswd(memberVO);
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
