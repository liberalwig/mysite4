<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>



<body>
	<div id="wrap">
		<div id="header" class="clearfix">
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		</div>
		<!-- //header+ //nav -->

		<div id="container" class="clearfix">
			<jsp:include page="/WEB-INF/views/include/boardAside.jsp"></jsp:include>
			<!-- //boardAside -->

			<div id="content">
				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="list">
						<form action="${pageContext.request.contextPath}/board" method="get">
							<div class="form-group text-right">
								<input type="text">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.pMap.boardList}" var="vo">
									<tr>
										<td>${vo.no}</td>
										<td class="text-left"><a href="${pageContext.request.contextPath}/board?read&no=${vo.no}">${vo.title}</a></td>
										<td>${vo.name}</td>
										<td>${vo.hit}</td>
										<td>${vo.regDate}</td>
										<td><c:if test="${(sessionScope.authUser.no) == (vo.userNo)}">
												<a href="${pageContext.request.contextPath}/board?delete&no=${vo.no}">[삭제]</a>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<div id="paging">
							<ul>
								<c:if test="${requestScope.pMap.prev == true}">
									<li><a
										href="${pageContext.request.contextPath}/board/list2?crtPage=${requestScope.pMap.startPageBtnNo-1}}"
									>◀</a></li>
								</c:if>

								<!--  현재 페이지만 볼드 처리 -->
								<c:forEach begin="${requestScope.pMap.startPageBtnNo}" end="${pMap.endPageBtnNo}" step="1" var="page">
									<c:choose>
										<c:when test="${page == param.crtPage}">
											<li class="active"><a class="active"
												href="${pageContext.request.contextPath}/board/list2?crtPage=${page}"
											>${page}</a></li>
										</c:when>
										<c:otherwise>
											<li><a class="active" href="${pageContext.request.contextPath}/board/list2?crtPage=${page}">${page}</a></li>
										</c:otherwise>
									</c:choose>



								</c:forEach>

								<!-- ▶버튼 안 생기게 할 때 -->
								<c:if test="${requestScope.pMap.next == true}">
									<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${requestScope.pMap.endPageBtnNo+1}">▶</a></li>
								</c:if>

							</ul>

							<div class="clear"></div>
						</div>

						<c:if test="${!(empty sessionScope.authUser)}">
							<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
						</c:if>

					</div>
					<!-- //list -->

				</div>
				<!-- //board -->

			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>