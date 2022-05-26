<%@page import="kr.co.EZHOME.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>이젠, 집에서 | 메인화면</title>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <style type="text/css">
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
	width: 100%;
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
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                
                	<c:forEach var="main" items="${ilist}">
                	<form action="cartinsert.do" method="post">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                             <a href="itemabout.do?item_num=${main.item_num}">
                            <img class="card-img-top" src="${main.item_pictureUrl1}" alt="..." />
                            </a>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${main.item_name}</h5>
                                    <!-- Product price-->
                                    \ <fmt:formatNumber value="${main.item_price}"/>
                                    
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto"><i class="bi-cart-fill me-1"></i>                    <input type="hidden" name="pictureurl" value="${main.item_pictureUrl1}">
                    <input type="hidden" name="item_quantity" value="${main.item_quantity}">
                    <input type="hidden" name="item_pictureUrl1" value="${main.item_pictureUrl1}">
                    <input type="hidden" name="item_num" value="${main.item_num}">
                    <input type="hidden" name="item_name" value="${main.item_name}">
                    <input type="hidden" name="item_price" value="${main.item_price}">
                    <input type="number" name="item_cnt" value="1" min="1" max="${main.item_quantity}">
                    <input type="submit" class="form-btn" value="장바구니에 담기"></a></div>
                            </div>
                        </div>
                    </div>

                    </form>
                    </c:forEach>
                    
                
                    
                </div>
            </div>
        </section>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>