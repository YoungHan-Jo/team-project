package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.MemberVO;

public interface MemberMapper {

	@Insert("INSERT INTO member (id, passwd, name, birthday, gender, email, recv_email, reg_date) "
			+ "VALUES (#{id}, #{passwd}, #{name}, #{birthday}, #{gender}, #{email}, #{recvEmail}, #{regDate}) ")
	int insert(MemberVO memberVO);

	@Delete("DELETE FROM member")
	int deleteAll();

	@Delete("DELETE FROM member WHERE id = #{id} ")
	int deleteById(String id);

	@Update("UPDATE member "
			+ "SET name = #{name}, birthday = #{birthday}, gender = #{gender}, email = #{email}, recv_email = #{recvEmail}, reg_date = #{regDate} "
			+ "WHERE id = #{id} ")
	void updateById(MemberVO memberVO);

	@Select("SELECT count(*) AS cnt FROM member WHERE id = #{id}")
	int getCountById(String id);

	@Select("SELECT count(*) AS cnt FROM member")
	int getCountAll();

	@Select("SELECT * FROM member WHERE id = #{id}")
	MemberVO getMemberById(String id);

	@Select("SELECT * FROM member ORDER BY id")
	List<MemberVO> getMembers();

	@Update("UPDATE member SET passwd = #{passwd} WHERE id = #{id} ")
	void updateOnlyPasswd(MemberVO memberVO);

	MemberVO getMemberAndProfileImg(String id);

}
