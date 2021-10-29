package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.BoardVO;
import com.example.mapper.BoardMapper;

public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public BoardVO getBoard(int num) {
		return boardMapper.getBoard(num);
	}

}
