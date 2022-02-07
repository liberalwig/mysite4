package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	// 게시판_1>글 전체 가져오기(리스트 출력할때)
	public List<BoardVo> selectList() {
		System.out.println("BoardDao > selectList()");

		return sqlSession.selectList("board.selectList");
	}

	// 게시판_2> 리스트 불러오기(+ 페이징)
	// 계산을 통해 발생한 애들이므로 Vo에 담을 순 있지만 파라미터론 못 불러 =>Map
	public List<BoardVo> selectList2(int startNum, int endNum) {

		System.out.println("BoardDao > selectList2()");
		System.out.println(startNum + "," + endNum);

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);

		List<BoardVo> boardList = sqlSession.selectList("board.selectList2", map);

		return boardList;
	}

	// 게시판_2-2> 게시글 전체 카운트 수 불러오기
	public int selectTotal() {
		System.out.println("BoardDao > selectTotal()");
				int count = sqlSession.selectOne("board.totalCnt");

		return count;
	}

	// 게시판_3> 글 1개 가져오기
	public BoardVo select(int no) {
		System.out.println("BoardDao > select()");

		return sqlSession.selectOne("board.selcet", no);
	}

	// 게시판_4> 글 저장
	public int insert(BoardVo boardVo) {
		System.out.println("BoardDao > insert()");

		return sqlSession.insert("board.insert", boardVo);
	}

	// 게시판_5> 글 삭제
	public int delete(BoardVo boardVo) {
		System.out.println("BoardDao > delete()");

		return sqlSession.delete("board.delete", boardVo);
	}

	// 게시판_6> 글 수정
	public int update(BoardVo boardVo) {
		System.out.println("BoardDao > update()");

		return sqlSession.update("board.update", boardVo);
	}

	// 게시판_4> 조회수 업데이트
	public int updateHit(int no) {
		System.out.println("BoardDao >updateHit()");

		return sqlSession.update("board.updateHit", no);
	}

	/*
	 * // 게시판_8> 글 전체 카운트 매기기. 페이징 public int selectTotal(String keyword) {
	 * System.out.println("BoardDao > selectTotal()");
	 * 
	 * return sqlSession.selectOne("board.selectTotal", keyword); }
	 */

	/*
	 * // 보드>글 전체 가져오기 (검색기능 추가) public List<BoardVo> selectList2(String keyword) {
	 * System.out.println("boardDao/selectList2");
	 * 
	 * return sqlSession.selectList("board.selectList2", keyword); }
	 */
}