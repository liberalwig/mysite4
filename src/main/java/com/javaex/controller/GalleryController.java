package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	// 파일 업로드 폼
	@RequestMapping("/form")
	public String form() {
		System.out.println("GalleryController > form()");

		return "";
	}

	// 파일 업로드 처리
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("GalleryController > upload()");

		String saveName = galleryService.restore(file);
		model.addAttribute("saveName", saveName);

		return "fileupload/result";
	}

}
