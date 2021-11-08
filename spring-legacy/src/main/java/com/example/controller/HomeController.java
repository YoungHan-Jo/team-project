package com.example.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.domain.OpenApiDTO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class HomeController {

//	@GetMapping(value = { "/", "/index" })
//	public String home() {
//		System.out.println("home() 호출됨...");
//
//		return "index";
//	} // home

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
	
	
	
	// 외부 api 등록 controller
	@GetMapping(value = { "/", "/index" })
	public String homeAPI(
			@RequestParam(required = false, defaultValue = "10") String numOfRows,
			@RequestParam(required = false, defaultValue = "1") String pageNo,
			@RequestParam(required = false, defaultValue = "json") String dataFormat,
			@RequestParam(required = false, defaultValue = "2021") String implYy,
			@RequestParam(required = false, defaultValue = "T") String qualgbCd,
			@RequestParam(required = false, defaultValue = "7910") String jmCd,
			Model model) throws Exception {
		
			List<OpenApiDTO> apiList = getApiList(numOfRows, pageNo, dataFormat, implYy, qualgbCd, jmCd);
			
			System.out.println("apiList : " +apiList);
			
			model.addAttribute("apiList", apiList);
		
		return "index";
	} // homeAPI
	
	private List<OpenApiDTO> getApiList(String numOfRows, String pageNo, String dataFormat, String implYy, String qualgbCd, String jmCd) throws Exception{
		
		List<OpenApiDTO> apiList = new ArrayList<OpenApiDTO>();
		
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B490007/qualExamSchd/getQualExamSchdList"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + "WW16hiX2MbDQMssfhL75OdFFAoJ47xq202Eb4A%2FoEjJ9W1HAAI8p5IzSqje7xi930mcFoin%2FZPotmpWSjUseqQ%3D%3D"); /*공공데이터포털에서 받은 인증키*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("dataFormat","UTF-8") + "=" + URLEncoder.encode(dataFormat, "UTF-8")); /*응답 데이터 표준 형식 - xml / json (대소문자 구분 없음)*/
	        urlBuilder.append("&" + URLEncoder.encode("implYy","UTF-8") + "=" + URLEncoder.encode(implYy, "UTF-8")); /*시행년도*/
	        urlBuilder.append("&" + URLEncoder.encode("qualgbCd","UTF-8") + "=" + URLEncoder.encode(qualgbCd, "UTF-8")); /*자격구분코드 - T : 국가기술자격 - C : 과정평가형자격 - W : 일학습병행자격 - S : 국가전문자격*/
	        urlBuilder.append("&" + URLEncoder.encode("jmCd","UTF-8") + "=" + URLEncoder.encode(jmCd, "UTF-8")); /*종목코드 값 (예) 7910 : 한식조리 기능사(검정형)*/
		
	        URL url = new URL(urlBuilder.toString());
	        System.out.println(url.toString());
	        
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        
	        BufferedReader bf;
	        bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
	        
	        String result = bf.readLine();
	        
	        JsonParser jsonParser = new JsonParser();
	        
	        JsonObject jsonObject = (JsonObject)jsonParser.parse(result);
	        jsonObject.get("items");
	        
	        
	        
	        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
	        DocumentBuilder builder = builderFactory.newDocumentBuilder();
	        
	        Document document = builder.parse(urlBuilder.toString());
	        document.getDocumentElement().normalize();
	        System.out.println("Root Element : " + document.getDocumentElement().getNodeName());
	        
	        document.getElementsByTagName("items");
	        
	        NodeList nodeList = document.getElementsByTagName("items");
	        
	        System.out.println("nodeList size : " + nodeList.getLength());
	        
	        for(int i = 0; i < nodeList.getLength(); ++i) {
	        	Node node = nodeList.item(i);
	        	System.out.println("Current Element : "+ node.getNodeName());
	        	
	        	if(node.getNodeType() == Node.ELEMENT_NODE) {
	        		Element element = (Element) node;
	        		OpenApiDTO apiDTO = new OpenApiDTO();
	        		
	        		String implYyDTO = element.getElementsByTagName("implYy").item(0).getTextContent();
					apiDTO.setImplYyDTO(implYyDTO);
					String implSeqDTO = element.getElementsByTagName("implSeq").item(0).getTextContent();
					apiDTO.setImplYyDTO(implSeqDTO);
					String docRegStartDt = element.getElementsByTagName("docRegStartDt").item(0).getTextContent();
					apiDTO.setImplYyDTO(docRegStartDt);
					String docRegEndDt = element.getElementsByTagName("docRegEndDt").item(0).getTextContent();
					apiDTO.setImplYyDTO(docRegEndDt);
					String docExamStartDt = element.getElementsByTagName("docExamStartDt").item(0).getTextContent();
					apiDTO.setImplYyDTO(docExamStartDt);
					String docExamEndDt = element.getElementsByTagName("docExamEndDt").item(0).getTextContent();
					apiDTO.setImplYyDTO(docExamEndDt);
					String docPassDt = element.getElementsByTagName("docPassDt").item(0).getTextContent();
					apiDTO.setImplYyDTO(docPassDt);
					String pracRegStartDt = element.getElementsByTagName("pracRegStartDt").item(0).getTextContent();
					apiDTO.setImplYyDTO(pracRegStartDt);
					String pracRegEndDt = element.getElementsByTagName("pracRegEndDt").item(0).getTextContent();
					apiDTO.setImplYyDTO(pracRegEndDt);
					String pracExamStartDt = element.getElementsByTagName("pracExamStartDt").item(0).getTextContent();
					apiDTO.setImplYyDTO(pracExamStartDt);
					String pracExamEndDt = element.getElementsByTagName("pracExamEndDt").item(0).getTextContent();
					apiDTO.setImplYyDTO(pracExamEndDt);
					String pracPassDt = element.getElementsByTagName("pracPassDt").item(0).getTextContent();
					apiDTO.setImplYyDTO(pracPassDt);
					
					System.out.println(i +" 번 아이템 : "+ apiDTO);
	        		
					apiList.add(apiDTO);
	        	}
	        }
	        
		return apiList;
	}
	

}
