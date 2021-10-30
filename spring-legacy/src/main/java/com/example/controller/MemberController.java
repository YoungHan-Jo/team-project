package com.example.controller;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> master
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.MemberVO;
import com.example.domain.ProfileImg;
import com.example.service.MemberService;
import com.example.service.ProfileImgService;
import com.example.util.JScript;

import net.coobird.thumbnailator.Thumbnailator;

import com.example.service.MemberService;
import com.example.service.ProfileImgService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private ProfileImgService profileImgService;

<<<<<<< HEAD
=======
	@GetMapping("/loginAndSignUp")
	public String signUpForm() {
		System.out.println("signUp 호출됨...");

		return "member/loginAndSignUp";
	}

	@PostMapping("/loginAndSignUp")
	public ResponseEntity<String> signUp(MemberVO memberVO, MultipartFile file, HttpSession session)
			throws IllegalStateException, IOException {

		ProfileImg profileImg = uploadProfile(file, memberVO.getId(), "profileImg");

		// 업로드 또는 변경할 이미지 파일이 있는경우
		if (profileImg != null) {

			profileImgService.insertProfileImg(profileImg);

			session.setAttribute("profileImg", profileImg);
		}

		// 회원가입 날짜 설정
		memberVO.setRegDate(new Date());

		// 비밀번호를 jbcrypt 라이브러리 사용해서 암호화하여 저장하기
		String passwd = memberVO.getPasswd();
		String pwHash = BCrypt.hashpw(passwd, BCrypt.gensalt()); // 60글자로 암호화된 문자열 리턴함
		memberVO.setPasswd(pwHash); // 암호화된 비밀번호 문자열로 수정하기

		// 생년월일에서 하이픈(-) 제거
		String birthday = memberVO.getBirthday();
		birthday = birthday.replace("-", ""); // 하이픈 문자열을 빈문자열로 변경
		memberVO.setBirthday(birthday);

		memberService.register(memberVO);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");

		String str = JScript.href("회원가입 성공!", "/member/loginAndSignUp");

		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	} // signUp

	// 프로필 업로드 메소드
	private ProfileImg uploadProfile(MultipartFile file, String id, String isProfileImg)
			throws IllegalStateException, IOException {

		ProfileImg profileImg = new ProfileImg();

		// 업로드 할 파일이 없으면 메소드 종료
		if (file == null) {
			return profileImg;
		}

		String uploadFolder = "C:/team/upload"; // 업로드 기준경로

		File uploadPath = new File(uploadFolder, getFolder());

		// 프로필 사진일 경우(경로 변경)
		if (isProfileImg != null) {
			uploadFolder = "C:/team/upload/profile/" + id;
			uploadPath = new File(uploadFolder);
		}

		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		if (!file.isEmpty()) {
			String originalFilename = file.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String uploadFilename = uuid.toString() + "_" + originalFilename;

			File proFile = new File(uploadPath, uploadFilename); // 생성할 파일이름 정보

			file.transferTo(proFile);

			// 현재 업로드한 파일이 이미지 파일이면 썸네일 이미지를 추가로 생성하기
			File outFile = new File(uploadPath, "s_" + uploadFilename);

			Thumbnailator.createThumbnail(proFile, outFile, 200, 200); // 썸네일 이미지 파일 생성하기

			profileImg.setUuid(uuid.toString());
			profileImg.setUploadpath((isProfileImg != null) ? "profileImg" : getFolder());
			profileImg.setFilename(originalFilename);
			profileImg.setMemberId(id);
		}

		return profileImg;
	} // uploadProfile

	// 프로필 삭제 메소드 (회원수정 및 탈퇴에 사용)
	private void deleteProfile(ProfileImg profileImg, String isProfileImg) {

		if (profileImg == null) {
			return;
		}

		String basePath = "C:/team/upload";

		String uploadpath = basePath + "/" + profileImg.getUploadpath();

		if (isProfileImg != null) {
			basePath = "C:/team/upload/profile/" + profileImg.getMemberId();

		}

		String filename = profileImg.getUuid() + "_" + profileImg.getFilename();

		File file = new File(uploadpath, filename);

		if (file.exists() == true) { // 해당 경로에 첨부파일이 존재하면
			file.delete(); // 첨부파일 삭제하기
		}

		File thumbnailFile = new File(uploadpath, "s_" + filename);

		if (thumbnailFile.exists() == true) {
			thumbnailFile.delete();
		}

	} // deleteProfile

	// 년/월/일 형식의 폴더명 리턴하는 메소드
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String str = sdf.format(new Date());
		return str;
	} // getFolder

>>>>>>> master
}
