package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.BoardVO;
import com.example.domain.Criteria;

public interface BoardMapper {
	
	
	@Insert("INSERT INTO board (num, board_type, member_id, subject, content, view_count, reg_date, re_ref, re_lev, re_seq)"
			+ "VALUES (#{num}, #{boardType}, #{memberId}, #{subject}, #{content}, #{viewCount}, #{regDate}, #{reRef}, #{reLev}, #{reSeq})")
	int addBoard(BoardVO boardVO);
	
	@Select("SELECT COUNT(*) AS cnt FROM board")
	int getCountAll();
	
	@Select("SELECT FROM board WHERE num = #{num}")
	BoardVO getBoard(int num);
	
	@Select("SELECT * FROM board ORDER BY re_ref DESC, re_seq ASC")
	List<BoardVO> getBoardsAll();
	
	@Select("SELECT IFNULL(MAX(num), 0) + 1 AS nextnum FROM board")
	int getNextnum();
	
	int getCountBySearch(Criteria cri);
	
	List<BoardVO> getBoardsWithPaging(Criteria cri);
	
	BoardVO getBoardAndAttaches(int num);
	
	@Update("UPDATE board SET subject = #{subject}, content = #{content} WHERE num = #{num} ")
	void updateBoard(BoardVO boardVO);
	
	@Update("UPDATE board SET view_count = view_count + 1 WHERE num = #{num}")
	void updateViewcount(int num);
	
	@Update("UPDATE board SET re_seq = re_seq + 1 WHERE re_ref = #{reRef} AND re_seq > #{reSeq} ")
	void updateReSeqPlusOne(@Param("reRef") int reRef, @Param("reSeq") int reSeq);

	
	@Delete("DELETE FROM board WHERE num = #{num}")
	void deleteBoardByNum(int num);
	
	@Delete("DELETE FROM board")
	void deleteAll();
}
