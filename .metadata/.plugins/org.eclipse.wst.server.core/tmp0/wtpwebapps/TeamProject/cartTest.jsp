<%@page import="java.text.DecimalFormat"%>
<%@page import="kr.co.EZHOME.dto.CartDTO"%>
<%@page import="kr.co.EZHOME.dao.CartDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.cart table {
	border: 1px solid orange;
	text-align: center;
	width: 100%;
}

.cart th {
	background-color: orange;
	border: 1px solid orange;
}

.cart td {
	border: 1px solid orange;
}
</style>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="cart">
		<div style="width: 60%; margin-left: auto; margin-right: auto;">
		<hr>
		<h2><i class="bi-cart-check-fill"></i> 장바구니 담기 테스트</h2>
	<form action="cartinsert.do" method="post">
	<table>
		<tr>
			<td>유저명</td>
			<td><input type="text" name="userid" size="20" value="<%=session.getAttribute("userid")%>" readonly></td>
		</tr>
		<tr>
			<td>제품명</td>
			<td><input type="text" name="item_name" size="20" value="맛있는 밀키트"></td>
		</tr>
		<tr>			
			<td>제품가격</td>
			<td><input type="text" name="item_price" size="20" value="7900"></td>
		</tr>
		<tr>			
			<td>제품수량</td>
			<td><input type="text" name="item_cnt" size="20" value="1"></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="확인" onclick="cartAlert()">
				<input type="reset" value="취소">
			</td>
		</tr>
		</table>
	</form>
	
	

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>