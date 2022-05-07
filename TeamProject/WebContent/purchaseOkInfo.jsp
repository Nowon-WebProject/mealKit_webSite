<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>

	<div align="center">
	<h2>나의 주문 정보</h2>
		<table width="500" height="500">
			<tr>
				<th>주문번호</th>
				<th>주문일자</th>
				<th>총 금액</th>
				<th>배송지</th>
				<th>배송 상태</th>
			</tr>
			<c:forEach var="purchase" items="${plist}">
				<tr>
					<td>${purchase.purchase_seq}</td>
					<td>${purchase.purchase_date}</td>
					<td>&#92;<fmt:formatNumber value="${purchase.total_price}" pattern="#,##0" /></td>
					<td>${purchase.address}</td>
					<td>${purchase.delivery_status}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
