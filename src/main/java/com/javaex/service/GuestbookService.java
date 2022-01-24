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
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> guestbookList = guestbookDao.getGuestbookList();
		
		return guestbookList;
	}
	
	public int guestbookInsert(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService : guestbookInsert()");
		
		int count = guestbookDao.personInsert(guestbookVo);
		System.out.println(count + "건의 방명록이 추가되었습니다.");
		
		return count;
	}
	
}