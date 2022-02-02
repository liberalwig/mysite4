package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	// 게시판_1>글 전체 가져오기 (리스트 출력할때)
	public List<BoardVo> getBoardList() {
		System.out.println("BoardService > list()"); 

		return boardDao.selectList();
	}

	// 게시판_2> 글 저장
	public int addBoard(BoardVo boardVo) {
		System.out.println("BoardService > addBoard()");

		return boardDao.insert(boardVo);
	}

	// 게시판_3> 글 1개 가져오기
	@Transactional
	public BoardVo getBoard(int no, String type) throws Exception {
		System.out.println("BoardService > getBoard()");

		if ("read".equals(type)) {// 읽기 일때는 조회수 올림
			boardDao.updateHit(no);
			BoardVo boardVo = boardDao.select(no);
			return boardVo;

		} else { // 수정 일때는 조회수 올리지 않음
			BoardVo boardVo = boardDao.select(no);
			return boardVo;
		}

	}
	
	// 게시판_4> 조회수 업데이트: 없음

	// 게시판_6> 글 수정
	public int modifyBoard(BoardVo boardVo) {
		System.out.println("BoardService > modifyBoard()");

		return boardDao.update(boardVo);
	}

	// 게시판_7> 글 삭제
	public int removeBoard(BoardVo boardVo) {
		System.out.println("BoardService > removeBoard()");

		return boardDao.delete(boardVo);
	}

}