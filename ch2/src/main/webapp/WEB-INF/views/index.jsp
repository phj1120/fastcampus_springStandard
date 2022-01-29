<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:if test = '${not empty pageContext.request.session.getAttribute("id")}'>
	<c:set var = "loginOutLink" value = 'login/logout'/>
	<c:set var = "loginOut" value = "logout" />
</c:if>

<c:if test = '${empty pageContext.request.session.getAttribute("id")}'>
	<c:set var = "loginOutLink" value = 'login/login'/>
	<c:set var = "loginOut" value = "login" />
</c:if>


<%-- <%@ page session="true" %>

<c:set var = "loginOutLink" value ="${sessionScope.id==null ? '/login/login' : 'login/logout'}"/>
<c:set var = "loginOut" value ="${sessionScope.id==null?'login':'logout'}"/>
--%>

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
	    <li><a href="<c:url value="${loginOutLink}"/>">${loginOut}</a></li>    
	    <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
	    <li><a href=""><i class="fas fa-search small"></i></a></li>
	</ul> 
<p>session id : ${pageContext.request.session.getAttribute("id")}</p>

</div>
<div style="text-align:center">
	<h1>This is HOME</h1>
	<h1>This is HOME</h1>
	<h1>This is HOME</h1>
</div>
