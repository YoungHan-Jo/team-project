package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.AttachVO;
import com.example.domain.Criteria;
import com.example.mapper.AttachMapper;

@Service
public class AttachService {

	@Autowired
	private AttachMapper attachMapper;

	
	public List<AttachVO> getAttachesByBoardNum(int boardNum) {
		return attachMapper.getAttachesByboardNum(boardNum);
	}

	public List<AttachVO> getAttachesByUploadpath(String uploadpath) {
		return attachMapper.getAttachesByUploadpath(uploadpath);
	}

	public List<AttachVO> getAttachesByUuids(List<String> uuidList) {
		return attachMapper.getAttachesByUuids(uuidList);
	}

	public int deleteAttachesByUuids(List<String> uuidList) {
		return attachMapper.deleteAttachesByUuids(uuidList);
	}

	public List<AttachVO> getAttachImage(Criteria cri) {

		int startRow = (cri.getPageNum() - 1) * cri.getAmount();
		cri.setStartRow(startRow);

		return attachMapper.getAttachImage(cri);
	}

	public int getImageTotalCount() {
		int count = attachMapper.getImageTotalCount();
		return count;
	}

}
