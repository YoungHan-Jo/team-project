package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.NoticeVO;
import com.mysql.cj.protocol.x.Notice;

public interface NoticeMapper {

	@Select("SELECT * FROM notice WHERE num={num} ")
	Notice getNotice(int num);
	
	@Insert("INSERT INTO notice(num, subject, content, view_count, reg_date) "
			+ "VALUES(#{num}, #{subject}, #{content}, #{viewCount}, #{regDate}) ")
	void insertNotice(NoticeVO noticeVO);
	
	@Update("UPDATE notice"
			+ "SET subject=#{subject}, content=#{content}, view_count=#{viewCount}, reg_date=#{regDate} "
			+ "WHERE num = #{num} ")
	void updateNotice(Notice notice);
	
	@Delete("DELETE FROM notice WHERE num = #{num} ")
	void deleteNotice(int num);
	
	@Select("SELECT * FROM notice ORDER BY num ")
	List<NoticeVO> getNotices();
	
}
