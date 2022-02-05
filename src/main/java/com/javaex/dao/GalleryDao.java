package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;

	// 갤러리_1> 업로드
	public int insert(GalleryVo galleryVo) {
		int count = sqlSession.insert("gallery.insert", galleryVo);
		System.out.println(count + "건이 업로드 되었습니다. [GalleryDao]");

		return count;
	}

	// 갤러리_2> 갤러리 리스트 가져오기
	public List<GalleryVo> selectList() {
		return sqlSession.selectList("gallery.selectList");
	}

	// 갤러리_3> 클릭한 사진 보기
	public GalleryVo selectByNo(int no) {
		return sqlSession.selectOne("gallery.selectByNo", no);
	}

	// 갤러리_4> 파일 삭제
	public int delete(int no) {
		int count = sqlSession.delete("gallery.delete", no);
		System.out.println(count + "건이 삭제되었습니다. [GalleryDao]");

		return count;
	}

}
