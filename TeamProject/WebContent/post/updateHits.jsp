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
	<%-- 이 화면 그대로 조회수만 1씩 늘어나게 하기 나중에 할 것 --%>
	<%
		String post_num = request.getParameter("post_num");

		PostScriptDAO pDao = PostScriptDAO.getInstance();
		int result = pDao.updateHits(Integer.parseInt(post_num));

		if (result == 1) {
	%>
	<script type="text/javascript">
		window.onload = function() {
			self.close();
		};
	</script>
	<%
		}
	%>
</body>
</html>