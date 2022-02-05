package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	// 갤러리_1> 업로드
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("GalleryController > upload()");

		String saveName = galleryService.upload(file);
		model.addAttribute("saveName", saveName);

		return "redirect:/";
	}

	// 갤러리_2> 갤러리 리스트 가져오기
	@RequestMapping("/list")
	public String form(Model model) {
		System.out.println("GalleryController > list()");

		List<GalleryVo> galleryList = galleryService.getList();
		model.addAttribute("galleryList", galleryList);

		return "gallery/list";
	}

	// 갤러리_3> 클릭한 사진 보기

	// 갤러리_4> 파일 삭제
	/*
	@RequestMapping("remove")
	public String delete(@RequestParam("no") int no) {
		System.out.println("GalleryController > delete()");
	
		String deleteDone = galleryService.remove(no);
		return deleteDone;
	}
	*/
	
}
