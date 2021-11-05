package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.example.domain.AttachVO;
import com.example.domain.Criteria;

public interface AttachMapper {
	
	@Insert("INSERT INTO attach (uuid, uploadpath, filename, filetype, board_num) "
			+ "VALUES (#{uuid}, #{uploadpath}, #{filename}, #{filetype}, #{boardNum}) ")
	void addAttach(AttachVO attachVO);

	void addAttaches(List<AttachVO> attachList);

	@Select("SELECT * FROM attach WHERE uuid = #{uuid} ")
	AttachVO getAttachByUuid(String uuid);

	List<AttachVO> getAttachesByUuids(List<String> uuidList);

	List<AttachVO> getAttachesByboardNum(int boardNum);

	@Select("SELECT * FROM attach WHERE uploadpath = #{uploadpath} ")
	List<AttachVO> getAttachesByUploadpath(String uploadpath);

	@Delete("DELETE FROM attach WHERE board_num = #{boardNum} ")
	void deleteAttachesByboardNum(int boardNum);

	@Delete("DELETE FROM attach WHERE uuid = #{uuid} ")
	void deleteAttachByUuid(String uuid);

	int deleteAttachesByUuids(List<String> uuidList);

	List<AttachVO> getAttachImage(Criteria cri);

	@Select("SELECT COUNT(*) AS cnt FROM attach WHERE filetype='I' ")
	int getImageTotalCount();

}
