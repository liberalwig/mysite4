package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;

	// 갤러리_1> 업로드
	// <0>파일 업로드 세팅
	public String upload(MultipartFile file) {
		System.out.println("GalleryService > upload()");
		System.out.println(file.getOriginalFilename());
		String saveDir = "C:\\javaStudy\\upload";

		// <1>파일 관련 정보 추출_운영 내용
		// 원본파일 이름
		String orgName = file.getOriginalFilename();

		// 확장자
		String exName = orgName.substring(file.getOriginalFilename().lastIndexOf("."));

		// 저장 파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName);

		// 파일 패스
		String filePath = saveDir + "\\" + saveName;

		// 파일 사이즈
		long fileSize = file.getSize();

		// 파일 저장(사용자: 업로드)
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
	}

	// 갤러리_2> 갤러리 리스트 가져오기
	public List<GalleryVo> getList() {
		System.out.println("GalleryService > getList()");

		return galleryDao.selectList();
	}

	// 갤러리_3> 클릭한 사진 보기
	public GalleryVo read(int no) {
		System.out.println("GalleryService > getList()");

		return galleryDao.selectByNo(no);
	}

	// 갤러리_4> 파일 삭제
	/*
	public GalleryVo remove(int no) {
		System.out.println("GalleryService > remove()");

		return galleryDao.delete(no);
	}
	*/

}