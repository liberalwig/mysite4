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

	// 게시판_1> 리스트 가져오기
	public List<BoardVo> getBoardList() {
		System.out.println("BoardService > getBoarList()");

		return boardDao.selectList();
	}

	// 게시판_2> 리스트 가져오기 (+페이징)
	public List<BoardVo> getBoardList2(int crtPage) {
		System.out.println("BoardService > getBoardList2()");
		//////////////////////////////////////////////
		////////// 리스트 가져오기////////////
		////////////////////////////////////////////

		// 현재 페이지 처리 (음수 경계): 삼항연산자
		crtPage = (crtPage > 0) ? crtPage : (crtPage=1); 
		
		/* if(crtPage <= 0) {// 마이너스페이지를 요청할 시 1페이지 출력
			crtPage=1;
		} 	*/
				
		// 한 페이지에 넣을 글 갯수
		int listCnt = 10;

		// 시작글 번호: 1 구하기
		int startNum = (crtPage - 1) * listCnt + 1;

		// 끝글 번호
		int endNum = (crtPage) * listCnt;

		// 글 리스트 가져오기(+페이징)
		List<BoardVo> boardList =  boardDao.selectList2(startNum, endNum);
		
		return boardList;
	}

	
	
	// 게시판_3> 글 저장
	public int addBoard(BoardVo boardVo) {
		System.out.println("BoardService > addBoard()");

		// 페이징 데이터 추가123개
		for (int i = 1; i <= 123; i++) {
			boardVo.setTitle(i + "번째 게시글 제목입니다.");
			boardVo.setContent(i + "번째 게시글 내용입니다.");
			boardVo.setHit(0);
			boardVo.setUserNo(1);
			boardDao.insert(boardVo);
		}

		return 1;
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