package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	// 유저_로그인
	public UserVo login(UserVo userVo) {
		System.out.println("UserService > login()");
		
		UserVo authUser = userDao.selectUser(userVo);
		
		return authUser;
	}

	// 유저_회원가입 후 성공
	public UserVo getUserList(UserVo userVo) {
		
		fdafdfdsafdafd
		
	}

	
}
