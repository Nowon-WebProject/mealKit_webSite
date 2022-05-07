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

<table align="center" width="500" height="300">
			<tr>
				<th>상품명</th>
				<th>상품가격</th>
				<th>상품수량</th>
			</tr>
			<c:set var="result" value="0" />
			<c:forEach var="cart" items="${clist}">
				<tr>
					<td>${cart.product_name}</td>
					<td>&#92;<fmt:formatNumber value="${cart.product_price}"
							pattern="#,##0" /></td>
					<td>${cart.product_cnt}</td>
				</tr>
				<c:set var="result"
						value="${result+(cart.product_price*cart.product_cnt)}" />
			</c:forEach>
		</table>
		
		
				&#92;<Strong><fmt:formatNumber value="${result}" pattern="#,##0" /></Strong>원 입니다.
				
		<form action="purchaseok.do" method="post">
			<input type="hidden" name="userid" value="<%=session.getAttribute("id")%>">
			<input type="hidden" name="total_price" value="${result}">
			<input type="hidden" name="address" value="주소값<%-- <%=session.getAttribute("address")%> --%>">
			<input type="hidden" name="delivery_status" value="결제 완료">
			<input type="submit" value="결제">
		</form>
		
		

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>