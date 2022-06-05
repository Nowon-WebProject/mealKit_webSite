<%@page import="kr.co.EZHOME.dao.OrderDAO"%>
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
    width: 100%;
    border-top: 1px solid orange;
    border-collapse: collapse;
}

.info th {
	background-color: orange;
    border-bottom: 1px solid #444444;
    padding: 10px;
}

.info td {
    border-bottom: 1px solid #444444;
    padding: 10px;
}
</style>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
	<div class="info">
	<div style="width: 60%; margin-left: auto; margin-right: auto;">
	<%
		
		// 화면에 보여질 총 게시글 개수
		int pageSize = 10;
		String ps = request.getParameter("pageSize");
		if (ps == null)
			pageSize = 10;
		else
			pageSize = Integer.parseInt(ps);
			
		// 누른 페이지
		String pageNum = request.getParameter("pageNum");	
		
		// 처음엔 1페이지
		if (pageNum == null)
			pageNum = "1";

		// 현재 페이지 (누른 페이지 또는 1페이지)
		int currentPage = Integer.parseInt(pageNum);
		
		// 전체 글 개수
		int count = 0;
		OrderDAO odao = OrderDAO.getInstance();
		count = odao.orderCnt(session.getAttribute("userid").toString());
		
		// 페이지 숫자 세기
		int number = 0;
		

		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = (currentPage * pageSize);
		
		
	 String check = (request.getAttribute("olist").toString());
	if(!check.equals("[]")){


		
	%>
<br>
	<hr>
	<h2>나의 주문 내역</h2>
		<table>
			<tr>
				<th width="10%">주문번호/주문일자</th>
				<th width="10%"></th>
				<th width="30%">상품명</th> <!-- ~외 ~건 -->
				<th width="10%">결제 금액</th> <!-- 배송비포함 -->
				<th width="10%">배송 상태</th> <!--  기본 결제완료 -->
				<th width="5%">취소/환불</th>
			</tr>
			<c:forEach var="order" items="${olist}">
				<tr>
					<td height="100px">${order.order_date}<br><a href="orderInfo.do?order_num=${order.order_num}">${order.order_num}</a></td>
					<td><img src="upload/${order.item_pictureUrl1}" width="75px" height="75px">
					<td>${order.order_name}</td>
					<td>&#92;<fmt:formatNumber value="${order.amount}" pattern="#,##0" /><br>
					사용포인트${order.usePoint}p</td>
					<td>${order.deli_status}</td>
					<td>
					<form action="orderInfo.do" method="post">
					<input type="hidden" name="order_num" value="${order.order_num}">
					<input type="submit" name="check" value="취소/환불 요청">
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
	<hr>
	<br>
	        <div align="center">
        <h4>
		<%
			// 전체 페이지 개수 구하기
			// count: 전체 글 개수, pageSize: 화면에 보여질 총 게시글 개수
			int pageCount = count / pageSize + (count % pageSize == 0? 0:1);
		
			int startPage = 1;

			// 시작 페이지 구하기
			// currentPage: 현재 페이지
			if (currentPage % 10 != 0) {
				startPage = (currentPage / 10) * 10 + 1;
			} else {		
				startPage = (currentPage / 10 - 1) * 10 + 1;
			}
			
			// 끝 페이지 구하기
			int pageBlock = 10;
			int endPage = startPage + pageBlock - 1;
			
			if(endPage > pageCount)
				endPage = pageCount;
					
			// 아래는 페이지 표시 과정

			if (startPage > 10) {
		%>
		<a href="orderOkList.do?pageNum=<%=startPage - 10 %>&pageSize=<%=pageSize%>" style="color:black;"><i class="bi-chevron-compact-left"></i></a>
		<%
			}
			for (int i = startPage; i <= endPage; i++) {
				if(currentPage == i){
					%>
		<a href="orderOkList.do?pageNum=<%=i %>&pageSize=<%=pageSize%>" style="color:white;background-color:gray;border-radius:75px;text-decoration-line: none;">　<%=i %>　</a>
		<%
				}else{
					%>
		<a href="orderOkList.do?pageNum=<%=i %>&pageSize=<%=pageSize%>" style="color:black;text-decoration-line: none;">　<%=i %>　</a>
					
					<%
				}
			}
			if (endPage < pageCount) {
		%>
		<a href="orderOkList.do?pageNum=<%=startPage + 10 %>&pageSize=<%=pageSize%>" style="color:black;"><i class="bi-chevron-compact-right"></i></a>
		<%
			}
		%>
		</h4>
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
