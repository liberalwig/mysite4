<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- boardAside -->
<div id="aside">
	<h2>게시판</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/board/list" class="btn_s">일반게시판</a></li>
		<li><a href="">댓글게시판</a></li>
	</ul>
</div>
<!-- //boardAside-->