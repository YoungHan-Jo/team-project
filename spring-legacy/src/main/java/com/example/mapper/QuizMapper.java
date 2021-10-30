package com.example.mapper;

import java.util.List;

import com.example.domain.Criteria;
import com.example.domain.PackageVO;

public interface QuizMapper {
	
	
	//=================== select ======================
	
	List<PackageVO> getPackagesByCri(Criteria cri);
	
	
	
	//=================== insert ======================
	
	
	

}
