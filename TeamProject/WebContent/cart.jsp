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
input[type='number']{
    width: 45px;
    text-align:right;
} 


th, td{
text-align:center;
}

</style>
</head>
<body>


	<jsp:include page="nav.jsp"></jsp:include>
	<%
		if ((int) session.getAttribute("cartcnt") != 0) {
	%>
	<div align="center">
		<%=session.getAttribute("id")%>님의 장바구니입니다.


		<table border="1" bordercolor="orange" width="500">
			<tr style="text-align:center">
				<th></th>
				<th>상품정보</th>
				<th>판매가</th>
				<th>수량</th>
				<th></th>
			</tr>
			<c:set var="result" value="0" />
			<c:forEach var="cart" items="${clist}">
				<tr>
					<td>
					<input type="checkbox" name="checkBtn" id="checkBtn" value="${cart.cart_seq}" checked>
					</td>
					<td>${cart.product_name}</td>
					<td>&#92;<fmt:formatNumber value="${cart.product_price}"
							pattern="#,##0" /></td>
					<td>
					<form action="cartcntmodify.do" method="post">
					<input type="hidden" name="cart_seq" value="${cart.cart_seq}">
					<input type="number" min="1" max="10" name="product_cnt" size="2" value="${cart.product_cnt}">
					<input type="submit" value="변경">
					</form>
					</td>
					
					<c:set var="result"
						value="${result+(cart.product_price*cart.product_cnt)}" />
					<td>
					<form action="cartdelete.do" method="post">
					<input type="hidden" name="cart_seq" value="${cart.cart_seq}">
					<input type="submit" value="삭제">
					</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		&#92;<Strong><fmt:formatNumber value="${result}" pattern="#,##0" /></Strong>원 입니다.
	</div>
	
					
					<form action="purchase.do" method="post">
					<input type="hidden" name="userid" value="<%=session.getAttribute("id")%>">
					<input type="submit" value="구매하기">
					</form>
					
					<form action="cartdeleteall.do" method="post">
					<input type="hidden" name="userid" value="<%=session.getAttribute("id")%>">
					<input type="submit" value="전체 상품 삭제">
					</form>
					
	
	<%
		} else {
	%>
	<div>장바구니가 비었습니다.</div>
	</script> -->
	<%
		}
	%>
<label for="checkBtn">zz</label>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>