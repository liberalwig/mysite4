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
	
}