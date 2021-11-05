package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.BoardVO;
import com.example.domain.CommentVO;
import com.example.mapper.CommentMapper;

@Service
public class CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	public List<CommentVO> getCommentsByBoardNum(int boardNum) {
		return commentMapper.getCommentsByBoardNum(boardNum);
	}
	
	public CommentVO getBoard(int num) {
		return commentMapper.getComment(num);
	}
	
	public void addComment(CommentVO commentVO) {
		commentMapper.addComment(commentVO);
	}

	public int getNextnum() {
		return commentMapper.getNextnum();
	}
	
	public void updateComment(CommentVO commentVO) {
		commentMapper.updateComment(commentVO);
	}
	
	public void deleteComment(int num) {
		commentMapper.deleteCommentByNum(num);
	} 
}
