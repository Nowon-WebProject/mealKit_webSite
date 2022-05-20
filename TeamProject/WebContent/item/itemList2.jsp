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
				// 화면에 보여질 총 게시글 개수
				int pageSize = 0;
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
				// 페이지 숫자 세기
				int number = 0;
				
				ItemDAO2 iDao2 = ItemDAO2.getInstance();
				count = iDao2.getAllCount();
				
				// 지금 페이지에 보여질 시작 번호와 끝 번호
				// ex.
				// 1p 2p 3p 4p
				// 1  11 21 31 ...
				// 10 20 30 40 ...
				int startRow = (currentPage - 1) * pageSize + 1;
				int endRow = (currentPage * pageSize);
	
				List<ItemVO2> list = iDao2.selectAllItems(startRow, endRow);
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
			<c:forEach var="item" items="<%=list %>">
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
		<a href="itemList2.do?pageNum=<%=startPage - 10 %>&pageSize=<%=pageSize%>">[이전]</a>
		<%
			}
			for (int i = startPage; i <= endPage; i++) {
		%>
		<a href="itemList2.do?pageNum=<%=i %>&pageSize=<%=pageSize%>">[<%=i %>]</a>
		<%
			}
			if (endPage < pageCount) {
		%>
		<a href="itemList2.do?pageNum=<%=startPage + 10 %>&pageSize=<%=pageSize%>">[다음]</a>
		<%
			}
		%>
		<br><br>
		<!-- EL로 변수 가리키는 문법 알아내기 -->
		<c:choose>
			<c:when test="${pageSize == 5}">
				<form action="itemList2.do">
				<select name="pageSize">
					<option value="5" selected>5</option>
					<option value="10">10</option>
					<option value="15">15</option>
					<option value="20">20</option>
				</select>
				<button type="submit">페이지씩 보기</button>
				</form>
			</c:when>
			<c:when test="${pageSize == 10}">
				<form action="itemList2.do">
				<select name="pageSize">
					<option value="5">5</option>
					<option value="10" selected>10</option>
					<option value="15">15</option>
					<option value="20">20</option>
				</select>
				<button type="submit">페이지씩 보기</button>
				</form>
			</c:when>
			<c:when test="${pageSize == 15}">
				<form action="itemList2.do">
				<select name="pageSize">
					<option value="5">5</option>
					<option value="10">10</option>
					<option value="15" selected>15</option>
					<option value="20">20</option>
				</select>
				<button type="submit">페이지씩 보기</button>
				</form>
			</c:when>
			<c:when test="${pageSize == 20}">
				<form action="itemList2.do">
				<select name="pageSize">
					<option value="5">5</option>
					<option value="10">10</option>
					<option value="15">15</option>
					<option value="20" selected>20</option>
				</select>
				<button type="submit">페이지씩 보기</button>
				</form>
			</c:when>
		</c:choose>
	</div>
</body>
</html>