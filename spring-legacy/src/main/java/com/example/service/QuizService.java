package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Criteria;
import com.example.domain.QuizVO;
import com.example.domain.BunchVO;
import com.example.mapper.QuizMapper;

@Service
@Transactional
public class QuizService {
	
	private QuizMapper quizMapper;

	public QuizService(QuizMapper quizMapper) {
		super();
		this.quizMapper = quizMapper;
	}
	
	public List<BunchVO> getBunchesByCri(Criteria cri){
		
		int startRow = (cri.getPageNum() - 1) * cri.getAmount();
		
		cri.setStartRow(startRow);
		
		return quizMapper.getBunchesByCri(cri);
	}
	
	public int getNextBunchNum() {
		return quizMapper.getNextBunchNum();
	}
	
	public void addBunchAndQuizList(BunchVO bunchVO) {
		quizMapper.addBunch(bunchVO);
		quizMapper.addQuizList(bunchVO.getQuizList());
		
	}
	
	public BunchVO getBunchAndQuizList(int bunchNum){
		return quizMapper.getBunchAndQuizList(bunchNum);
	}
	

	public List<QuizVO> getQuiz(){
		return quizMapper.getQuiz();
	}
	
}
