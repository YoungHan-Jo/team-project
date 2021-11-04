package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.domain.Criteria;
import com.example.domain.BunchVO;
import com.example.domain.QuizVO;

public interface QuizMapper {
	
	
	//=================== select ======================
	
	List<BunchVO> getBunchesByCri(Criteria cri);
	
	int getNextBunchNum();
	
	BunchVO getBunchAndQuizList(int bunchNum);
	
	// 퀴즈 전체 조회를 하기위한 SQL문
	@Select("SELECT * FROM quiz ORDER BY bunch_num")
	List<QuizVO> getQuiz();
	
	//=================== insert ======================
	
	void addBunch(BunchVO bunchVO);
	
	void addQuizList(List<QuizVO> quizList);
	
	
	

}
