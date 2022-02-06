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

	/*
	 * // 보드>글 전체 가져오기 (검색기능 추가) public List<BoardVo> selectList2(String keyword) {
	 * System.out.println("boardDao/selectList2");
	 * 
	 * return sqlSession.selectList("board.selectList2", keyword); }
	 */

	// 게시판_2> 글 저장
	public int insert(BoardVo boardVo) {
		System.out.println("BoardDao > insert()");

		return sqlSession.insert("board.insert", boardVo);
	}

	// 게시판_3> 글 1개 가져오기
	public BoardVo select(int no) {
		System.out.println("BoardDao > select()");

		return sqlSession.selectOne("board.selcet", no);
	}

	// 게시판_4> 조회수 업데이트
	public int updateHit(int no) {
		System.out.println("BoardDao >updateHit()");

		return sqlSession.update("board.updateHit", no);
	}

	// 게시판_5> 글 전체 가져오기
	public List<BoardVo> selectList3(int startRnum, int endRnum, String keyword) {
		System.out.println("BoardDao > selectList3()");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		map.put("keyword", keyword);
		System.out.println(map.toString());

		return sqlSession.selectList("board.selectList3", map);
	}

	// 게시판_6> 글 수정
	public int update(BoardVo boardVo) {
		System.out.println("BoardDao > update()");

		return sqlSession.update("board.update", boardVo);
	}

	// 게시판_7> 글 삭제
	public int delete(BoardVo boardVo) {
		System.out.println("BoardDao > delete()");

		return sqlSession.delete("board.delete", boardVo);
	}

	// 게시판_8> 글 전체 카운트 매기기. 페이징
	public int selectTotal(String keyword) {
		System.out.println("BoardDao > selectTotal()");

		return sqlSession.selectOne("board.selectTotal", keyword);
	}

}