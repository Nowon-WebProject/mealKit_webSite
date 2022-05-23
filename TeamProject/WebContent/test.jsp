<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이젠, 집에서 | 회원가입</title>
<link href="css/styles.css" rel="stylesheet" />
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<style>
    -webkit-text-size-adjust: 100%;
    font-family: noto sans,malgun gothic,AppleGothic,dotum;
    line-height: 1;
    letter-spacing: -.05em;
    color: #4c4c4c;
    font-size: 12px;
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    word-break: break-all;

</style>
</head>
<body>

<jsp:include page="nav.jsp"></jsp:include>
<div class="goods-view-tab">
<ul class="goods-view-infomation-tab-group">
<li class="goods-view-infomation-tab"><a href="#goods-description" class="goods-view-infomation-tab-anchor __active">상품설명</a></li>
<li class="goods-view-infomation-tab"><a href="#goods-infomation" class="goods-view-infomation-tab-anchor">상세정보</a></li>
<li class="goods-view-infomation-tab goods-view-review-tab"><a href="#goods-review" class="goods-view-infomation-tab-anchor">후기 <span class="count_review">(98)</span></a></li>

<li class="goods-view-infomation-tab qna-show"><a href="#goods-qna" class="goods-view-infomation-tab-anchor">문의</a></li>
</ul>
</div>


<jsp:include page="footer.jsp"></jsp:include>





</body>
</html>