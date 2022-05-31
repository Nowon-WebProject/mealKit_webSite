<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이젠, 집에서 | 회원가입</title>
<link href="css/styles.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>

	<div align="center" style="padding: 150px">
		<%=session.getAttribute("name")%>님 안녕하세요<br> <br>
		<button onclick="location.href='orderOkList.do'">주문정보 확인</button>
		<br> <br>
		<button onclick="location.href='itemWriteGo.do'">제품등록</button>
		<br> <br>
	<form action="refundManage.do" method="post">
		<input type="submit" value="취소환불관리">
	</form>
	<form action="orderManage.do" method="post">
		<input type="submit" value="주문내역관리">
	</form>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>