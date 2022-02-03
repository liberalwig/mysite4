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

		int count = sqlSession.insert("user.userInsert", userVo);
		System.out.println(count + "명의 회원이 추가되었습니다.");
		return count;
	}

	// 유저_4>회원정보 가져와서 세션에 저장하기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao > selectUser()");

		return sqlSession.selectOne("user.selectUser", userVo);
	}

	// 유저_5> 회원정보 가져와서 회원정보 수정품
	public UserVo selectUser(int no) {
		System.out.println("userDao > selectUserByNo()");

		return sqlSession.selectOne("user.selectUserByNo", no);
	}

	// 유저_6>회원정보 업데이트
	public int update(UserVo userVo) {
		System.out.println("UserDao > update()");

		int count = sqlSession.update("user.update", userVo);
		System.out.println(count + "건의 회원정보가 수정되었습니다.");
		return count;
	}

}
