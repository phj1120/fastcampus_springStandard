<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.getAttribute('id') ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<!DOCTYPE html>
<html>
<head>
	<style>
		table.boardTable {
			margin-left: auto;
			margin-right: auto;
			margin-top: 20px;
			border-collapse: collapse;
			text-align: left;
			line-height: 1.5;
		}
		table.boardTable tr th{
			padding: 10px;
			font-weight: bold;
			vertical-align: top;
			border: 1px solid #ccc;
			text-align: center;
		}
		table.boardTable tr th.longCols{
			 width: 350px;
			 padding: 10px;
			 font-weight: bold;
			 vertical-align: top;
			 border: 1px solid #ccc;
			 text-align: center;
		}
		table.boardTable tr td {
			width: 150px;
			padding: 10px;
			vertical-align: top;
			border: 1px solid #ccc;
			text-align: center;
		}
		table.boardTable tr td.longCols {
			padding: 10px;
			vertical-align: top;
			border: 1px solid #ccc;
			text-align: left;
		}
	</style>
	<meta charset="UTF-8">
	<title>ParkH</title>
	<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
<div id="menu">
	<ul>
		<li id="logo">ParkH</li>
		<li><a href="<c:url value='/'/>">Home</a></li>
		<li><a href="<c:url value='/board/list'/>">Board</a></li>
		<li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
		<li><a href=""><i class="fa fa-search"></i></a></li>
	</ul>
</div>
<div style="text-align:center">
	<table class="boardTable">
		<tr>
			<th class = "shortCols" scope="cols">bno</th>
			<th class = "shortCols" scope="cols">제목</th>
			<th class = "longCols" scope="cols">글</th>
			<th class = "shortCols" scope="cols">작성자</th>
			<th class = "shortCols" scope="cols">조회수</th>
		</tr>
	<c:forEach items="${list}" var="board">
		<tr class = "rows">
			<td>${board.bno}</td>
			<td>${board.title}</td>
			<td class="longCols" ><a href="<c:url value='/board/read?bno=${board.bno}&page=${pageHandler.page}&pageSize=${pageHandler.pageSize}' />">${board.content}</a></td>
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
