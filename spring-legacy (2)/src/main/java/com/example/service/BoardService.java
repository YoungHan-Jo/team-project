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
	
	
	// 페이징, 검색어 적용하여 글 목록 가져오기
	public List<BoardVO> getBoards(Criteria cri) {
		// 시작 행번호 (MySQL의 LIMIT절의 시작행번호)

		// 한 페이지당 글개수(amount)가 10개씩일때
		// 1 페이지 -> 0
		// 2 페이지 -> 10
		// 3 페이지 -> 20
		// 4 페이지 -> 30
		int startRow = (cri.getPageNum() - 1) * cri.getAmount();
		cri.setStartRow(startRow);
		
		List<BoardVO> boardList = boardMapper.getBoardsWithPaging(cri);
		return boardList;
	} // getBoards
	
	// 페이징, 검색어 적용하여 글 개수 가져오기
	public int getCountBySearch(Criteria cri) {
		int count = boardMapper.getCountBySearch(cri);
		return count;
	}
	
	
	public void updateReadcount(int num) {
		boardMapper.updateReadcount(num);
	}
	
	
	public BoardVO getBoard(int num) {
		return boardMapper.getBoard(num);
	}
	
	
	public BoardVO getBoardAndAttaches(int num) {
		// join 쿼리 없이 따로따로 데이터 가져오기
//		BoardVO boardVO = boardMapper.getBoard(num);
//		List<AttachVO> attachList = attachMapper.getAttachesByBno(num);
//		boardVO.setAttachList(attachList);
//		return boardVO;
		
		// join 쿼리로 데이터 가져오기
		BoardVO boardVO = boardMapper.getBoardAndAttaches(num);
		return boardVO;
	}
	
	
	public int getNextnum() {
		return boardMapper.getNextnum();
	}
	
	
	// 주글쓰기 메소드(게시글 정보와 첨부파일정보를 한개의 트랜잭션으로 처리)
	@Transactional
	public void addBoardAndAttaches(BoardVO boardVO) {
		// attach 테이블의 bno 컬럼이 외래키로서
		// board 테이블의 num 컬럼을 참조하므로
		// board 레코드가 먼저 insert된 이후에 attach 레코드가 insert 가능함.
		boardMapper.addBoard(boardVO);
		
		List<AttachVO> attachList = boardVO.getAttachList();
		
		if (attachList.size() > 0) {
//			for (AttachVO attachVO : attachList) {
//				attachMapper.addAttach(attachVO);
//			}
			attachMapper.addAttaches(attachList);
		}
	} // addBoardAndAttaches
	
	
	// 답글쓰기 메소드(게시글 정보와 첨부파일정보를 한개의 트랜잭션으로 처리)
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
	} // addReplyAndAttaches
	
	
	
	
	@Transactional
	public void deleteBoardAndAttaches(int num) {
		// 외래키 관계가 있다면 삭제 순서는 외래키로 참조하는 테이블부터 삭제함에 유의!
		attachMapper.deleteAttachesByBno(num);
		boardMapper.deleteBoardByNum(num);
	} // deleteBoardAndAttaches
	
	
	@Transactional
	public void updateBoardAndInsertAttachesAndDeleteAttaches(BoardVO boardVO, List<AttachVO> newAttachList, List<String> delUuidList) {
		if (newAttachList != null && newAttachList.size() > 0) {
			attachMapper.addAttaches(newAttachList);
		}
		if (delUuidList != null && delUuidList.size() > 0) {
			attachMapper.deleteAttachesByUuids(delUuidList);
		}
		boardMapper.updateBoard(boardVO);
	} // updateBoardAndInsertAttachesAndDeleteAttaches
	
	
	
}







