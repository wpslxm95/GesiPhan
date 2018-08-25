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
						href="<c:url value='/TeamStudy/Free/FreeHome.kosmo'/>">HOME</a></li>
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
			<h1>
					Free Board<small>Write</small>
				</h1>
		<table class="table table-bordered">
			
    
        <form method="post"  action="<c:url value='/FreeBoard/FreeWrite.kosmo'/>" encType="multiplart/form-data">
            <tr>
                <th>제목: </th>
                <td><input type="text" placeholder="제목을 입력하세요. " name="name" class="form-control"/></td>
            </tr>
            <tr>
                <th>카테고리: </th>
                <td><select name="category">
								<option value="ca1">ca1</option>
								<option value="ca2">ca2</option>
								<option value="ca3">ca3</option>
								<option value="ca4">ca4</option>
				</select></td>
                
            </tr>
              <tr>
                <th>작성자: </th>
                <td><input type="text" placeholder="제목을 입력하세요. " name="title" class="form-control"/></td>
            </tr>
              <tr>
                <th>비밀번호: </th>
                <td><input type="password" placeholder="비밀번호을 입력하세요. " name="pass" class="form-control"/></td>
            </tr>
            <tr>
                <th>내용: </th>
                <td><textarea cols="10" placeholder="내용을 입력하세요. " name="content" class="form-control"></textarea></td>
            </tr>
         
            <tr>
                <td colspan="2">
                   
                   
                   <button type="submit" class="btn btn-danger">등록</button>
                    <!-- <a class="btn btn-default" type="reset"> reset </a>
                    <a class="btn btn-default" onclick="javascript:location.href='list.jsp'">글 목록으로...</a> -->
                </td>
            </tr>
        </form>
   
</table>
</div>



		<!-- ---------------------------------------------------------- -->
		</div>
</body>
</html>