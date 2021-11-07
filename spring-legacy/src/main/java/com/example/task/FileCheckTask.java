package com.example.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.domain.AttachVO;
import com.example.service.AttachService;

@Component
public class FileCheckTask {

	@Autowired
	private AttachService attachService;

	@Scheduled(cron = "0/5 * * * * ?")
	public void checkFiles() {
		System.out.println("===============================");
		System.out.println("checkFiles() task run......");
		System.out.println("===============================");

		Calendar cal = Calendar.getInstance(); // 현재날짜시간 정보를 가진 Calendar 객체 가져오기
		cal.add(Calendar.DATE, -1); // 현재날짜시간에서 하루 빼기

		// 어제날짜 년월일 폴더경로 구하기
		Date yesterdayDate = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = sdf.format(yesterdayDate);

		String path = "C:/team/upload/" + strDate;
		System.out.println("path : " + path);

		// 어제날짜 경로를 파일객체로 준비
		File dir = new File(path);
		// 어제날짜 경로 폴더안에 있는 파일목록을 배열로 가져오기
		File[] files = dir.listFiles();
		// 배열을 리스트로 변환하기
		List<File> fileList = Arrays.asList(files);

		// DB에서 년월일 경로에 해당하는 첨부파일 리스트 가져오기
		List<AttachVO> attachList = attachService.getAttachesByUploadpath(strDate);

		// fileList에서 파일명만 가지는 filenameList 만들기
		List<String> filenameList = new ArrayList<>();
		for (File file : fileList) {
			filenameList.add(file.getName());
		}

		// attachList에서 파일명만 가지는 dbFilenameList 만들기
		List<String> dbFilenameList = new ArrayList<>();
		for (AttachVO attach : attachList) {
			dbFilenameList.add(attach.getUuid() + "_" + attach.getFilename());

			if (attach.getFiletype().equals("I")) {
				dbFilenameList.add("s_" + attach.getUuid() + "_" + attach.getFilename());
			}
		}

		for (String filename : filenameList) { // 실제 폴더에 있는 파일
			// 실제 파일이 DB에서 가져온 목록에 포함되어 있지 않으면
			if (dbFilenameList.contains(filename) == false) {
				File delFile = new File(path, filename);

				if (delFile.exists()) { // 해당 파일 존재하면
					delFile.delete();

					// 파일명 출력하기
					System.out.println(delFile.getName() + " 파일 삭제됨..");
				}
			}
		} // for
	} // checkFiles
}
