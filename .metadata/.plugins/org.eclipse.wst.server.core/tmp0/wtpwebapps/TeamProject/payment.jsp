<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    
    request.setCharacterEncoding("UTF-8");
    String item_name = null;
    int etc = (int) session.getAttribute("cartcnt")-1;
    String userid = request.getParameter("userid");
    String deli_name = request.getParameter("deli_name");
    String deli_phone = request.getParameter("deli_phone");
    String deli_addr = "("+request.getParameter("deli_postcode")+") "+ request.getParameter("deli_addr1") +", "+ request.getParameter("deli_addr2");
    String deli_postcode = request.getParameter("deli_postcode");
    String deli_msg = request.getParameter("deli_msg");
    String deli_pwd = request.getParameter("deli_pwd");
    String deli_status = request.getParameter("deli_status");
    
    
    int total_price = Integer.parseInt(request.getParameter("total_price"));
    int usePoint =  Integer.parseInt(request.getParameter("usePoint"));
    int amount = total_price - usePoint;
    
    int point = Integer.parseInt(request.getParameter("point"));
    
    if(etc == 0){
    item_name = request.getParameter("item_name");
    }else{
    item_name = request.getParameter("item_name")+" 외 "+etc+" 건";
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이젠, 집에서 | 회원가입</title>
<link href="css/styles.css" rel="stylesheet" />
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<style type="text/css">

</style>
</head>
<body>

<jsp:include page="nav.jsp"></jsp:include>
아이디:<%=userid %><br>
배송받을 이름:<%=deli_name%><br>
연락처:<%=deli_phone%><br>
우편번호<%=deli_postcode%>
배송지:<%=deli_addr%><br>
공동현관:<%=deli_pwd%><br>
배송메세지:<%=deli_msg%><br>
배송상태:<%=deli_status%><br>
상품명:<%=item_name%><br>
적립금:<%=point%><br>
총 금액:<%=total_price%><br>
사용할 적립금:<%=usePoint%><br>
최종 구매금액 :<%=amount%>
    <script>
               var deli_pwd_encode = encodeURIComponent('<%=deli_pwd%>');
               location.href='http://localhost:8080/TeamProject/purchaseok.do?userid=<%=userid%>&item_name=<%=item_name%>&total_price=<%=amount%>&deli_name=<%=deli_name%>&deli_addr=<%=deli_addr%>&deli_phone=<%=deli_phone%>&deli_msg=<%=deli_msg%>&deli_pwd='+deli_pwd_encode+'&deli_status=<%=deli_status%>&usePoint=<%=usePoint%>&point=<%=point%>&deli_postcode=<%=deli_postcode%>';
        <%-- $(function(){
        var IMP = window.IMP; // 생략가능
        IMP.init('imp79971809'); // "가맹점 식별코드"
        var msg;
        
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : '이젠, 집에서) <%=item_name%>',
            amount : <%=amount%>,
            buyer_name : '<%=deli_name%>',
            buyer_tel : '<%=deli_phone%>',
            buyer_postcode : '<%=deli_postcode%>', //우편번호 임시
            buyer_addr : '<%=deli_addr%>',
        }, function(rsp) {
            if ( rsp.success ) {
                jQuery.ajax({
                    url: "/payments/complete",
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        imp_uid : rsp.imp_uid
                        //기타 필요한 데이터가 있으면 추가 전달
                    }
                })
                //성공시 이동할 페이지
               var deli_pwd_encode = encodeURIComponent('<%=deli_pwd%>');
               location.href='http://localhost:8080/TeamProject/purchaseok.do?userid=<%=userid%>&item_name=<%=item_name%>&total_price=<%=amount%>&deli_name=<%=deli_name%>&deli_addr=<%=deli_addr%>&deli_phone=<%=deli_phone%>&deli_msg=<%=deli_msg%>&deli_pwd='+deli_pwd_encode+'&deli_status=<%=deli_status%>&usePoint=<%=usePoint%>&point=<%=point%>&deli_postcode=<%=deli_postcode%>';
               
            } else {
                msg = '결제에 실패하였습니다. 장바구니로 돌아갑니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지
                 location.href='http://localhost:8080/TeamProject/cartlist.do';
                alert(msg);
            }
        });
        
    }); --%>
    </script>
  
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>