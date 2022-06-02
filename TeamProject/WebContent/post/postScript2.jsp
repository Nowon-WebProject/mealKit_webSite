<%@page import="dao.ItemDAO3"%>
<%@page import="dto.ItemVO3"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/shopping.css">
</head>
<body>
	<%
		// 화면에 보여질 게시글 개수 7개로 고정한 버전
	
				// 화면에 보여질 총 게시글 개수
				int pageSize = 7;
			
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
				
				ItemDAO3 iDao3 = ItemDAO3.getInstance();
				count = iDao3.getAllCount();
				
				// 지금 페이지에 보여질 시작 번호와 끝 번호
				// ex.
				// p1 p2 p3 p4
				// 1  11 21 31 ...
				// 10 20 30 40 ...
				int startRow = (currentPage - 1) * pageSize + 1;
				int endRow = (currentPage * pageSize);
	
				List<ItemVO3> list = iDao3.selectAllItems(startRow, endRow);
	%>
	<div id="wrap" style="width: 800px" align="center">
		<h3>후기</h3>
		<table class="list">
			<tr>
				<td colspan="7" style="border: white; text-align: right">
					<select name="order">
						<option value="1">최근등록순</option>
						<option value="2">도움많은순</option>
						<option value="3">조회많은순</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>도움</td>
				<td>조회</td>
				<td>평점</td>
			</tr>
			<c:forEach var="item" items="<%=list %>">
				<tr>
					<td>${item.item_num}</td>
					<td>${item.item_subject}</td>
					<td>${item.item_writer}</td>
					<td>${item.item_date}</td>
					<td>${item.item_help}</td>
					<td>${item.item_hits}</td>
					<td>${item.item_stars}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" style="border: white; text-align: right">
					<a href="postWrite.do">후기 쓰기</a>
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
		<a href="postList.do?pageNum=<%=startPage - 10 %>&pageSize=<%=pageSize%>">[이전]</a>
		<%
			}
			for (int i = startPage; i <= endPage; i++) {
		%>
		<a href="postList.do?pageNum=<%=i %>&pageSize=<%=pageSize%>">[<%=i %>]</a>
		<%
			}
			if (endPage < pageCount) {
		%>
		<a href="postList.do?pageNum=<%=startPage + 10 %>&pageSize=<%=pageSize%>">[다음]</a>
		<%
			}
		%>
		<br><br>
	</div>
</body>
</html>