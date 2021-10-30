package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Criteria;
import com.example.domain.PackageVO;
import com.example.mapper.QuizMapper;

@Service
@Transactional
public class QuizService {
	
	private QuizMapper quizMapper;

	public QuizService(QuizMapper quizMapper) {
		super();
		this.quizMapper = quizMapper;
	}
	
	public List<PackageVO> getPackagesByCri(Criteria cri){
		
		int startRow = (cri.getPageNum() - 1) * cri.getAmount();
		
		cri.setStartRow(startRow);
		
		return quizMapper.getPackagesByCri(cri);
	}
	

}
