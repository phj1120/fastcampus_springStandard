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
			width:300px;
			height:50px;
			font-size: 17px;
			border : none;
			border-radius: 5px;
			margin : 20px 0 30px 0;
		}
		#title {
			font-size : 50px;
			margin: 40px 0 30px 0;
		}
		#msg {
			height: 30px;
			text-align:center;
			font-size:16px;
			color:red;
			margin-bottom: 20px;
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
</div>
<%--<form action="<c:url value="/register/add"/>" method="post" onsubmit="return formCheck(this);">--%>
<form action="<c:url value="/register/add"/>" method="post" onsubmit="return formCheck(this);">
	<h3 id="title">Login</h3>
	<div id="msg">
		<c:if test="${not empty param.msg}">
			<i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>
		</c:if>
	</div>
	<input type="text" name="id" value="${cookie.id.value}" placeholder="아이디 입력" autofocus>
	<input type="password" name="pwd" placeholder="비밀번호">
	<input type="password" name="pwdCheck" placeholder="비밀번호 확인">
	<input type="text" name="name" placeholder="이름">
	<input type="text" name="email" placeholder="이메일 입력">
	<input type="text" name="birth" placeholder="2022-01-01">
	<div>
		<span style="margin: 5px"><input type="checkbox" name="sns" value="kakao">카카오톡</span>
		<span style="margin: 5px"><input type="checkbox" name="sns" value="fb">페이스북</span>
		<span style="margin: 5px"><input type="checkbox" name="sns" value="insta">인스타</span>
	</div>
<%--	<input type="hidden" name="toURL" value="${param.toURL}">--%>
	<button>회원가입</button>
</form>
</body>
</html>

<script>
	function formCheck(frm) {
		let msg ='';
		if(frm.id.value.length < 4) {
			setMessage('id는 4자 이상입니다.');
			return false;
		}
		if (frm.pwd.value.length < 8) {
			setMessage('password는 8자 이상입니다.');
			return false;
		}
		if(frm.pwd.value != frm.pwdCheck.value){
			setMessage('비밀번호가 일치 하지 않습니다.');
			return false;
		}
		if(frm.name.value.length == 0) {
			setMessage('이름을 입력해 주세요.');
			return false;
		}
		if(frm.email.value.length == 0) {
			setMessage('이메일을 입력해 주세요.');
			return false;
		}
		if(frm.birth.value.length == 0) {
			setMessage('생년월일을 입력해 주세요.');
			return false;
		}
		return true;
	}
	function setMessage(msg){
		document.getElementById("msg").innerHTML = ` ${'${msg}'}`;
	}
</script>