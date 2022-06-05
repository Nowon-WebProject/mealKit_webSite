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
		<button onclick="location.href='orderOkList.do?pageSize=10&pageNum=1'">주문정보 확인</button>
		<br> <br>
		<button onclick="location.href='itemWriteGo.do'">제품등록</button>
		<br> <br>
		<button onclick="location.href='refundManage.do?pageSize=20&pageNum=1&category=&keyword='">취소환불 관리</button>
		<br> <br>
		<button onclick="location.href='orderManage.do?pageSize=10&pageNum=1&category=&keyword='">배송상태 관리</button>
	</div>



<i style="color:orange;" class="bi-star-fill"></i>
<select>
<option>5.0</option>
<option>4.5</option>
<option>4.0</option>
<option>3.5</option>
<option>3.0</option>
<option>2.5</option>
<option>2.0</option>
<option>1.5</option>
<option>1.0</option>
<option>0.5</option>
<option>0.0</option>
</select>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>