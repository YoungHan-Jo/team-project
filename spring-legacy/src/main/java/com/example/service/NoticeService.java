package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.NoticeVO;
import com.example.mapper.NoticeMapper;
import com.mysql.cj.protocol.x.Notice;

@Service
public class NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	public Notice getNotice(int num) {
		return noticeMapper.getNotice(num);
	}
	
	public void insertNotice(NoticeVO noticeVO) {
		noticeMapper.insertNotice(noticeVO);
	}
	
	public void updaeNotice(Notice notice) {
		noticeMapper.updateNotice(notice);
	}
	
	public void deleteNotice(int num) {
		noticeMapper.deleteNotice(num);
	}
	
	public List<NoticeVO> getNotices(){
		return noticeMapper.getNotices();
	}
}
