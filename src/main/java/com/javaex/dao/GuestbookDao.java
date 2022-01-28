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
	public int guestbookDelete(int no, String password) {
		System.out.println("GuestbookDao > guestbookDelete()");

		Map<String, Object> guestbookMap = new HashMap<String, Object>();

		guestbookMap.put("no", no);
		guestbookMap.put("password", password);

		int count = sqlSession.delete("guestbook.guestbookDelete", guestbookMap);
		System.out.println(count + "건의 방명록이 삭제되었습니다.");
		return count;
	}

	// 방명록_5>방명록 글 저장==>저장글 리턴:
	// Dao하나에 쿼리문 두 종류가 들어가면 논리상 틀리므로 서비스에 이양. 성공한 건수를
	public int insertSelectKey(GuestbookVo guestbookVo) {
		System.out.println("GustbookDao > addGuestResultVo()");
		System.out.println(guestbookVo); // 3개(:이름, 비번, 내용)

		return sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
	}

	// 방명록_6>숫자까지 담은 guestbookVo를 하나로 쳐서 뽑아내는 과정
	public GuestbookVo selectGuest(int num) {
		System.out.println("guestbookDao > selectGuest()");

		return sqlSession.selectOne("guestbook.selectByNo", num);
	}
}