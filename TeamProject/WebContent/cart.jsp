<%@page import="java.text.DecimalFormat"%>
<%@page import="kr.co.EZHOME.dto.CartDTO"%>
<%@page import="kr.co.EZHOME.dao.CartDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.cart table{
border:1px solid orange;
width:1000px;
text-align:center;

}

.cart th{
background-color:orange;
border:1px solid orange;
}
.cart td{
border:1px solid orange;
}

/* CSS RESET */
html, body {
	height: 100%;
}

* {
	padding: 0;
	margin: 0;
}

a {
	text-decoration: none;
}

li {
	list-style: none;
}

.full-bg {
	height: 100%;
}

.table {
	height: 100%;
	display: table;
	margin: 0 auto;
}

.table-cell {
	height: 100%;
	display: table-cell;
	vertical-align: middle;
}

.login-container {
	width: 1000px;
	background-color: #fff;
	padding: 70px 20px;
	box-sizing: border-box;
	
}

.login--title {
	width: 100%;
	text-align: center;
	font-size: 50px;
}

.form-btn {
	display: block;
	width: 30%;
	font-size: 16px;
	height: 40px;
	background-color: #fd7e14;
	color: #fff;
	box-sizing: border-box;
	margin: 5px 0;
	cursor: pointer;
	border:0;
}

.form-btn:hover {
	background-color: #FF9900;
	box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.5);
}
</style>

</head>
<body>


	<jsp:include page="nav.jsp"></jsp:include>

	<%
		if ((int) session.getAttribute("cartcnt") != 0) {
	%>
	<div align="center"  class="cart">

		<div class="login-container">
		<h2>장바구니</h2>
		<table border="1" bordercolor="orange">
			<tr style="text-align:center">
				<th style="width:75px"><!-- 이미지 --></th>
				<th>상품정보</th>
				<th>판매금액</th>
				<th>수량</th>
				<th>합 금액</th>
				<th></th>
			</tr>
			<c:set var="result" value="0" />
			<c:forEach var="cart" items="${clist}">
				<tr>
					<td><img alt="이미지" src="images/product/1.jpg" width="75px" height="75px"></td>
					<td>${cart.item_name}</td>
					<td><fmt:formatNumber value="${cart.item_price}"
							pattern="#,##0" />원</td>
					<td>
					<form action="cartcntmodify.do" method="post">
					<input type="hidden" name="cart_seq" value="${cart.cart_seq}">
					<input type="number" min="1" max="10" name="item_cnt" size="2" value="${cart.item_cnt}">
					
					<input type="submit" value="변경">
					</form>
					</td>
					
					<td>
					<fmt:formatNumber value="${cart.item_price*cart.item_cnt}"
							pattern="#,##0" />원
					</td>
					
					<c:set var="result"
						value="${result+(cart.item_price*cart.item_cnt)}" />
					<td>
					<form action="cartdelete.do" method="post">
					<input type="hidden" name="cart_seq" value="${cart.cart_seq}">
					<input type="submit" value="삭제">
					</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div>
			<Strong><fmt:formatNumber value="${result}" pattern="#,##0" /></Strong>원 입니다.
					<form action="cartdeleteall.do" method="post">
					<input type="hidden" name="userid" value="<%=session.getAttribute("userid")%>">
					<input type="submit" value="전체 상품 삭제">
					</form></div>
					<form action="purchase.do" method="post">
					<input type="hidden" name="userid" value="<%=session.getAttribute("userid")%>">
					<input type="submit" class="form-btn" value="구매하기">
					</form>
						</div>
	</div>
	

					
	
	<%
		} else {
	%>
	<div>장바구니가 비었습니다.</div>
	<%
		}
	%>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>