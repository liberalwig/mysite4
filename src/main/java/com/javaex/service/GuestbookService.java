package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	// 방명록_1>리스트 가져오기
	public List<GuestbookVo> getList() {
		List<GuestbookVo> guestbookList = guestbookDao.getGuestbookList();

		return guestbookList;
	}

	// 방명록_2>추가
	public int guestbookInsert(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService > guestbookInsert()");

		int count = guestbookDao.guestbookInsert(guestbookVo);
		System.out.println(count + "건의 방명록이 추가되었습니다.");

		return count;
	}

	// 방명록_3>삭제 폼: 없음

	// 방명록_4>삭제
	public int guestbookDelete(int no, String password) {
		System.out.println("GuestbookService > guestbookInsert()");

		int count = guestbookDao.guestbookDelete(no, password);
		return count;
	}

	// 방명록_5>방명록 글 저장==>저장글 리턴. 7> 넘버를 인서트해서 4개의 정보를 담은 Vo 하나를 셀렉하기.
	// Dao하나는 한 쿼리만 처리하도록
	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {
		System.out.println("GustbookService > addGuestResultVo()");

		// 저장하기
		int count = guestbookDao.insertSelectKey(guestbookVo);

		// 저장한 내용 화면에 그리기 위해 가져오기
		int no = guestbookVo.getNo();
		guestbookDao.selectGuest(no);
		return guestbookDao.selectGuest(no);
	}

}