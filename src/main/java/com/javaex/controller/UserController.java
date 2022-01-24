package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	// 필드
	@Autowired
	private UserService userService;

	// 메소드 일반
	// 유저_로그인폼
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserController > loginForm()");

		return "/user/loginForm";
	}

	// 유저_로그인 후 성공시 메인으로
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
				// 세션에 저장
			session.setAttribute("authUser", authUser);

			System.out.println("로그인에 성공했습니다.");
			return "redirect:/";
		}
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("UserController > logout()");

		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/";
	}

	// 유저_유저가입폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("UserController > joinForm()");

		return "/user/joinForm";
	}

	// 유저_회원가입 후 성공
	@RequestMapping(value = "join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(Model model) {
		System.out.println("UserController > join()");

		// 다오에서 리스트를 가져온다
		UserVo userVo = new UserVo();
		List<UserVo> userList = userService.getUserList(UserVo userVo);
		System.out.println(userList.toString());

		// 컨트롤러-->DS데이터를 보낸다(model)
		model.addAttribute("userList", userList);

		// jsp정보를 리턴한다(view)
		return "/user/joinOkForm";
	}

	// 유저_수정폼

	// 유저_수정
}
