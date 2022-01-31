<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- guestAside -->
<div id="container" class="clearfix">
	<div id="aside">
		<h2>방명록</h2>
		<ul>
			<li><a href="${pageContext.request.contextPath}/guest/addList" class="btn_s">일반방명록</a></li>
			<li><a href="${pageContext.request.contextPath}/api/guestbook/addList" class="btn_s">ajax방명록</a></li>
		</ul>
	</div>
	<!-- //guestAside-->