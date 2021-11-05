package com.example.mapper;

import java.util.List;
import java.util.Map;

import com.example.domain.Criteria;
import com.example.domain.BunchVO;
import com.example.domain.QuizVO;
import com.example.domain.SolveHistoryVO;

public interface QuizMapper {

	// =================== select ======================

	List<BunchVO> getBunchesByCri(Criteria cri);

	int getNextBunchNum();
	
	int getNextSolveHistoryNum();

	BunchVO getBunchAndQuizList(int bunchNum);

	List<String> getAnswerListByBunchNum(int bunchNum);
	
	SolveHistoryVO getSolveHistoryByNum(int num);
	
	List<QuizVO> getQuizListByResult(Map<String, Object> map);
	
	List<BunchVO> getBunchesById(String id);
	
	List<SolveHistoryVO> getSolveHistoryAndBunch(String id);

	// =================== insert ======================

	void addBunch(BunchVO bunchVO);

	void addQuizList(List<QuizVO> quizList);
	
	void insertSolveHistory(SolveHistoryVO solveHistoryVO);
	
	// =================== delete ======================
	
	void deleteQuizList(int bunchNum);
	
	void deleteBunch(int num);
	

}
