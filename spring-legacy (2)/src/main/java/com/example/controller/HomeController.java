package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // @Component 계열 애노테이션
public class HomeController {
	
	@GetMapping(value = {"/", "/index"})
	public String home() {
		System.out.println("home() 호출됨...");
		
		return "index";
	}
	
	
	@GetMapping("/display")
	@ResponseBody  // 컨트롤러 메소드가 리턴하는 데이터 자체를 바로 응답으로 주고자 할 경우 사용함
	public ResponseEntity<byte[]> getImageFile(String fileName) throws IOException {
		System.out.println("fileName : " + fileName);
		
		File file = new File("C:/ksw/upload", fileName);
		System.out.println("실제 이미지 파일 경로 : " + file.getPath());
		
		HttpHeaders headers = new HttpHeaders();
		
		String contentType = Files.probeContentType(file.toPath());
		headers.add("Content-Type", contentType); // "image/jpeg"  "image/png"  "image/gif"
		
		byte[] imageData = FileCopyUtils.copyToByteArray(file);
		
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(imageData, headers, HttpStatus.OK);
		return responseEntity;
	} // getImageFile
	
	
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName) throws UnsupportedEncodingException {
		System.out.println("fileName : " + fileName);
		
		File file = new File("C:/ksw/upload", fileName);
		
		Resource resource = new FileSystemResource(file);
		System.out.println("resource : " + resource);
		
		if (resource.exists() == false) { // 다운로드할 파일이 존재하지 않으면
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND); // 404 코드. 자원없음 응답코드 보내고 종료.
		}
		
		// 다운로드할 파일이 존재하면
		String resourceName = resource.getFilename();
		System.out.println("resourceName : " + resourceName);
		// resourceName : 7f34243e-d680-451e-b935-02ad55345ece_학습안내서.pdf
		
		int beginIndex = resourceName.indexOf("_") + 1;
		String originFilename = resourceName.substring(beginIndex);  // 순수 파일명 구하기
		System.out.println("originFilename : " + originFilename);  // "학습안내서.pdf"
		
		// 다운로드 파일명의 문자셋을 "utf-8"에서 "iso-8859-1"로 변환하기
		String downloadName = new String(originFilename.getBytes("utf-8"), "iso-8859-1");
		System.out.println("downloadName : " + downloadName);
		
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Content-Type", "application/octet-stream"); // 애노테이션의 produces 속성으로 대체함
		headers.add("Content-Disposition", "attachment; filename=" + downloadName); // 다운로드 파일명을 헤더에 설정하기
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	} // downloadFile
	
	
	
	
}






