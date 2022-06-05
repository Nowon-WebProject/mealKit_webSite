<%@page import="kr.co.EZHOME.dao.OrderDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/scripts.js"></script>
<title>Insert title here</title>
<style type="text/css">
.refund table {
    width: 100%;
    border-top: 1px solid orange;
    border-collapse: collapse;
}

.refund th {
	background-color: orange;
    border-bottom: 1px solid #444444;
    padding: 10px;
}

.refund td {
    border-bottom: 1px solid #444444;
    padding: 10px;
}

.form-input {
	width: 100%;
	padding: 10px 20px;
	font-size: 20px;
	outline: none;
	margin: 10px 0;
	border: 1px solid #efefef;
	box-sizing: border-box;
}

.form-input:focus {
	box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.1);
	border: none;
}

.form-input--title {
	width: 100%;
	display: block;
	margin: 5px 0;
	box-sizing: border-box;
}

.form-btn {
	border: 0;
	display: block;
	width: 120px;
	font-size: 16px;
	height: 40px;
	background-color: #fd7e14;
	color: #fff;
	box-sizing: border-box;
	margin: 5px 0;
	cursor: pointer;
}

.form-btn:hover {
	background-color: #FF9900;
	box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.5);
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$("#cbx_chkAll").click(function() {
		if($("#cbx_chkAll").is(":checked")) $("input[name=orderInfo]").prop("checked", true);
		else $("input[name=orderInfo]").prop("checked", false);
	});
	
	$("input[name=orderInfo]").click(function() {
		var total = $("input[name=orderInfo]").length;
		var checked = $("input[name=orderInfo]:checked").length;
		
		if(total != checked) $("#cbx_chkAll").prop("checked", false);
		else $("#cbx_chkAll").prop("checked", true); 
	});
});
</script>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<%
		// 화면에 보여질 총 게시글 개수
		int pageSize = 20;
		String ps = request.getParameter("pageSize");
		if (ps == null)
			pageSize = 10;
		else
			pageSize = Integer.parseInt(ps);

		// 누른 페이지
		String pageNum = request.getParameter("pageNum");
		String category = request.getParameter("category");
		String keyword = request.getParameter("keyword");

		// 처음엔 1페이지
		if (pageNum == null)
			pageNum = "1";

		// 현재 페이지 (누른 페이지 또는 1페이지)
		int currentPage = Integer.parseInt(pageNum);

		// 전체 글 개수
		int count = 0;
		OrderDAO odao = OrderDAO.getInstance();

		count = odao.refundRequestCnt(category, keyword);
		System.out.println(count);

		// 페이지 숫자 세기
		int number = 0;

		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = (currentPage * pageSize);

		String check = (request.getAttribute("olist").toString());
		if (!check.equals("[]")) {
	%>

		<div style="width: 60%; margin-left: auto; margin-right: auto;">
			<br>
			<h2>취소/환불 요청 목록</h2>
			<br>
			<hr>
			<br>
			<div align="center">
				<form action="refundManage.do">
					<input type="hidden" name="pageNum" value="1">
					<input type="hidden" name="pageSize" value="<%=pageSize%>">
					<select name="category">
						<option value="order_num">주문번호</option>
						<option value="userid">아이디</option>
					</select>
					&nbsp;
					<i class="bi-search" style="font-size: 20px"></i>
					&nbsp;
					<input type="text" name="keyword" placeholder="" size="40">
					<input type="submit" value="검색">
				</form>
			</div>
			<form action="refundManageOk.do" method="post">
				<br>
				<br>
				<div class="refund">
				<table>
					<tr>
						<th><input type="checkbox" id="cbx_chkAll"></th>
						<th>주문번호</th>
						<th>주문자</th>
						<th></th>
						<th>상품명</th>
						<th>상품가격</th>
						<th>상품갯수</th>
						<th>취소/환불사유</th>
						<th>거절사유</th>
					</tr>
					<c:forEach var="list" items="${olist}">
						<tr>
							<td><input type="checkbox" value="${list.item_num}/${list.item_cnt}/${list.order_num}" name="orderInfo"></td>
							<td><a href="orderInfo.do?order_num=${list.order_num}">${list.order_num}</a>
							</td>
							<td>${list.userid}</td>
							<td><img src="upload/${list.item_pictureUrl1}" width="75px" height="75px">
							<td>${list.item_name}</td>
							<td>${list.item_price}</td>
							<td>${list.item_cnt}</td>
							<td>${list.refund_request}</td>
							<td>${list.refund_reject}</td>
						</tr>
					</c:forEach>
				</table>
				</div>
				<br>
				<div align="right">
				<input type="hidden" name="pageSize" value="<%=pageSize%>">
				<input type="hidden" name="pageNum" value="<%=pageNum%>">
				<input type="hidden" name="category" value="<%=category%>">
				<input type="hidden" name="keyword" value="<%=keyword%>">
				<input type="submit" class="form-btn" name="check" value="승인">
				<input type="submit" class="form-btn" name="check" value="거절">
				거절 사유 :
				<select name="reject" id="select">
					<option value="empty" id="1">직접입력</option>
					<option value="밀키트 제품 특성 상 단순 변심으로 인한 취소/환불이 불가합니다.">밀키트 제품 특성 상 단순 변심으로 인한 취소/환불이 불가합니다.</option>
					<option value="현재 배송이 완료된 건으로 도움을 드릴 수 없습니다. 택배사로 연락 바랍니다.">현재 배송이 완료된 건으로 도움을 드릴 수 없습니다. 택배사로 연락 바랍니다.</option>
					<option value="해당 부분 확인 결과, 문제가 없는 것으로 파악되었습니다.">해당 부분 확인 결과, 문제가 없는 것으로 파악되었습니다.</option>
				</select>
				<div id="form1">
					<input type="text" name="reject2" size="50">
				</div>
				</div>
			</form>
		</div>
		<script type="text/javascript">
		    $("#select").change(function () {
				$("#form1").hide();
				$('#form' + $(this).find('option:selected').attr('id')).show();
			});
		</script>
	<%
		} else {
	%>
	<div align="center">
		<i style="font-size: 200px; color: orange"
			class="bi-file-earmark-x-fill"></i>
		<div style="font-size: 30px; color: gray">취소/환불 요청이 없습니다.</div>
		<%
			}
		%>
	</div>

	<hr>
	<br>
	<div align="center">
		<h4>
			<%
				// 전체 페이지 개수 구하기
				// count: 전체 글 개수, pageSize: 화면에 보여질 총 게시글 개수
				int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);

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

				if (endPage > pageCount)
					endPage = pageCount;

				// 아래는 페이지 표시 과정

				if (startPage > 10) {
			%>
			<a href="refundManage.do?pageNum=<%=startPage - 10%>&pageSize=<%=pageSize%>&category=<%=category%>&keyword=<%=keyword%>" style="color: black;"><i class="bi-chevron-compact-left"></i></a>
			<%
				}
				for (int i = startPage; i <= endPage; i++) {
					if (currentPage == i) {
			%>
			<a href="refundManage.do?pageNum=<%=i%>&pageSize=<%=pageSize%>&category=<%=category%>&keyword=<%=keyword%>" style="color: white; background-color: gray; border-radius: 75px; text-decoration-line: none;">　<%=i%>　</a>
			<%
				} else {
			%>
			<a href="refundManage.do?pageNum=<%=i%>&pageSize=<%=pageSize%>&category=<%=category%>&keyword=<%=keyword%>" style="color: black; text-decoration-line: none;">　<%=i%>　</a>
			<%
					}
				}
				if (endPage < pageCount) {
			%>
			<a href="refundManage.do?pageNum=<%=startPage + 10%>&pageSize=<%=pageSize%>&category=<%=category%>&keyword=<%=keyword%>" style="color: black;"><i class="bi-chevron-compact-right"></i></a>
			<%
				}
			%>
		</h4>
	</div>
	
	
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>