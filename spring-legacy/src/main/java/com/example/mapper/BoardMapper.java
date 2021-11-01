package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.BoardVO;

public interface BoardMapper {
	
	
	@Insert("INSERT INTO board (num, board_type, , member_id, subject, content, view_count, reg_date, ipaddr, re_ref, re_lev, re_seq)"
			+ "VALUES (#{num}, #{boardType}, #{memberId}, #{subject}, #{content}, #{vieCount}, #{regDate}, #{ipaddr}, #{reRef}, #{reLev}, #{reSeq})")
	int addBoard(BoardVO boardVO);
	
	@Select("SELECT COUNT(*) AS cnt FROM board")
	int getCountAll();
	
	@Select("SELECT FROM board WHERE num = #{num}")
	BoardVO getBoard(int num);
	
	@Select("SELECT * FROM board ORDER BY re_ref DESC, re_seq ASC")
	List<BoardVO> getBoardsAll();
	
	@Select("SELECT IFNULL(MAX(num), 0) + 1 AS nextnum FROM board")
	int getNextnum();
	
	@Update("UPDATE board SET subject = #{subject}, content = #{content} WHERE num = #{num} ")
	void updateBoard(BoardVO boardVO);
	
	@Update("UPDATE board SET viewcount = viewcount + 1 WHERE num = #{num}")
	void updateViewcount(int num);
	
	@Delete("DELETE FROM board WHERE num = #{num}")
	void deleteBoardByNum(int num);
	
	@Delete("DELETE FROM board")
	void deleteAll();
}
