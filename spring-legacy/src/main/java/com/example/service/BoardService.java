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
import com.example.mapper.ViewMemberMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private ViewMemberMapper viewMapper;

	
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

	public void deleteBoard(int num) {
		boardMapper.deleteBoardByNum(num);
	} 
	
	
	
	
	
	
	// =========================== 내 정보에서 내가 쓴 글 가져오기 관련된 메소드 =======================================
	
	public int getCountAllbyMemberId() {
		return viewMapper.getCountAll();
	} // 회원 아이디로 쓴 글 개수 가져오기
	
	
	public List<BoardVO> getBoardsAllbyMemberID() {
		List<BoardVO> boardList = viewMapper.getBoardsAll();
		return boardList;
	} // 회원 아이디로 쓴 게시글 목록 모두 가져오기
	
	
	public BoardVO getBoardAndAttachesbyMemberID(String memberId) {
		return viewMapper.getBoardAndAttaches(memberId);
	} // 회원 아이디로 게시글과 첨부파일 모두 가져오기
	
	
	public List<BoardVO> getBoardsbyMemberIdwithPaging(Criteria cri, String id) {
		int startRow = (cri.getPageNum() - 1) * cri.getAmount();
		cri.setStartRow(startRow);

		List<BoardVO> boardList = viewMapper.getBoardsWithPaging(cri, id);
		return boardList;
	} // 회원 아이디로 게시글 페이징으로 가져오기
	
	
	public int getCountSearchingbyMemberId(Criteria cri, String id) {
		int count = viewMapper.getCountBySearch(cri, id);
		return count;
	}

}
