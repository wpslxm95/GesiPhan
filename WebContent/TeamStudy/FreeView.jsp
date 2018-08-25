<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Index.jsp</title>
<link href="<c:url value='/BootStrap/css/bootstrap.min.css'/>"
	rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/BootStrap/js/bootstrap.min.js'/>"></script>

</head>
<style>
body {
	padding-right: 10px;
	padding-left: 10px;
}

* {
	margin: 0;
	padding: 0;
	font-family: Verdana;
	padding-right: 10px;
	padding-bottom: 10px;
}

.header {
	/* 	border-right: 2px GRAY solid; */
	padding-top: 50px;
	padding-right: 10px;
	padding-bottom: 5px;
	width: 100%;
	height: 100%;
	line-height: 100%;
}

.header div.logo {
	display: inline;
	/* border: 2px RED solid; */
}

.header div.logo img {
	width: 200px;
	height: 120px;
	padding-left: 20px;
}

.header div.topMenu li {
	display: inline;
	/* border: 2px RED solid; */
	/* border-right: 1px gray solid; */
	padding-right: 5px;
	font-size: 25px;
}

.header div.topMenu {
	text-align: right;
}

div.all {
	clear: both;
}

.all div.header {
	width: 15%;
	float: left;
}

.all div.header ul {
	list-style-type: none;
	margin: 10px 0 0 10px;
}

.all div.header ul li {
	padding: 10px 0 0 10px
}

.center {
	padding-top: 30px;
	padding-right: 50px;
	padding-left: 100px;
}
</style>
<script>
	$(function() {
		$(".navbar-right li a").click(function() {
			$('.navbar-right li').each(function() {
				$(this).removeClass("active");
			});
			$(this).parent().addClass("active");

		});
	});
</script>
<script>
	$(function() {
		$("#tabMenu a").click(function() {
			$(this).tab('show');
			if ($(this).html() == "수정") {
				console.log("수정 클릭");
				$("input[name=mode]").val('UPDATE');
				$(".modal-title").html("수정용 비밀번호 입력창");
			} else if ($(this).html() == "삭제") {
				console.log("삭제 클릭");
				if (confirm("정말로 삭제 하시겠습니까?")) {
					$(".modal-title").html("삭제용 비밀번호 입력창");
					$("input[name=mode]").val('DELETE');
					$("#passwordModal").modal("show");
				}////			
			}////
		});
	});
</script>
<body>
	<div class="all">
		<!-- 로고및 헤드 시작 -->
		<div class="header" align="left">
			<div class="logo" align="right">
				<img src='<c:url value="/TeamStudy/praclogo.png"/>' alt="logoImage" />
			</div>
			<div class="topMenu">
				<%-- <ul>
    				<li <c:if test="${active=='HOME' }">class="active"</c:if>><a href="<c:url value='/TeamStudy/Free/FreeHome.kosmo'/>">HOME</a></li>
					<li <c:if test="${active=='FREEBOARD' }">class="active"</c:if>><a href="<c:url value='/DataRoom/List.kosmo'/>">Free Board</a></li>
      				<li><a href="#">Q&A</a></li>
				</ul> --%>
				<ul class="nav nav-pills nav-stacked">
					<li <c:if test="${active=='HOME' }">class="active"</c:if>><a
						href="<c:url value='/FreeBoard/FreeHome.kosmo'/>">HOME</a></li>
					<li <c:if test="${active=='FREEBOARD' }">class="active"</c:if>><a
						href="<c:url value='/FreeBoard/FreeList.kosmo'/>">Free Board</a></li>
					<li><a href="#">Q&A</a></li>
				</ul>
			</div>
		</div>
		<!-- 로고및 헤드 끝 -->
		<!-- ---------------------------------------------------------- -->
		<!-- 내용 시작 -->
		<div class="container">
			<div class="page-header">
				<h1>
					FreeBoard<small>View</small>
				</h1>
			</div>

			<div class="row">
				<!-- 테이블전체 가로폭은 테이블을 감싸는  div에 col-*-*로 조정 -->
				<div class="col-md-11">
					<table class="table table-hover table-striped table-bordered">
						<tr>
							<th class="col-md-2 text-center">번호</th>
							<td>${record.no}</td>
						</tr>
						<tr>
							<th class="col-md-2 text-center"> 카테고리</th>
							<td>${record.category}</td>
						</tr>
						<tr>
							<th class="col-md-2 text-center">제목</th>
							<td>${record.title}</td>
						</tr>
						<tr>
							<th class="col-md-2 text-center">작성자</th>
							<td>${record.name}</td>
						</tr>
						<tr>
							<th class="col-md-2 text-center">등록일</th>
							<td>${record.enterDate}</td>
						</tr>
						<tr>
							<th colspan="2" class="text-center">내용</th>
						</tr>
						<tr>
							<td colspan="2">${record.content}</td>
						</tr>

					</table>
				</div>
			</div>
			<!-- row -->
			<div class="row">
				<div class="col-md-11">
					<!-- .center-block 사용시 해당 블락의 크기를 지정하자 -->
					<ul id="tabMenu" class="nav nav-pills center-block"
						style="width: 195px;">
						<li><a href="#" data-toggle="modal"
							data-target="#passwordModal">수정</a></li>
						<!-- 삭제 취소시에는 모달창이 뜨지 않도록 자스로 제어하기 의해:data-toggle="modal" 제거 -->
						<li><a href="#" data-target="#passwordModal">삭제</a></li>

						<li><a href="<c:url value='/FreeBoard/FreeList.kosmo'/>">목록</a></li>
					</ul>
				</div>
			</div>

			<!-- ---------------------------------------------------------- -->
		</div>  

		</div>
</body>
</html>