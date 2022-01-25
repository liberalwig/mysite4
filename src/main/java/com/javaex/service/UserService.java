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
		System.out.println("UserService >  UserInsert()");

		int count = userDao.userInsert(userVo);

		return count;
	}

	// 유저_3>로그인폼: 없음
	
	// 유저_4>로그인 후 성공시 메인으로
	public UserVo login(UserVo userVo) {
		System.out.println("UserService > login()");

		UserVo authUser = userDao.selectUser(userVo);

		return authUser;
	}
}
