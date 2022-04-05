<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.getAttribute('id') ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>fastcampus</title>
	<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
<div id="menu">
	<ul>
		<li id="logo">fastcampus</li>
		<li><a href="<c:url value='/'/>">Home</a></li>
		<li><a href="<c:url value='/board/list'/>">Board</a></li>
		<li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
		<li><a href="<c:url value='/register/add'/>">Sign in</a></li>
		<li><a href=""><i class="fa fa-search"></i></a></li>
	</ul>
</div>
<div style="text-align:center">
	<table border='1'>
	<c:forEach items="${list}" var="board">
		<tr>
			<td>${board.bno}</td>
			<td>${board.title}</td>
			<td>${board.content}</td>
			<td>${board.writer}</td>
			<td>${board.view_cnt}</td>
		</tr>
	</c:forEach>
	</table>
	<c:if test="${pageHandler.showPrev}">
		<a href="<c:url value="/board/list?page=${pageHandler.page-1}&pageSize=${pageHandler.pageSize}"/>"><c:out value="<"/></a>
	</c:if>
	<c:forEach var="num" begin="${pageHandler.beginPage}" end="${pageHandler.endPage}" >
		<a href="<c:url value="/board/list?page=${num}&pageSize=${pageHandler.pageSize}"/>"><c:out value="${num}"/></a>
	</c:forEach>
	<c:if test="${pageHandler.showNext}">
		<a href="<c:url value="/board/list?page=${pageHandler.page+1}&pageSize=${pageHandler.pageSize}"/>"><c:out value=">"/></a>
	</c:if>
</div>
</body>
</html>
