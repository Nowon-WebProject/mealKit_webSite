<%@page import="dto.PostScriptVO"%>
<%@page import="dao.PostScriptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	width: 780px;
	height: 550px;
}

td {
	border-bottom: 1px solid black;
}

#headline {
	font-family: sans-serif;
	font-size: 30px;
	color: rgb(56, 69, 170);
}
</style>
</head>
<body>
		<%-- 나중에 새 창을 띄워서 내용을 보여 주던가,
		아니면 화면 안 바뀌고 조회수만 1씩 늘어나게 할 것 --%>
	<%
		// 여기 post_num이 invalid identifier라는 문제 해결할 것
		String post_num = request.getParameter("post_num");

		PostScriptDAO pDao = PostScriptDAO.getInstance();
		// 조회수 증가
		pDao.updateHits(Integer.parseInt(post_num));
		
		PostScriptVO pVo = pDao.selectPostByPost_num(post_num);
	%>
	<table>
		<tr style="height:45px;">
			<td id="headline">자세히 보는 후기</td>
			<td align="right">닫기 버튼</td>
		</tr>
		<tr style="height:90px;">
			<td><%=pVo.getPost_content()%></td>
			<td style="text-align:right; vertical-align: bottom;">
				조회: <%=pVo.getPost_hits()%>
				<br>
				작성일: <%=pVo.getPost_date()%>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<c:set var="image" value="<%=pVo.getPost_image()%>"></c:set>
				<c:if test="${image != null}">
					<img src="../images/product/${image}">
				</c:if>
			</td>
		</tr>
		<tr style="height:90px;">
			<td colspan="2" style="text-align:right; vertical-align: top;">신고 버튼</td>
		</tr>	
	</table>
</body>
</html>