package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Map<String, Object> getBoardList2(int crtPage) {
		System.out.println("BoardService > getBoardList2()");
		//////////////////////////////////////////////
		////////// 리스트 가져오기////////////
		////////////////////////////////////////////

		// 전체 글 갯수 가져오기
		int totalCnt = boardDao.selectTotal();
		System.out.println("totalCnt = " + totalCnt);

		// 한 페이지에 넣을 글 갯수
		int listCnt = 10;

		// 현재 페이지 처리 (음수 경계): 삼항연산자
		crtPage = crtPage > (totalCnt / listCnt) || (crtPage < 0) ? (crtPage = 1) : crtPage;

		// if(crtPage <= 0) {// 마이너스페이지를 요청할 시 1페이지 출력 crtPage=1; }

		// 시작글 번호: 1 구하기
		int startNum = (crtPage - 1) * listCnt + 1;

		// 끝글 번호
		int endNum = (crtPage) * listCnt;

		// 글 리스트 가져오기(+페이징)
		List<BoardVo> boardList = boardDao.selectList2(startNum, endNum);

		//////////////////////////////////////////////
		///////////// 페이징 버튼///////////////
		////////////////////////////////////////////

		// 전체 글 개수 가져오기: 위 참고

		// 페이지당 드러낼 버튼 개수
		int pageBtnCount = 5;

		// 마지막 버튼 번호
		// startpageBtnCount + (pageBtnCount-1) = lastpageBtnCount
		// 1 1~5
		// 2 1~5
		int endPageBtnNo = (int) (Math.ceil(crtPage / (double) pageBtnCount)) * pageBtnCount;

		// 시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);

		// 다음 화살표 유무
		boolean next = false;
		if (endPageBtnNo * listCnt < totalCnt) {
			next = true;
		} else { // 다음 화살표가 안 보이면 마지막 버튼 값을 다시 계산
			endPageBtnNo = (int) Math.ceil(totalCnt / (double) listCnt);
		}

		// 이전 화살표 유무: 현 페이지가 1쪽이 아니기만 하면 이전페이지 갈 수 있는 화살표 나와야 함
		boolean prev = false;
		if (startPageBtnNo != 1) {
			prev = true;
		}

		//////////////////////////////////////////////
		///////// Map에 담는 포장////////////
		////////////////////////////////////////////
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("next", next);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("boardList", boardList);

		return pMap;
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