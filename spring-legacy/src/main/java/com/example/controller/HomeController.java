
package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
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
	public String examInfoServiceforMember(
			@RequestParam(required = false, defaultValue = "10") String numOfRows,
			@RequestParam(required = false, defaultValue = "1") String pageNo,
			@RequestParam(required = false, defaultValue = "xml") String dataFormat,
			@RequestParam(required = false, defaultValue = "2021") String implYy,
			@RequestParam(required = false, defaultValue = "T") String qualgbCd,
			@RequestParam(required = false, defaultValue = "7910") String jmCd,
			Model model) throws Exception {
		
		System.out.println("pageNo : "+ pageNo);
		System.out.println("dataFormat : " + dataFormat);
		System.out.println("numOfRows : " + numOfRows);
		System.out.println("implyYy : " + implYy);
		
		System.out.println("home의 시험 서비스도 같이 호출됨");
		List<OpenApiDTO> apiList = getExamInfo(numOfRows, pageNo, dataFormat, implYy, qualgbCd, jmCd);
		
		System.out.println("apiList : " + apiList);
		
		model.addAttribute("apiList", apiList);
		
		return "index";
	} // examInfoServiceforMember
	
	private List<OpenApiDTO> getExamInfo(String numOfRows, String pageNo,String dataFormat, String implYy, String qualgbCd, String jmCd) throws Exception {
		
		List<OpenApiDTO> apiList = new ArrayList<OpenApiDTO>(); // 리턴할 List 준비
		
		// 데이터 가져올 요청주소 만들기
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B490007/qualExamSchd/getQualExamSchdList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=9HO9k9I%2FIXeQLCVQLiHGJkcTKvvHZRy%2Fi6%2BZa%2F2mMUsZ7TIprrrKXIkJSBDuBjkBh5Ufb2bbweUEOSBUZGmSBQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("dataFormat","UTF-8") + "=" + URLEncoder.encode(dataFormat, "UTF-8")); /*format형식*/
        urlBuilder.append("&" + URLEncoder.encode("implYy","UTF-8") + "=" + URLEncoder.encode(implYy, "UTF-8")); /*검색할 생성일 범위의 시작*/
        urlBuilder.append("&" + URLEncoder.encode("qualgbCd","UTF-8") + "=" + URLEncoder.encode(qualgbCd, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("jmCd","UTF-8") + "=" + URLEncoder.encode(jmCd, "UTF-8")); 
        
        System.out.println("urlBuilder : "+ urlBuilder);
		// 이하 내용은 요청주소를 통해 가져온 XML 응답을 자바 오브젝트로 파싱하는 코드임.
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder(); // DocumentBuilder가 XML 파서이다.
		
		Document document = builder.parse(urlBuilder.toString()); // 요청주소를 DocumentBuilder에 인자로 전달하면 XML 응답을 Document 객체로 리턴받음.
		document.getDocumentElement().normalize();
		System.out.println("Root Element : " + document.getDocumentElement().getNodeName()); // Node는 html 형식을 말하는데 안에는 <>인 element가 있고, textnode(p 태그 사이에 오는 안녕하세요와 같은 text)가 있다. 
		
		NodeList nodeList = document.getElementsByTagName("item"); // 행단위로 반복되는 item 태그명을 기준으로 NodeList 가져옴.
		
		System.out.println("nodeList : "+ nodeList);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // Date 객체로 파싱할 SimpleDateFormat 준비.
		
		// 필요한 데이터만 DTO에 담아서 준비된 covidList에 추가하기
		for (int i=0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			System.out.println("Current Element : " + node.getNodeName());
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {// 여러 요소가 node 안에 있기 때문에 node인지 먼저 확인하기
				Element element = (Element) node;  // item 요소(Element)  // node 중에서 element만 사용할 것이기 때문에 element로 다운캐스팅 해주기
				OpenApiDTO dto = new OpenApiDTO();
				
				String implYyDTO = element.getElementsByTagName("implYy").item(0).getTextContent();
				dto.setImplYyDTO(implYyDTO);
				
				String implSeq = element.getElementsByTagName("implSeq").item(0).getTextContent();
				dto.setImplSeq(implSeq);
				
				String qualgbCdDTO = element.getElementsByTagName("qualgbCd").item(0).getTextContent();
				dto.setQualgbCdDTO(qualgbCdDTO);
				
				String qualgbNm = element.getElementsByTagName("qualgbNm").item(0).getTextContent();
				dto.setQualgbNm(qualgbNm);
				
				if (element.getElementsByTagName("docRegStartDt") != null) {
					String docRegStartDt = element.getElementsByTagName("docRegStartDt").item(0).getTextContent(); // 필수 요소들은 null 체크 필요 없지만 어떻게 하는지 알기 위해서 null 체크 한번 해보기!!
					dto.setDocRegStartDt(docRegStartDt);
				}
			
				if (element.getElementsByTagName("docRegStartDt") != null) {
					String docRegEndDt = element.getElementsByTagName("docRegEndDt").item(0).getTextContent();
					dto.setDocRegEndDt(docRegEndDt);
				}
				
				String docExamStartDt = element.getElementsByTagName("docExamStartDt").item(0).getTextContent();
				dto.setDocExamStartDt(sdf.parse(docExamStartDt));
				
				String docExamEndDt = element.getElementsByTagName("docExamEndDt").item(0).getTextContent();
				dto.setDocExamEndDt(sdf.parse(docExamEndDt));
				
				String docPassDt = element.getElementsByTagName("docPassDt").item(0).getTextContent();
				dto.setDocPassDt(sdf.parse(docPassDt));
				
				String pracRegStartDt = element.getElementsByTagName("pracRegStartDt").item(0).getTextContent();
				if (pracRegStartDt.length() > 0) { // pracRegStartDt != null && !pracRegStartDt.equals("") 
					dto.setPracRegStartDt(sdf.parse(pracRegStartDt));
				}
				
				String pracRegEndDt = element.getElementsByTagName("pracRegEndDt").item(0).getTextContent();
				if (!pracRegEndDt.equals("")) { // equals보다는 length가 좀 더 속도가 빠르다
					dto.setPracRegEndDt(sdf.parse(pracRegEndDt));
				}
				
				String pracExamStartDt = element.getElementsByTagName("pracExamStartDt").item(0).getTextContent();
				if (pracExamStartDt.length() > 0 ) {
					dto.setPracExamStartDt(sdf.parse(pracExamStartDt));
				}
				
				String pracExamEndDt = element.getElementsByTagName("pracExamEndDt").item(0).getTextContent();
				if (pracExamEndDt.length() > 0 ) {
					dto.setPracExamEndDt(sdf.parse(pracExamEndDt));
				}
				
				String pracPassDt = element.getElementsByTagName("pracPassDt").item(0).getTextContent();
				if (pracPassDt.length() > 0) {
					dto.setPracPassDt(sdf.parse(pracPassDt));
				}
				
				System.out.println("dto : " + dto);
				
				apiList.add(dto); // 리스트에 DTO를 추가
			} // if
		} // for
		
		System.out.println("파싱결과 : " + apiList);
        
		return apiList; // 파싱된 List를 리턴
	} // getCovid19XmlData

}
