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
			width:900px;
			height:600px;
			display : flex;
			flex-direction: column;
			position : absolute;
			top:50%;
			left:50%;
			transform: translate(-50%, -50%) ;
			/*align-items:center;*/
			border: 1px solid rgb(89,117,196);
			border-radius: 10px;
		}
		input[type='text']{
			width: 500px;
			height: 30px;
			padding: 0 10px;
		}
		textarea[name='content'] {
			width: 800px;
			height: 300px;
			margin: 10px 0 0 20px;
			resize: none;
			padding: 10px 10px 10px 10px;
			border : 1px solid rgb(89,117,196);
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
			font-size: 30px;
			float: left;
			margin: 20px 20px 20px 20px;
		}
		span[class="rows"] {
			margin: 5px 0 0 20px;
			font-weight: bold;
		}
		.rowsRead{
			margin: 5px 0 0 20px;
			border: none;
		}
		.rowsModify{
			margin: 5px 0 0 20px;
			border : 1px solid rgb(89,117,196);
			border-radius: 5px;
		}
		span[class="btn"] {
			margin: 5px 0 0 20px;
		}
	</style>
</head>
<body>
<div id="menu">
	<ul>
		<li id="logo">parkh</li>
		<li><a href="<c:url value='/'/>">Home</a></li>
		<li><a href="<c:url value='/board/list'/>">Board</a></li>
		<li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
		<li><a href=""><i class="fa fa-search"></i></a></li>
	</ul>
</div>
<form action="" id="form">
	<h1 id="title">게시물 쓰기</h1>
	<span class="rows">작성자<input type="text" class="rowsRead" name="writer" readonly="readonly" value="${sessionScope.id}"/></span>
	<span class="rows">제목<input type="text" class="rowsModify" name="title" value="${boardDto.title}"></span>
	<textarea name="content">${boardDto.content}</textarea>
	<span class="btn">
		<button type="button" id="writeBtn" class="btn">작성</button>
		<button type="button" id="listBtn" class="btn">목록</button>
	</span>
</form>
</body>
<script>
	$(document).ready(function(){
		$('#listBtn').on("click", function (){
			// alert("listBtn clicked")
			location.href = "<c:url value='/board/list?page=${page}&pageSize=${pageSize}'/>";
		})
		$('#writeBtn').on("click", function (){
			let form = $('#form')
			form.attr("action", "<c:url value='/board/write'/>?page=${page}&pageSize=${pageSize}");
			form.attr("method", "post");
			form.submit();
		})
	})
</script>
</html>
