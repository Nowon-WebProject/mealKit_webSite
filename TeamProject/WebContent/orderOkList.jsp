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
.info table {
	border: 1px solid orange;
	text-align: center;
	width: 100%;
}

.info th {
	background-color: orange;
	border: 1px solid orange;
}

.info td {
	border: 1px solid orange;
}
</style>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
	<div class="info">
	<div style="width: 60%; margin-left: auto; margin-right: auto;">
	<%
	 String check = (request.getAttribute("olist").toString());
	if(!check.equals("[]")){
		
	%>
<br>
	<hr>
	<h2>나의 주문 내역</h2>
		<table>
			<tr>
				<th colspan="2">주문일자</th>
				<th>상품명</th> <!-- ~외 ~건 -->
				<th>총 금액</th> <!-- 배송비포함 -->
				<th>받으실 분</th>
				<th>배송지</th> <!-- 우편주소, 도로명~상세주소 -->
				<th>연락처</th> 
				<th>배송 상태</th> <!--  기본 결제완료 -->
				<th>배송 메세지</th>
				<th>공동 현관 비밀번호</th>
				<th>사용한 포인트</th>
				<th>상세보기</th>
			</tr>
			<c:forEach var="order" items="${olist}">
				<tr>
					<td>${order.order_num}</td>
					<td>${order.order_date}</td>
					<td>${order.order_name}</td>
					<td>&#92;<fmt:formatNumber value="${order.amount}" pattern="#,##0" /></td>
					<td>${order.deli_name}</td>
					<td>${order.deli_addr}</td>
					<td>${order.deli_phone}</td>
					<td>${order.deli_status}</td>
					<td>${order.deli_msg}</td>
					<td>${order.deli_pwd}</td>
					<td>${order.usePoint}</td>
					<td>
					<form action="refund.do" method="post">
					<input type="hidden" name="order_num" value="${order.order_num}">
					<input type="submit" value="상세보기">
					</form>
					</td>
				</tr>
				<tr>
				</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	<%}else{ %>
	<div align="center">
	<i style="font-size:200px;color:orange" class="bi-file-earmark-x-fill"></i>
	<div style="font-size:30px;color:gray">결제 내역이 없습니다.</div>
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