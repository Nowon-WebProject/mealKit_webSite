<%@page import="kr.co.EZHOME.dto.UserDTO"%>
<%@page import="kr.co.EZHOME.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이젠, 집에서 | 회원가입</title>
<link href="css/styles.css" rel="stylesheet" />

</head>
<body>
<%
	String id=(String)session.getAttribute("id");
	UserDAO udao=UserDAO.getInstance();
	UserDTO udto=udao.getMember(id);
	
%>
<jsp:include page="nav.jsp"></jsp:include>
<jsp:include page="side.jsp"></jsp:include>
	
	<div align="center">
	<br>
	<br>
	<%= session.getAttribute("name")%>님 안녕하세요<br>
	
	<input type="submit" value="회원정보수정" onclick="location.href='modifyOK.jsp'">&nbsp;&nbsp;&nbsp;
	<input type="button" value="회원탈퇴"><a href = "delete.jsp"></a>
	
	</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>