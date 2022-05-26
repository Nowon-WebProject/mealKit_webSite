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
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(
		function() {
			var message = "${message}";
			if(message.length > 28 ){
				alert(message);
			}
		});
</script>
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
	border: 0;
}

.form-btn:hover {
	background-color: #FF9900;
	box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.5);
}
</style>

</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="cart">
		<div style="width: 60%; margin-left: auto; margin-right: auto;">
	<%
		if ((int) session.getAttribute("cartcnt") != 0) {
	%>
			<hr>
			<h2><i class="bi-cart-check-fill"></i> 장바구니</h2>
			<div align="right">
				<span style="color: black"><strong>01 장바구니 <i class="bi-caret-right-fill"></i></strong></span> 
				<span style="color: gray">02 주문서작성/결제 <i class="bi-caret-right"></i></span> 
				<span style="color: gray"> 03 주문완료 </span>
			</div>
			<table>
				<tr style="text-align: center">
					<th style="width: 75px">
						<!-- 이미지  -->
					</th>
					<th>상품정보</th>
					<th>판매금액</th>
					<th>수량</th>
					<th>합 금액</th>
					<th></th>
				</tr>
				<c:set var="result" value="0" />
				<c:forEach var="cart" items="${clist}">
					<tr>
						<td>
						<a href="itemabout.do?item_num=${cart.item_num}">
						<img alt="이미지" src="${cart.item_pictureUrl1}" width="75px" height="75px">
						</a>
						</td>
						<td>${cart.item_name}</td>
						<td><fmt:formatNumber value="${cart.item_price}" pattern="#,##0" />원</td>
						<td>
							<form action="cartcntmodify.do" method="post">
								<input type="hidden" name="cart_seq" value="${cart.cart_seq}">
								<input type="number" min="1" max="${cart.item_quantity}" name="item_cnt" size="2" value="${cart.item_cnt}">
								<input type="submit" value="변경">
							</form>
						</td>
						<td><fmt:formatNumber value="${cart.item_price*cart.item_cnt}" pattern="#,##0" />원</td>
						<c:set var="result" value="${result+(cart.item_price*cart.item_cnt)}" />
						<td>
							<form action="cartdelete.do" method="post">
								<input type="hidden" name="cart_seq" value="${cart.cart_seq}">
								<input type="submit" value="삭제">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<div align="center">
				<form action="cartdeleteall.do" method="post" align="right">
					<input type="hidden" name="userid" value="<%=session.getAttribute("userid")%>">
					<input type="submit" value="전체 상품 삭제">
				</form>
				<Strong><fmt:formatNumber value="${result}" pattern="#,##0" /></Strong>원 입니다.
				<form action="purchase.do" method="post">
					<input type="hidden" name="userid" value="<%=session.getAttribute("userid")%>">
					<input type="submit" class="form-btn" value="구매하기" onclick="check()">
				</form>
				<hr>
				${message}
			</div>
		</div>
	</div>
	<%
	
		} else {
	%>
		<div align="center">
			<i style="font-size:200px;color:orange" class="bi-cart-x-fill"></i>
			<div style="font-size:30px;color:gray">장바구니가 비어있습니다.</div>
	<%
		}
	%>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>