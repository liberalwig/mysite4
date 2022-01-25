package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	// 방명록_1>리스트 가져오기
	public List<GuestbookVo> getGuestbookList() {
		System.out.println("GuestbookDao > getGuestbookList()");

		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.selectList");

		return guestbookList;
	}

	// 방명록_2>추가
	public int guestbookInsert(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao > guestbookInsert()");

		int count = sqlSession.insert("guestbook.guestbookInsert", guestbookVo);
		System.out.println(count + "건 저장되었습니다.");
		return count;
	}

	// 방명록_3>삭제 폼: 없음

	// 방명록_4>삭제
	public int guestbookDelete(int no,  String password) {
		System.out.println("GuestbookDao > guestbookDelete()");
		
		Map<String, Object> guestbookMap = new HashMap<String, Object>();
		
		guestbookMap.put("no", no);
		guestbookMap.put("password", password);

		int count = sqlSession.delete("guestbook.guestbookDelete", guestbookMap);
		System.out.println(count + "건의 방명록이 삭제되었습니다.");
		return count;
	}

}
