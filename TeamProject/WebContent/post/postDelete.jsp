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
	<%
		String post_num = request.getParameter("post_num");

		PostScriptDAO pDAO = PostScriptDAO.getInstance();
		pDAO.deletePostScript(post_num);

		response.sendRedirect("postScript2.jsp");
	%>
</body>
</html>