package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.ProfileImg;
import com.example.mapper.ProfileImgMapper;

@Service
public class ProfileImgService {

	@Autowired
	private ProfileImgMapper profileImgMapper;

	public ProfileImg getProfileImg(String id) {
		return profileImgMapper.getProfileImg(id);
	}

	public void insertProfileImg(ProfileImg profileImg) {
		profileImgMapper.insertProfileImg(profileImg);
	}

	public void updateProfileImg(ProfileImg profileImg) {
		profileImgMapper.updateProfileImg(profileImg);
	}

	public void deleteProfileImg(String id) {
		profileImgMapper.deleteProfileImg(id);
	}

}
