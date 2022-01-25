package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;
import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	// 유저_전체 리스트 가져오기
	public List<UserVo> getUser() {
		System.out.println("UserDao > getUser()");

		List<UserVo> userList = sqlSession.selectOne("user.getUser");

		return userList;
	}

	// 유저_2>회원가입
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao > userInsert()");

		int count = sqlSession.insert("user.insert", userVo);
		System.out.println(count + "명의 회원이 추가되었습니다.");
		return count;
	}
	
	// 유저_4>로그인 후 성공시 메인으로
	// 회원정보 수정 위해 하나 가져오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao > getUser()");

		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		System.out.println(authUser);

		return authUser;
	}

}
