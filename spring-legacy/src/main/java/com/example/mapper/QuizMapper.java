package com.example.mapper;

import java.util.List;

import com.example.domain.Criteria;
import com.example.domain.PackageVO;
import com.example.domain.QuizVO;

public interface QuizMapper {
	
	
	//=================== select ======================
	
	List<PackageVO> getPackagesByCri(Criteria cri);
	
	int getNextPackageNum();
	
	
	//=================== insert ======================
	
	void addPackage(PackageVO packageVO);
	
	void addQuizList(List<QuizVO> quizList);
	
	
	

}
