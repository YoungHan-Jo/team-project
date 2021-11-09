package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.CommentVO;

public interface CommentMapper {
	
	@Insert("INSERT INTO comment (num, board_num, member_id, content, reg_date, re_ref, re_lev, re_seq)"
			+ "VALUES (#{num}, #{boardNum}, #{memberId}, #{content}, #{regDate}, #{reRef}, #{reLev}, #{reSeq})")
	int addComment(CommentVO commentVO);
	
	@Select("SELECT * FROM comment WHERE board_num = #{boardNum}")
	List<CommentVO> getCommentsByBoardNum(int boardNum);
	
	@Select("SELECT * FROM comment WHERE num = #{num}")
	CommentVO getComment(int num);
	
	@Select("SELECT COUNT(*) AS cnt FROM comment")
	int getCountAll();
	
	@Select("SELECT IFNULL(MAX(num), 0) + 1 AS nextnum FROM comment")
	int getNextnum();
	
	@Update("UPDATE board SET content = #{content} WHERE num = #{num} ")
	void updateComment(CommentVO commentVO);
	
	@Delete("DELETE FROM comment WHERE num = #{num}")
	int deleteCommentByNum(int num);
	
	@Delete("DELETE FROM comment WHERE board_num = #{boardNum}")
	void deleteCommentByBoardNum(int num);
	
	@Delete("DELETE FROM comment")
	void deleteAll();
	
	@Select("SELECT * FROM comment ORDER BY re_ref DESC, re_seq ASC ")
	List<CommentVO> getCommentsAll();
	
	@Update("UPDATE comment SET re_seq = re_seq + 1 WHERE re_ref = #{reRef} AND re_seq > #{reSeq} ")
	void updateReSeqPlusOne(@Param("reRef") int reRef, @Param("reSeq") int reSeq);
	
}
