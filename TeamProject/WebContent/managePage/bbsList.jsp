<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.EZHOME.dto.BbsDTO" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.cart {
	margin-left: auto; margin-right: auto;
	border: 1px solid orange;
}

.cart table {
	
	border: 1px solid orange;
	text-align: center;
	width: 100%;
}
.cart th {
	background-color: orange;
	border: 1px solid orange;
	white-space: nowrap;
	
}


.cart td {
	border: 1px solid orange;
	white-space: nowrap;
}
.cart tbody tr:nth-child(2n+1){
    background-color: 	#F8AD7B;
}
</style>
 <script type="text/javascript" src="js/sunwoo.js"></script>


</head>
<body>
<%
	Vector<BbsDTO> vec=(Vector<BbsDTO>)request.getAttribute("vec");
	int pageNum = (int)request.getAttribute("pageNum");
	int pageSize = (int)request.getAttribute("pageSize");
%>
<jsp:include page="/ui/nav.jsp"></jsp:include>
	<div align="center">
        <br><br>
        <b><font size="6" color="gray">공지 사항</font></b>
        <br><br><br>
    </div>
	<div class="cart">
	<table>
    <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
    </thead> 
    <tbody>	
    <% for(int i=0;i<vec.size();i++){
    		BbsDTO bdto=vec.get(i);		
    %>
			<tr>
			<td><%=bdto.getBbsid() %></td>
			<td><a href="javascript:method('<%=bdto.getBbsid()%>')"><%=bdto.getBbstitle() %></a></td>
			<td><%=bdto.getUserid() %></td>
			<td><%=bdto.getBbsdate() %></td>	
			</tr>
	<%} %>

    </tbody>
    </table>
  	</div>
  	<br>
  	<div align="center">
			<form action="/TeamProject/bbsList.do" method="post">
			
			<% for(int i=1;i<=pageSize;i++){%>	
			<span><input type="submit" value="<%=i %>" name="page"></span>
			<%} %>
			</form>
    </div>
    <div align="right">
	<h2><a href="/TeamProject/managePage/bbsWrite.jsp">글쓰기</a></h2>
	</div>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/ui/footer.jsp"></jsp:include>

</body>
</html>