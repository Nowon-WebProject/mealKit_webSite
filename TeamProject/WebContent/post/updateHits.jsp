<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.PostScriptDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<%-- 나중에 새 창을 띄워서 내용을 보여 주던가,
		아니면 화면 안 바뀌고 조회수만 1씩 늘어나게 할 것 --%>
	<%
		String post_num = request.getParameter("post_num");

		PostScriptDAO pDao = PostScriptDAO.getInstance();
		pDao.updateHits(Integer.parseInt(post_num));
	%>
	<script type="text/javascript">
		window.onload = function() {
			self.close();
		};
	</script>
</body>
</html>