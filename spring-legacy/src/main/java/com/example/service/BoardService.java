package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.BoardVO;
import com.example.domain.Criteria;
import com.example.mapper.AttachMapper;
import com.example.mapper.BoardMapper;

public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private AttachMapper attachMapper;

	
	public BoardVO getBoard(int num) {
		return boardMapper.getBoard(num);
	}
	
	public void updateReadcount(int num) {
		boardMapper.updateViewcount(num);
	}

	public int getNextnum() {
		return boardMapper.getNextnum();
	}
	
	

}
