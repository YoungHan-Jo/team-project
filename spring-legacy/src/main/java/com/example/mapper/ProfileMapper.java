package com.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.ProfileImg;

public interface ProfileMapper {

	@Select("SELECT * FROM profile_img WHERE member_id=#{id}")
	ProfileImg getProfileImg(String id);

	@Insert("INSERT INTO profile_img (uuid, uploadpath, filename, member_id) "
			+ "VALUES (#{uuid},#{uploadpath},#{filename},#{memberId}) ")
	void insertProfileImg(ProfileImg profileImg);

	@Update("UPDATE profile_img "
			+ "SET uuid=#{uuid}, uploadpath=#{uploadpath}, filename=#{filename} "
			+ "WHERE member_id = #{memberId} ")
	void updateProfileImg(ProfileImg profileImg);

	@Delete("DELETE FROM profile_img WHERE member_id = #{id} ")
	void deleteProfileImg(String id);

}
