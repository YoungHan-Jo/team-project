package com.example.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.domain.BoardVO;
import com.example.domain.CommentVO;
import com.example.domain.Criteria;

public interface ViewMemberMapper {
	
	@Select("SELECT COUNT(*) AS cnt FROM board WHERE member_id = #{id} ")
	int getCountAll(); // 자신이 쓴 전체 글 개수 가져오기
	
	@Select("SELECT * FROM board WHERE member_id = #{id} ORDER BY re_ref DESC, re_seq ASC ")
	List<BoardVO> getBoardsAll(); // 순서대로 게시글 모두 가져오기
	
	
	// 매개변수가 2개인거는 무조건 @Param을 붙여서 하기!!!
	int getCountBySearch(@Param("cri") Criteria cri, @Param("id") String id); // 검색 조건 등이 있을 때 게시글 목록 갯수 가져오기

	List<BoardVO> getBoardsWithPaging(@Param("cri") Criteria cri, @Param("id") String id); // 페이징으로 게시글 가져오기
	
	
	BoardVO getBoardAndAttaches(String memberId); //내가 쓴 게시글 첨부파일이랑 같이 가져오기
	
	
	// ==================================================================================================== 여기까지가 내가 쓴 글 보기 mapper
	// ===================================여기서부터 내가 쓴 댓글 보기 mapper
	
	
	@Select("SELECT COUNT(*) AS cnt FROM comment WHERE member_id = #{id} ")
	int getCommentCountAll(); // 자신이 쓴 전체 댓글 개수 가져오기
	
	
	@Select("SELECT * FROM comment WHERE member_id = #{id} ORDER BY reg_date ")
	List<CommentVO> getCommentsbyRegDate(String id); // 자신이 쓴 전제 댓글 게시글 순서대로 가져오기
	
	int getCommentCountSearchingforMemberId(@Param("cri") Criteria cri, @Param("id") String id); // 검색 조건 등이 있을 때 댓글 목록 갯수 가져오기
	
	List<CommentVO> getCommentsByPaging(@Param("cri") Criteria cri, @Param("id") String id); // 페이징으로 댓글 가져오기
	
}
