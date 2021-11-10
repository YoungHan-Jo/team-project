package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.AttachVO;
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

	public CommentVO getComment(int num) {
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

	public int deleteComment(int num) {
		return commentMapper.deleteCommentByNum(num);
	}

	public List<CommentVO> getCommentsAll() {
		return commentMapper.getCommentsAll();
	}
	
	@Transactional
	public void addReply(CommentVO commentVO) {
		commentMapper.updateReSeqPlusOne(commentVO.getReRef(), commentVO.getReSeq());
		
		commentVO.setReLev(commentVO.getReLev() + 1);
		commentVO.setReSeq(commentVO.getReSeq() + 1);

		commentMapper.addComment(commentVO);
	}
}
