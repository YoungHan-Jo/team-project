package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.ProfileImg;
import com.example.mapper.ProfileMapper;

@Service
public class ProfileService {

	@Autowired
	private ProfileMapper profileMapper;

	public ProfileImg getProfileImg(String id) {
		return profileMapper.getProfileImg(id);
	}

	public void insertProfileImg(ProfileImg profileImg) {
		profileMapper.insertProfileImg(profileImg);
	}

	public void updateProfileImg(ProfileImg profileImg) {
		profileMapper.updateProfileImg(profileImg);
	}

	public void deleteProfileImg(String id) {
		profileMapper.deleteProfileImg(id);
	}

}
