<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
.refund table {
	border: 1px solid orange;
	text-align: center;
	width: 100%;
}

.refund th {
	background-color: orange;
	border: 1px solid orange;
}

.refund td {
	border: 1px solid orange;
}

.form-input {
	width: 100%;
	padding: 10px 20px;
	font-size: 20px;
	outline: none;
	margin: 10px 0;
	border: 1px solid #efefef;
	box-sizing: border-box;
}

.form-input:focus {
	box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.1);
	border: none;
}

.form-input--title {
	width: 100%;
	display: block;
	margin: 5px 0;
	box-sizing: border-box;
}

.form-btn {
	border: 0;
	display: block;
	width: 100%;
	font-size: 16px;
	height: 40px;
	background-color: #fd7e14;
	color: #fff;
	box-sizing: border-box;
	margin: 5px 0;
	cursor: pointer;
}

.pageSize{
	border: 0;
	width: 100px;
	font-size: 16px;
	height: 30px;
	background-color: #fd7e14;
	color: #fff;
	box-sizing: border-box;
	margin: 5px 0;
	cursor: pointer;
}


}

.form-btn:hover {
	background-color: #FF9900;
	box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.5);
}
</style>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="refund">
		<div style="width: 60%; margin-left: auto; margin-right: auto;">
			<br>
			결제번호 : <h2>${olist[0].order_num}</h2><br>
			배송 상태 :	<h3>${olist[0].deli_status}</h3>
			<hr>
			<br>
			<form action="refundRequest.do" method="post">
				<table>
					<tr>
						<th></th>
						<th>상품명</th>
						<th>상품가격</th>
						<th>상품갯수</th>
						<th>환불/취소여부</th>
					</tr>
					<c:forEach var="list" items="${olist}">
						<tr>
							<c:choose>
								<c:when test="${list.refund_status eq '미신청'}">
									<td><input type="checkbox"
										value="${list.item_num}/${list.item_name}/${list.item_price}/${list.item_cnt}/${list.deli_status}"
										name="test[]"></td>
								</c:when>
								<c:otherwise>
								
									<td><input disabled type="checkbox" value="" name="test[]"></td>
								</c:otherwise>
							</c:choose>
							<td>${list.item_name}</td>
							<td>${list.item_price}</td>
							<td>${list.item_cnt}</td>
							<td>${list.refund_status}</td>
						</tr>
					</c:forEach>
				</table>
				<br>
				<br>
				<input type="hidden" name="order_num" value="${olist[0].order_num}">
				<input type="submit" class="form-btn" value="신청">
			</form>
			<br> 결제완료 : 별도 과정 없이 즉시 취소가 가능합니다. <br> 배송중 : 관리자의 승인이 필요합니다.
			<br> 배송완료 : 관리자의 승인이 필요합니다. <br>
		</div>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>