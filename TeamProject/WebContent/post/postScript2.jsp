<%@page import="dao.PostScriptDAO"%>
<%@page import="dto.PostScriptVO"%>
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
<script type="text/javascript" src="../js/item.js?var=4"></script>
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
				
				PostScriptDAO pDao = PostScriptDAO.getInstance();
				count = pDao.getAllCount();
				
				// 지금 페이지에 보여질 시작 번호와 끝 번호
				// ex.
				// p1 p2 p3 p4
				// 1  8  15 22 ...
				// 7  14 21 28 ...
				int startRow = (currentPage - 1) * pageSize + 1;
				int endRow = (currentPage * pageSize);
				
				// 정렬 값 받기
				String order = request.getParameter("order");

				if (order == null)
					order = "1";
	
				List<PostScriptVO> list = pDao.selectAllPostScripts(startRow, endRow, order);
	%>
	<div id="wrap" style="width: 800px" align="center">
		<h3>후기</h3>
		<table class="list">
			<tr>
				<td colspan="7" style="border: white; text-align: right;">
					<c:set var="order" value="<%=order %>"></c:set>
					<c:choose>
						<c:when test="${order == 1}">
							<select onchange="window.open(value, '_self');">
								<option value="postScript2.jsp?order=1" selected>최근등록순</option>
								<option value="postScript2.jsp?order=2">도움많은순</option>
								<option value="postScript2.jsp?order=3">조회많은순</option>
							</select>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${order == 2}">
							<select onchange="window.open(value, '_self');">
								<option value="postScript2.jsp?order=1">최근등록순</option>
								<option value="postScript2.jsp?order=2" selected>도움많은순</option>
								<option value="postScript2.jsp?order=3">조회많은순</option>
							</select>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${order == 3}">
							<select onchange="window.open(value, '_self');">
								<option value="postScript2.jsp?order=1">최근등록순</option>
								<option value="postScript2.jsp?order=2">도움많은순</option>
								<option value="postScript2.jsp?order=3" selected>조회많은순</option>
							</select>
						</c:when>
					</c:choose>
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
			<c:forEach var="post" items="<%=list %>">
				<tr>
					<td>${post.post_num}</td>
					<td onclick="javascript:display('js_detail${post.post_num}')">${post.post_subject}</td>
					<td>${post.post_writer}</td>
					<td>${post.post_date}</td>
					<td>${post.post_help}</td>
					<td>${post.post_hits}</td>
					<td>${post.post_stars}</td>
				</tr>
				<tr id="js_detail${post.post_num}" style="display:none;">
					<td colspan="7">${post.post_content}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" style="border: white; text-align: right;">
					<a href="postWrite.do">후기 쓰기</a>
				</td>
			</tr>
		</table>
		<br> <br>
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
		<a href="postList.do?pageNum=<%=startPage - 10 %>">[이전]</a>
		<%
			}
			for (int i = startPage; i <= endPage; i++) {
		%>
		<a href="postList.do?pageNum=<%=i %>">[<%=i %>]
		</a>
		<%
			}
			if (endPage < pageCount) {
		%>
		<a href="postList.do?pageNum=<%=startPage + 10 %>">[다음]</a>
		<%
			}
		%>
		<br> <br>
	</div>
</body>
</html>