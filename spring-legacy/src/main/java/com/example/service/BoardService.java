package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.AttachVO;
import com.example.domain.BoardVO;
import com.example.domain.Criteria;
import com.example.mapper.AttachMapper;
import com.example.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private AttachMapper attachMapper;

	
	public List<BoardVO> getBoards(Criteria cri) {
		int startRow = (cri.getPageNum() - 1) * cri.getAmount();
		cri.setStartRow(startRow);

		List<BoardVO> boardList = boardMapper.getBoardsWithPaging(cri);
		return boardList;
	} // getBoards

	
	public int getCountBySearch(Criteria cri) {
		int count = boardMapper.getCountBySearch(cri);
		return count;
	}

	public void updateReadcount(int num) {
		boardMapper.updateViewcount(num);
	}

	public BoardVO getBoard(int num) {
		return boardMapper.getBoard(num);
	}

	public BoardVO getBoardAndAttaches(int num) {
		BoardVO boardVO = boardMapper.getBoardAndAttaches(num);
		return boardVO;
	}

	public int getNextnum() {
		return boardMapper.getNextnum();
	}
	
	public void addBoard(BoardVO boardVO) {
		boardMapper.addBoard(boardVO);
	}

	// 여기부분 수정했어요 혹시나 확인부탁드려요 잘못되었을 수도 있어요
	public BoardVO deleteBoard(int num) {
		return boardMapper.deleteBoardByNum(num);
	} 

}
