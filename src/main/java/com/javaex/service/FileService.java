package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;

@Service
public class FileService {

	@Autowired
	private GalleryDao galleryDao;
	
	// <0>파일 업로드 세팅
	public String restore(MultipartFile file) {
		System.out.println("FileService > restore()");
		System.out.println(file.getOriginalFilename());
		String saveDir = "C:\\javaStudy\\upload";

		// <1>파일 관련 정보 추출_운영 내용
		// 원본파일 이름
		String orgName = file.getOriginalFilename();

		// 확장자 추출: 'index이후의 값을 가져와라'
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		// 저장파일 이름+시각
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName); // 생성된 UUID 형태를 String형으로 변환 필요

		// 파일 경로
		String filePath = saveDir + "\\" + saveName;

		// 파일 사이즈
		long fileSize = file.getSize();

		// <2> 파일을 하드디스크에 저장[업로드]
		try {
			byte[] fileData = file.getBytes();

			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// <3> DB에 저장
		galleryDao.insert(galleryVo);

		return saveName;

	}

}
