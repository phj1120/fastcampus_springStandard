<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loginId" value="${pageContext.request.getAttribute('id') ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>fastcampus</title>
	<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
	<style>
		* { box-sizing:border-box; }
		a { text-decoration: none; }
		form {
			width:400px;
			height:500px;
			display : flex;
			flex-direction: column;
			align-items:center;
			position : absolute;
			top:50%;
			left:50%;
			transform: translate(-50%, -50%) ;
			border: 1px solid rgb(89,117,196);
			border-radius: 10px;
		}
		input[type='text'], input[type='password'] {
			width: 300px;
			height: 40px;
			border : 1px solid rgb(89,117,196);
			border-radius:5px;
			padding: 0 10px;
			margin-bottom: 10px;
		}
		button {
			background-color: rgb(89,117,196);
			color : white;
			width:120px;
			height:40px;
			font-size: 17px;
			border : none;
			border-radius: 5px;
			margin : 20px 0 30px 0;
		}
		#title {
			font-size: 50px;
			margin: 40px 0 30px 0;
		}
	</style>
</head>
<body>
<div id="menu">
	<ul>
		<li id="logo">fastcampus</li>
		<li><a href="<c:url value='/'/>">Home</a></li>
		<li><a href="<c:url value='/board/list'/>">Board</a></li>
		<li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
		<li><a href=""><i class="fa fa-search"></i></a></li>
	</ul>
	<h2 id="title">게시물 읽기</h2>
	<form action="" id="form">
		<input type="text" name="bno" value="${boardDto.bno}" readonly="readonly">
		<input type="text" name="title" value="${boardDto.title}" readonly="readonly">
		<textarea name="content" readonly="readonly">${boardDto.content}</textarea>
		<button type="button" id="writeBtn" class="btn">등록</button>
		<button type="button" id="modifyBtn" class="btn">수정</button>
		<button type="button" id="removeBtn" class="btn">삭제</button>
		<button type="button" id="listBtn" class="btn">목록</button>
	</form>
</div>
</form>
</body>
<script>
	$(document).ready(function(){
		$('#listBtn').on("click", function (){
			// alert("listBtn clicked")
			location.href = "<c:url value='/board/list?page=${page}&pageSize=${pageSize}'/>";
		})
		$('#removeBtn').on("click", function (){
			$('#form').attr("action", "<c:url value='/board/remove'/>?page=${page}&pageSize=${pageSize}");
			$('#form').attr("method", "post");
			$('#form').submit();
		})
	})
</script>
</html>
