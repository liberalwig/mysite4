package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.PersonVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	// 필드
	@Autowired
	private UserService userService;

	// 메소드 일반
	// 유저_1>회원가입폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("UserController > joinForm()");

		return "/user/joinForm";
	}

	// 유저_2>회원가입
	@RequestMapping(value = "join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController > join()");

		userService.userInsert(userVo);

		return "redirect:/";
	}

	// 유저_3>로그인폼
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserController > loginForm()");

		return "/user/loginForm";
	}

	// 유저_4>로그인 후 성공시 메인으로
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController > login()");

		// 다오에서 리스트를 가져온다
		UserVo authUser = userService.login(userVo);
		System.out.println(authUser);

		if (authUser == null) {// 로그인 실패
			System.out.println("로그인에 실패했습니다.");

			return "redirect:/user/loginForm?result=fail";
		}

		else {// 로그인 성공
			session.setAttribute("authUser", authUser);

			System.out.println("로그인에 성공했습니다.");
			return "redirect:/";
		}
	}

	// 유저_5>로그아웃
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("UserController > logout()");

		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/";
	}

	// 유저_6>회원정보 수정폼
	@RequestMapping(value = "/modifyForm", method = {RequestMethod.GET, RequestMethod.POST })
	private String delete(@RequestParam("no")int no,
											@RequestParam("password")String password, Model model) {
		System.out.println("UserController > guestbookModifyForm()");

		UserVo autthUser = userService.userModify(userVo);

		model.addAttribute("UserVo", userVo);

		return "modifyForm";
	}

	// 유저_7>회원정보 수정
}
