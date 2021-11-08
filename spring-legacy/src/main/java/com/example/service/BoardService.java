package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.AttachVO;
import com.example.domain.BoardVO;
import com.example.domain.CommentVO;
import com.example.domain.Criteria;
import com.example.mapper.AttachMapper;
import com.example.mapper.BoardMapper;
import com.example.mapper.CommentMapper;
import com.example.mapper.ViewMemberMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private AttachMapper attachMapper;
	@Autowired
	private ViewMemberMapper viewMapper;
	@Autowired
	private CommentMapper commentMapper;

	public List<BoardVO> getBoards(Criteria cri) {
		int startRow = (cri.getPageNum() - 1) * cri.getAmount();
		cri.setStartRow(startRow);

		List<BoardVO> boardList = boardMapper.getBoardsWithPaging(cri);
		return boardList;
	}

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

	public void updateBoard(BoardVO boardVO) {
		boardMapper.updateBoard(boardVO);
	}

	@Transactional
	public void addBoardAndAttaches(BoardVO boardVO) {

		boardMapper.addBoard(boardVO);

		List<AttachVO> attachList = boardVO.getAttachList();

		if (attachList.size() > 0) {
			attachMapper.addAttaches(attachList);
		}
	}

	@Transactional
	public void addReplyAndAttaches(BoardVO boardVO) {
		// 답글을 남길 대상글과 같은 글그룹(reRef) 안에서
		// 대상글의 순번(reSeq)보다 큰 글들의 순번을 1씩 증가(UPDATE)
		boardMapper.updateReSeqPlusOne(boardVO.getReRef(), boardVO.getReSeq());

		// insert할 답글 re값으로 수정
		boardVO.setReLev(boardVO.getReLev() + 1);
		boardVO.setReSeq(boardVO.getReSeq() + 1);

		// 답글 insert 하기
		boardMapper.addBoard(boardVO);

		// 첨부파일 정보 insert하기
		List<AttachVO> attachList = boardVO.getAttachList();
		if (attachList != null && attachList.size() > 0) {
			attachMapper.addAttaches(attachList);
		}
	}

	@Transactional
	public void deleteBoardAndAttachesAndComments(int num) {
		attachMapper.deleteAttachesByboardNum(num);
		boardMapper.deleteBoardByNum(num);
		commentMapper.deleteCommentByNum(num);
	}

	@Transactional
	public void updateBoardAndInsertAttachesAndDeleteAttaches(BoardVO boardVO, List<AttachVO> newAttachList,
			List<String> delUuidList) {
		if (newAttachList != null && newAttachList.size() > 0) {
			attachMapper.addAttaches(newAttachList);
		}
		if (delUuidList != null && delUuidList.size() > 0) {
			attachMapper.deleteAttachesByUuids(delUuidList);
		}
		boardMapper.updateBoard(boardVO);
	}

	// =========================== 내 정보에서 내가 쓴 글 가져오기 관련된 메소드
	// =======================================

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
	
	
	// =========================== 내가 쓴 댓글 가져오기 관련 메소드
	
	public int getCommentCountAllbyMemberId() {
		return viewMapper.getCommentCountAll();
	} // 회원 아이디로 쓴 댓글 갯수 가져오기
	
	public List<CommentVO> getCommentsbyMemberIdOrderByDate(String id) {
		List<CommentVO> commentList = viewMapper.getCommentsbyRegDate(id);
		return commentList;
	} // 회원 아이디로 작성한 댓글을 날짜 순으로 가져오기
	
	
	public int getCommentCountSearchingforMemberId(Criteria cri, String id) {
		int count = viewMapper.getCommentCountSearchingforMemberId(cri, id);
		return count;
	} // 검색 조건이 있을 때 내 아이디로 작성한 댓글 갯수 가져오기
	
	
	public List<CommentVO> getCommentsByPaging(Criteria cri, String id) {
		int startRow = (cri.getPageNum() - 1) * cri.getAmount();
		cri.setStartRow(startRow);
		
		List<CommentVO> commentList = viewMapper.getCommentsByPaging(cri, id);
		return commentList;
	} // 회원 아이디로 댓글 페이징해서 가져오기

}
