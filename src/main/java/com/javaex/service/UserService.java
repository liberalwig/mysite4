package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	// 유저_1>회원가입 폼: 없음

	// 유저_2>회원가입
	public int userInsert(UserVo userVo) {
		System.out.println("UserService >  userInsert()");

		int count = userDao.userInsert(userVo);

		return count;
	}

	// 유저_4>로그인 후 성공시 메인으로
	public UserVo login(UserVo userVo) {
		System.out.println("UserService > login()");

		UserVo authUser = userDao.selectUser(userVo);

		return authUser;
	}

	// 유저_5>로그아웃

	// 유저_6>회원정보 수정폼
	public UserVo modifyForm(int no) {
		System.out.println("UserService > modifyForm()");

		return userDao.selectUser(no);
	}

	// 유저_7>회원정보 수정
	public int modify(UserVo userVo) {
		System.out.println("UserService > modify()");

		return userDao.update(userVo);
	}
}
