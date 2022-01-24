package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	// 전체 리스트 가져오기
	public List<UserVo> getUser() {
		System.out.println("UserDao > getUser()");

		List<UserVo> userList = sqlSession.selectOne("user.getUser");

		return userList;
	}

	// 회원정보 수정 위해 하나 가져오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao > getUser()");

		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		System.out.println(authUser);

		return authUser;
	}

}
