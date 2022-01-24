package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	// 방명록_리스트 가져오기
	public List<GuestbookVo> getGuestbookList() {
		System.out.println("GuestbookDao > getGuestbookList()");

		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.selectList");

		return guestbookList;
	}

	// 방명록_추가
	public int personInsert(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao > personInsert()");

		int count = sqlSession.insert("guestbook.insert", guestbookVo);
		System.out.println(count + "건 저장되었습니다.");
		return count;
	}

	// 방명록_삭제
	public int personDelete(int no) {
		System.out.println("GuestbookDao > personDelete()");

		int count = sqlSession.delete("guestbook.delete", no);
		System.out.println(count + "건 삭제되었습니다.");
		return count;
	}

}
