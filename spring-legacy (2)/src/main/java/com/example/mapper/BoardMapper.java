package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.domain.BoardVO;
import com.example.domain.Criteria;

public interface BoardMapper {

	@Delete("DELETE FROM board")
	void deleteAll();
	
	//@Delete("DELETE FROM board WHERE num = #{num}")
	void deleteBoardByNum(int num);
	
	//@Select("SELECT COUNT(*) AS cnt FROM board")
	int getCountAll();
	
	
	int getNextnum();
	
	
	
	void addBoard(BoardVO boardVO);
	
	
	List<BoardVO> getBoardsAll();
	
	
	BoardVO getBoard(int num);
	
	
	// 조회수 1 증가시키는 메소드
	void updateReadcount(int num);
	
	
	// 게시글 수정하기
	void updateBoard(BoardVO boardVO);
	
	
	// 답글쓰기에서 답글을 다는 대상글과 같은 글그룹 내에서
	// 답글을 다는 대상글의 그룹내 순번보다 큰 글들의 순번을 1씩 증가시키기
	// 매퍼 메소드 매개변수 특이사항: 매개변수가 2개 이상일 경우,
	//                                각 매개변수마다 SQL문에서 사용할 이름을 지정해야 함
	void updateReSeqPlusOne(
			@Param("reRef") int reRef, 
			@Param("reSeq") int reSeq);
	
	
	// 검색어가 적용된 전체글개수 가져오기
	int getCountBySearch(Criteria cri);
	
	
	// 페이징으로 글 가져오기
	List<BoardVO> getBoardsWithPaging(Criteria cri);
	
	
	// join 쿼리로 글번호에 해당하는 게시글 한개와 첨부파일 리스트 모두 가져오기 
	BoardVO getBoardAndAttaches(int num);
	
	
}






