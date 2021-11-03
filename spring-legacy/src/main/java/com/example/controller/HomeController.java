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

@Controller
public class HomeController {

	@GetMapping(value = { "/", "/index" })
	public String home() {
		System.out.println("home() 호출됨...");

		return "index";
	} // home

	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getImageFile(String fileName) throws IOException {
		File file = new File("C:/team/upload", fileName);

		HttpHeaders headers = new HttpHeaders();
		String contentType = Files.probeContentType(file.toPath());
		headers.add("Content-Type", contentType);

		byte[] imageData = FileCopyUtils.copyToByteArray(file);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(imageData, headers, HttpStatus.OK);

		return responseEntity;
	} // getImageFile

	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName) throws UnsupportedEncodingException {
		File file = new File("C:/team/upload", fileName);

		Resource resource = new FileSystemResource(file);

		if (!resource.exists()) { // 다운로드할 파일이 존재하지 않으면
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND); // 404코드. 자원없음 응답코드 보내고 종료.
		}

		// 다운로드할 파일이 존재하면
		String resourceName = resource.getFilename();
		int beginIdenx = resourceName.indexOf("_") + 1;
		String originFilename = resourceName.substring(beginIdenx); // 순수 파일면 구하기

		// 다운로드 파일형식으로 변환
		String downFilename = new String(originFilename.getBytes("utf-8"), "iso-8859-1");

		HttpHeaders headers = new HttpHeaders();

		// 애노테이션의 produces 속성으로 대체함
		headers.add("Content-Disposition", "attachment; filename=" + downFilename);

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	} // downloadFile

}
