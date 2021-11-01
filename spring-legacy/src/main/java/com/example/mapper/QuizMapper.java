package com.example.mapper;

import java.util.List;

import com.example.domain.Criteria;
import com.example.domain.BunchVO;
import com.example.domain.QuizVO;

public interface QuizMapper {
	
	
	//=================== select ======================
	
	List<BunchVO> getBunchesByCri(Criteria cri);
	
	int getNextBunchNum();
	
	List<QuizVO> getBunchAndQuizList(int bunchNum);
	
	
	//=================== insert ======================
	
	void addBunch(BunchVO bunchVO);
	
	void addQuizList(List<QuizVO> quizList);
	
	
	

}
