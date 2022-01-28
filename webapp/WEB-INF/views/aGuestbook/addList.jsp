<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js">
	
</script>

<body>
	<div id="wrap">

		<div id="header" class="clearfix">
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		</div>
		<!-- //head+//nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax 방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->


				<div id="guestbook">
					<!-- <form action="${pageContext.request.contextPath}/guest/add" method="get">-->
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-name">이름</label></th>
								<td><input id="input-name" type="text" name="name"></td>

								<th><label class="form-text" for="input-pass">패스워드</label></th>
								<td><input id="input-pass" type="password" name="password"></td>
							</tr>

							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>

							<tr class="button-area">
								<td colspan="4" class="text-center">
									<button id="btnSubmit" type="submit">등록</button>
								</td>
							</tr>
						</tbody>


					</table>
					<!-- //guestWrite -->
					</form>

					<div id="listArea"></div>

				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<!-- //footer -->

	</div>
</body>


<script type="text/javascript">
	// 로딩 되기 이전에 요청
	$(document).ready(function() {
		fetchList();
		console.log("리스트 요청");
	});

	// 저장 버튼 클릭될 때
	$("#btnSubmit").on("click", function() {
		console.log("클릭")

		// 폼에 데이터를 모아야 한다
		var name = $("#input-name").val(); // 이름
		var password = $("#input-pass").val(); //비번
		var content = $("[name='content']").val(); //내용

		// 객체로 묶어주기
		var guestbookVo = {
			name : name,
			password : password,
			content : content
		};

		// 확인
		console.log(guestbookVo);

		// 요청
		$.ajax({

			url : "${pageContext.request.contextPath }/api/guestbook/add",
			type : "post",
			//contentType : "application/json",
			data : guestbookVo,
			//dataType : "json",

			success : function(guestbookVo) {/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo, "up");

				$("#input-name").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");
			},

			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});

	// 리스트 출력
	function fetchList() {

		$.ajax({//보낼 때
			url : "${pageContext.request.contextPath }/api/guestbook/list",
			type : "get", //post로 해도 주소창이 바뀌지 않으므로 get 말고 그냥 두자
			//contentType : "application/json",
			//data : {name: "홍길동"},

			//dataType : "json", // 받을 때
			success : function(guestbookList) {// json==>js. 여기 변수 이름은 js용.
				console.log(guestbookList);

				for (var i = 0; i < guestbookList.length; i++) {
					render(guestbookList[i], 'down'); // 방명록리스트 그리는 함수(guestbookList) 만들기
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}

	function render(guestbookVo, updown) {// 위에 for문에서 i번 한 사람의 정보만 뽑기로 했으니 List보단  Vo
		var str = ' ';
		str += ' <table class="guestRead">';
		str += '		<colgroup> ';
		str += ' 			<col style="width: 10%">';
		str += ' 			<col style="width: 40%">';
		str += ' 			<col style="width: 40%">';
		str += '			<col style="width: 10%"> ';
		str += '		</colgroup> ';
		str += ' <tr>';
		str += '		<td>' + guestbookVo.no + '</td> ';
		str += '		<td>' + guestbookVo.name + '</td> ';
		str += '		<td>' + guestbookVo.regDate + '</td> ';
		str += ' 		<td><a href="${pageContext.request.contextPath}/guest/deleteForm?no=${vo.no}">[삭제]</a></td>';
		str += '</tr> ';
		str += '<tr> ';
		str += '		 <td colspan=4 class="text-left">' + guestbookVo.content
				+ '</td>';
		str += '</tr> ';
		str += ' </table>	';

		if (updown == 'down') {
			$("#listArea").append(str); //html로 하면 계속 마지막 내용으로 업데이트되어 덮여짐
		} else {
			$("#listArea").prepend(str);
		}

	};
</script>

</html>