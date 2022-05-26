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
<jsp:include page="nav.jsp"></jsp:include>

	<div align="center" style="padding:150px">
	<%= session.getAttribute("name")%>님 안녕하세요<br><br>
	 	<button onclick="location.href='purchaseoklist.do'">주문정보 확인</button><br><br>
	<button onclick="location.href='cartTest.jsp'">장바구니에 제품 넣기 테스트</button><br><br>
	<form action="itemabout.do" method="post">
<input type="text" name="item_num" value="1">

<input type="submit" value="상품페이지로">
</form>
<button onclick="location.href='itemmain.do'">테스트 index</button><br><br>
	</div>
	
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>