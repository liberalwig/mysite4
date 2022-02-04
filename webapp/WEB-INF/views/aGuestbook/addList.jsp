<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>




<body>

	<div id="wrap">

		<div id="header" class="clearfix">
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		</div>
		<!-- //head+//nav -->

		<div id="guestAside" class="clearfix">
			<jsp:include page="/WEB-INF/views/include/guestAside.jsp"></jsp:include>
			<!-- //guestAside -->

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
								<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
							</tr>
						</tbody>

					</table>
					<!-- //guestAdd -->

					<!--  </form>-->

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

	<!-- --------------------------------------------------------------------------------------------- -->
	<!-- 삭제 모달창 -->

	<div id="delModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">비밀번호 입력 모달창</h4>
				</div>
				<div class="modal-body">

					비밀번호: <input id="modalPassword" name="password" value=""><br> <input id="modalNo" type="text"
						name="no" value=""
					>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button id="modalBtnDel" type="button" class="btn btn-danger">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- //삭제 모달창 -->
</body>




<script type="text/javascript">
	// 로딩 되기 전 돔이 완성 됐을 때
	$(document).ready(function() {
		
		//리스트 그리기
		fetchList();
		console.log("리스트 요청");
	});

	// 저장 버튼 클릭될 때- 파라미터 방식 요청
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

		// 요청 - 파라미터 방식
		$.ajax({

			url : "${pageContext.request.contextPath }/api/guestbook/add",
			type : "post",
			//contentType : "application/json",
			data : guestbookVo,
			
			dataType : "json",
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

	
	/*
	// 저장 버튼이 클릭될 때 - json 방식 요청
	$("#btnSubmit2").on("click", function(){
		console.log("click");
		
	});
	*/
	
	// 요청 - json 방식
	$.ajax({

		url : "${pageContext.request.contextPath }/api/guestbook/add2",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(guestbookVo), //자바스크립트 객체를 json형식으로  변경
		
		dataType : "json",
		success : function(guestbookVo) {
			
			//성공시 처리해야될 코드 작성
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

	
	// 삭제 팝업버튼을 눌렀을 때(이벤트)
	$("#listArea").on("click", ".btnDelPop", function() {

		// 데이터 수집
		var $this = $(this);
		var no = $this.data("no");// 우리가 지은 이름으로 꺼내 쓰기
		console.log(no);

		// 초기화
		$("#modalPassword").val("");
		$("#modalNo").val(no);
		$("#delModal").modal('show');
	});

	
	// 모달창 삭제버튼 클릭했을 때
	$("#modalBtnDel").on("click", function() {
		console.log("모달창 삭제버튼 클릭");
	});

	// 데이터 수집	
	var no = $("#modalNo").val();
	var pw = $("#$modalPassword").val();

	var delInfoVo = {
			no: no,
			password: pw
	};
	
	console.log(delInfoVo);
	
	// 요청: no, password
	$.ajax({

		url : "${pageContext.request.contextPath }/api/guestbook/remove",
		type : "post",
		//contentType : "application/json",
		data : delInfoVo,
		
		dataType : "json",
		success : function(state) {
			console.log(state);
			
			if(state === 'success') {
				/*성공시 처리해야될 코드 작성*/
			
				// 해당 테이블html 삭제(=통째로 삭제된 것처럼 보여주기)
				$("#t" +no).remove();
				
				// 모달창 닫기
			$("#delModal").modal('hide');
				
			}
			else {
				$("#delModal").modal('hide');
				alert("비밀번호를 확인하세요");
			}
		
		},

		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});



	// 리스트 출력
	function fetchList() {

		$.ajax({//보낼 때
			url : "${pageContext.request.contextPath }/api/guestbook/list",
			type : "post", //post로 해도 주소창이 바뀌지 않음
			//contentType : "application/json",
			//data : {name: "홍길동"},

			dataType : "json",  // 받을 때
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
		str += ' <table id=t '+ guestbookVo.no  +' class="guestRead">';
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
		str += ' 		<td><button class="btnDelPop"  type="button" data-no=" ' + guestbookVo.no +' ">삭제</button></td>';
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