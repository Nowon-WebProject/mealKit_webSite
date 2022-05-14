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
	<div id="wrap" align="center">
		<h1>상품 목록-관리자 페이지</h1>
		<table class="list">
			<tr>
				<td colspan="10" style="border: white; text-align: right">
					<a href="itemWrite.do">상품 등록</a>
				</td>
			</tr>
			<tr>
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
			<c:forEach var="item" items="${itemList}">
				<tr class="record">
					<td>${item.item_num}</td>
					<td>${item.item_category}</td>
					<td>${item.item_name}</td>
					<td>${item.item_price}원</td>
					<td>${item.item_quantity}</td>
					<td>${item.item_date}</td>
					<td>${item.item_total}</td>
					<td>${item.item_time}</td>
					<td><a href="itemUpdate.do?item_num=${item.item_num}">상품 수정</a></td>
					<td><a href="itemDelete.do?item_num=${item.item_num}">상품 삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>