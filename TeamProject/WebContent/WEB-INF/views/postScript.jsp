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
<script type="text/javascript" src="../js/item.js"></script>
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
				// 현재 페이지의 시작 게시글 번호
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
	
				// 현재 페이지의 시작 게시글 번호 받기
				number = count - (currentPage - 1) * pageSize;
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
								<option value="postScript?order=1" selected>최근등록순</option>
								<option value="postScript?order=2">도움많은순</option>
								<option value="postScript?order=3">조회많은순</option>
							</select>
						</c:when>
						<c:when test="${order == 2}">
							<select onchange="window.open(value, '_self');">
								<option value="postScript?order=1">최근등록순</option>
								<option value="postScript?order=2" selected>도움많은순</option>
								<option value="postScript?order=3">조회많은순</option>
							</select>
						</c:when>
						<c:when test="${order == 3}">
							<select onchange="window.open(value, '_self');">
								<option value="postScript?order=1">최근등록순</option>
								<option value="postScript?order=2">도움많은순</option>
								<option value="postScript?order=3" selected>조회많은순</option>
							</select>
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>번호</td>
				<td class="title">제목</td>
				<td>회원</td>
				<td>작성일</td>
				<td>도움</td>
				<td>조회</td>
				<td>평점</td>
			</tr>
			<c:forEach var="post" items="<%=list %>">
				<tr>
					<td><%=number-- %></td>
					<td class="title" onclick="javascript:displayContent('js_detail${post.post_num}', '${post.post_num}')">${post.post_subject}</td>
					<td>${post.post_writer}</td>
					<td>${post.post_date}</td>
					<td>${post.post_help}</td>
					<td>${post.post_hits}</td>
					<td>
					<!-- 별 그림 출처: https://hwasin.tistory.com/9 -->
						<c:set var="stars" value="${post.post_stars}"/>
						<c:choose>
							<c:when test="${stars == 0}">
								<img class="stars" src="../resources/stars/0.jpg">
							</c:when>
							<c:when test="${stars > 0 and stars < 0.5}">
								<img class="stars" src="../resources/stars/0.5.jpeg">
							</c:when>
							<c:when test="${stars >= 0.5 and stars < 1}">
								<img class="stars" src="../resources/stars/1.0.jpeg">
							</c:when>
							<c:when test="${stars >= 1 and stars < 1.5}">
								<img class="stars" src="../resources/stars/1.5.jpeg">
							</c:when>
							<c:when test="${stars >= 1.5 and stars < 2}">
								<img class="stars" src="../resources/stars/2.0.jpeg">
							</c:when>
							<c:when test="${stars >= 2 and stars < 2.5}">
								<img class="stars" src="../resources/stars/2.5.jpeg">
							</c:when>
							<c:when test="${stars >= 2.5 and stars < 3}">
								<img class="stars" src="../resources/stars/3.0.jpeg">
							</c:when>
							<c:when test="${stars >= 3 and stars < 3.5}">
								<img class="stars" src="../resources/stars/3.5.jpeg">
							</c:when>
							<c:when test="${stars >= 3.5 and stars < 4}">
								<img class="stars" src="../resources/stars/4.0.jpeg">
							</c:when>
							<c:when test="${stars >= 4 and stars < 4.5}">
								<img class="stars" src="../resources/stars/4.5.jpeg">
							</c:when>
							<c:when test="${stars >= 4.5 and stars <= 5}">
								<img class="stars" src="../resources/stars/5.0.jpeg">
							</c:when>
						</c:choose>
					</td>
				</tr>
				<tr id="js_detail${post.post_num}" style="display: none;">
					<td colspan="7">
						<div style="width: 75%; margin: 0 auto;">${post.post_content}</div>
						<input type="button" value="도움됐어요" onclick="javascript:helpful('${post.post_num}')">
						<br>
						<%-- 나중에 로그인한 아이디(userid 등)와 연결할 것 --%>
						<input type="button" value="지우기" onclick="javascript:deleteOK('${post.post_num}', '${post.post_writer}', '${userid}')">
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" id="js_detail_write" style="display: none;">
					<form action="postWriteDo" method="post" enctype="multipart/form-data">
						<table>
							<tr>
								<th>제목:</th>
								<td><input type="text" name="post_subject" size="40" maxlength="25"></td>
							</tr>
							<tr>
								<th>평점:</th>
								<td>
									<select name="post_stars">
										<option value="0">0</option>
										<option value="0.5">0.5</option>
										<option value="1.0">1</option>
										<option value="1.5">1.5</option>
										<option value="2.0">2</option>
										<option value="2.5">2.5</option>
										<option value="3.0">3</option>
										<option value="3.5">3.5</option>
										<option value="4.0">4</option>
										<option value="4.5">4.5</option>
										<option value="5.0" selected>5</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>내용:</th>
								<td><textarea name="post_content"></textarea></td>
							</tr>
							<tr>
								<th>사진:</th>
								<td><input type="file" name="post_image"></td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="submit" value="작성하기">&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="다시 쓰기">
									<%-- 나중에 value 값을 로그인한 계정의 아이디로 바꿀 것 (ex. ${id}) --%>
									<input type="hidden" value="id" name="post_writer">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td colspan="7" style="border: white; text-align: right;">
					<a href="javascript:displayWrite('js_detail_write')">후기 쓰기</a>
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
		<a href="postScript?pageNum=<%=startPage - 10 %>">[이전]</a>
		<%
			}
			for (int i = startPage; i <= endPage; i++) {
		%>
		<a href="postScript?pageNum=<%=i %>">[<%=i %>]
		</a>
		<%
			}
			if (endPage < pageCount) {
		%>
		<a href="postScript?pageNum=<%=startPage + 10 %>">[다음]</a>
		<%
			}
		%>
		<br> <br>
	</div>
</body>
</html>