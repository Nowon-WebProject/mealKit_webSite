<%@page import="dao.ItemDAO2"%>
<%@page import="dto.ItemVO2"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/shopping.css">
</head>
<body>
	<%
			int pageSize = 10;
		
			String pageNum = request.getParameter("pageNum");	

			if (pageNum == null)
				pageNum = "1";
			
			int count = 0;
			int number = 0;
			
			int currentPage = Integer.parseInt(pageNum);
			
			ItemDAO2 iDao2 = ItemDAO2.getInstance();
			count = iDao2.getAllCount();
			
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = (currentPage * pageSize);
			
			List<ItemVO2> vec =  iDao2.selectAllItems();
		
			number = count - (currentPage - 1) * pageSize;
	%>
	<div id="wrap" style="width: 700px" align="center">
		<h1>상품 목록-관리자 페이지</h1>
		<table class="list">
			<tr>
				<td colspan="11" style="border: white; text-align: right">
					<a href="itemWrite2.do">상품 등록</a>
				</td>
			</tr>
			<tr>
				<td>사진</td>
				<td>상품 번호</td>
				<td>카테고리</td>
				<td>상품명</td>
				<td>가격</td>
				<td>재고</td>
				<td>날짜</td>
				<td>인분</td>
				<td>조리 시간</td>
				<td>수정</td>
				<td>삭제</td>
			</tr>
			<c:forEach var="item" items="${itemList}">
				<tr>
					<td>
						<c:choose>
							<c:when test="${empty item.item_pictureUrl}">
								<img id="imgList" src="upload/noimage.gif">
							</c:when>
							<c:otherwise>
								<img id="imgList" src="upload/${item.item_pictureUrl}">
							</c:otherwise>
						</c:choose>
					</td>
					<td>${item.item_num}</td>
					<td>${item.item_category}</td>
					<td>${item.item_name}</td>
					<td>${item.item_price}원</td>
					<td>${item.item_quantity}</td>
					<td>${item.item_date}</td>
					<td>${item.item_total}</td>
					<td>${item.item_time}</td>
					<td><a href="itemUpdate2.do?item_num=${item.item_num}">상품 수정</a></td>
					<td><a href="itemDelete2.do?item_num=${item.item_num}">상품 삭제</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="11" style="border: white; text-align: right">
					<a href="itemNumReset.do">상품  번호 초기화(미완)</a>
				</td>
			</tr>
		</table>
		<br><br>
		<%
			int pageCount = count / pageSize + (count % pageSize == 0? 0:1);
			int startPage = 1;
			
			if (currentPage % 10 != 0) {
				startPage = (currentPage / 10) * 10 + 1;
			} else {
				startPage = (currentPage / 10 - 1) * 10 + 1;
			}
			
			int pageBlock = 10;
			int endPage = startPage + pageBlock - 1;
			
			if(endPage > pageCount)
				endPage = pageCount;
					
			if (startPage > 10) {
		%>
		<a href="itemList2.jsp?pageNum=<%=startPage - 10 %>">[이전]</a>
		<%
			}
			for (int i = startPage; i <= endPage; i++) {
		%>
		<a href="itemList2.jsp?pageNum=<%=i %>">[<%=i %>]</a>
		<%
			}
			if (endPage < pageCount) {
		%>
		<a href="itemList2.jsp?pageNum=<%=startPage + 10 %>">[다음]</a>
		<%
			}
		%>
	</div>
</body>
</html>