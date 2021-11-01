package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.example.domain.AttachVO;

public interface AttachMapper {

	
	@Insert("INSERT INTO attach (uuid, uploadpath, filename, filetype, bno) "
			+ "VALUES (#{uuid}, #{uploadpath}, #{filename}, #{filetype}, #{bno}) ")
	void addAttach(AttachVO attachVO);
	
	
	void addAttaches(List<AttachVO> attachList);
	
	
	// 첨부파일 한개 정보 가져오기
	@Select("SELECT * FROM attach WHERE uuid = #{uuid} ")
	AttachVO getAttachByUuid(String uuid);
	
	
	List<AttachVO> getAttachesByUuids(List<String> uuidList);
	
	
	// 특정 게시글에 포함된 첨부파일 정보 가져오기
	List<AttachVO> getAttachesByBno(int bno);
	
	
	// 업로드 경로가 일치하는 첨부파일 정보 가져오기
	@Select("SELECT * FROM attach WHERE uploadpath = #{uploadpath} ")
	List<AttachVO> getAttachesByUploadpath(String uploadpath);
	
	
	// 특정 게시글번호에 해당하는 첨부파일들 삭제하기
	@Delete("DELETE FROM attach WHERE bno = #{bno} ")
	void deleteAttachesByBno(int bno);
	
	
	// uuid에 해당하는 첨부파일 한개 삭제하기
	@Delete("DELETE FROM attach WHERE uuid = #{uuid} ")
	void deleteAttachByUuid(String uuid);
	
	
	
	int deleteAttachesByUuids(List<String> uuidList);
	
	
}









