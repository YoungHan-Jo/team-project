package com.example.util;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class JsonUtils {
	
	// json String 문자열을 List<Integer>로 변환해주는 매서드
	public static List<Integer> strJsonToList(String strJson) {
		Gson gson = new Gson();
		// strJson -> Integer[] 역직렬화
		Integer[] fromJson = gson.fromJson(strJson, Integer[].class);
		// 배열 -> 리스트
		List<Integer> fromJsonToList = Arrays.asList(fromJson);
		
		return fromJsonToList;
	}

}
