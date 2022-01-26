<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.fastcampus.ch2.*" %>
<%
	Person person_LV = new Person();
	request.setAttribute("name", "남궁성");   
	request.setAttribute("person", person_LV);
	request.setAttribute("list", new java.util.ArrayList());
	
	pageContext.setAttribute("name", "박현준");
	pageContext.setAttribute("person", person_LV);
%>
<html>  
<head>   
	<title>EL</title>   
</head>  
<body>   
[EL 을 쓰면 더 간단하게 값을 가져올 수 있다.] <br>
person.getCar().getColor() <br>
< %= %> <br>
person_LV.getCar().getColor() : <%=person_LV.getCar().getColor()%> <br>
$ { } <br>
requestScope.person.getCar().getColor() : ${requestScope.person.getCar().getColor()} <br>
requestScope.person.car.color : ${requestScope.person.car.color} <br>  
<br>

[EL 을 쓰면 지역변수를 사용할 수 없어 저장소에 저장해야한다.]<br>
[EL 사용을 위해서는 저장소를 지정해줘야하는데 생략하면]<br>
[PageContext -> request ->  session -> appication 이 순서로 확인해 사용.]<br>
<br>
pageContext.getAttribute("name") : ${pageContext.getAttribute("name")} <br>
pageContext.name : 불가능<br>
<br>
requestScope.getAttribute("name") : 불가능 <br>
requestScope.name : ${requestScope.name} <br>
<br>
name : ${name} <br>
<br>

[pageContext.request 와 request 는 다르다.]<br>
[pageContext.request 는 pageContext 저장소에 있는 request 고]<br>
[requestScope 는 request 저장소다.]<br>
<br>

pageContext.request : ${pageContext.request.toString() }<br>
requestScpope : ${requestScope } <br>

<br>
pageContext.request.getParameter("id")  : ${pageContext.request.getParameter("id") }<br>
param.id  : ${param.id } <br>

<br>
color=${requestScope.person.car.color} <br>
name=${pageContext.getAttribute("name")} <br>

[EL은 문자열, 숫자 연산시 문자열이 숫자로 바뀐다]<br>
< %= %> <br>
"1"+1 : <%="1"+1%><br>
$ { } <br>
"1"+1 : ${"1"+1}<br>