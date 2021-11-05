package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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
	
	List<BunchVO> getBunchesById(@Param("cri") Criteria cri, @Param("id") String id); // 내가 만든 퀴즈 bunch로 목록 가져오기
	
	int getCountBunchesById(String id);
	
	List<SolveHistoryVO> getSolveHistoryAndBunch(@Param("cri") Criteria cri, @Param("id") String id); // 내가 푼 퀴즈 목록 가져오기
	
	int getCountSolveHistory(String id); // 내가 푼 퀴즈 목록 개수

	// =================== insert ======================

	void addBunch(BunchVO bunchVO);

	void addQuizList(List<QuizVO> quizList);
	
	void insertSolveHistory(SolveHistoryVO solveHistoryVO);
	
	// =================== delete ======================
	
	void deleteQuizList(int bunchNum);
	
	void deleteBunch(int num);
	

}
