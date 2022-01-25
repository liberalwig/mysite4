package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guest")
public class GuestbookController {

	// 필드
	@Autowired
	public GuestbookService guestbookService;

	// 메소드 일반

	// 방명록_1>리스트 가져오기
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {
		System.out.println("GuestbookController > addList()");

		List<GuestbookVo> guestbookList = guestbookService.getList();
		System.out.println(guestbookList);

		model.addAttribute("guestbookList", guestbookList);

		return "/guestbook/addList";
	}

	// 방명록_2>추가
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController > add() ");

		guestbookService.guestbookInsert(guestbookVo);

		return "redirect:/guest/addList";
	}

	// 방명록_3>삭제 폼
	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	private String deleteForm(@RequestParam("no") int no){
		System.out.println("GuestbookController > deleteForm()");

		return "/guestbook/deleteForm";
	}

	// 방명록_4>삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	private String delete(@RequestParam("no")int no,
											@RequestParam("password")String password) {
		System.out.println("GuestbookController > guestbookdelete()");

		guestbookService.guestbookDelete(no, password);

		return "redirect:guest/addList";
	}

}