package kr.co.EZHOME.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.EZHOME.dao.RecentAddrDAO;
import kr.co.EZHOME.dao.CartDAO;
import kr.co.EZHOME.dao.ItemDAO;
import kr.co.EZHOME.dao.OrderDAO;
import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.dto.RecentAddrDTO;
import kr.co.EZHOME.dto.OrderDTO;
import kr.co.EZHOME.dto.UserDTO;

/**
 * Servlet implementation class PurchaseOkServlet
 */
@WebServlet("/orderOk.do")
public class OrderOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderOkServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		// 주문 완료 서블릿
		// payment.jsp에서 결제가 이루어진 후
		// 주문 정보를 가지고 이곳으로 넘어옴.
		// 넘어온 값들을 이용해
		// 유저 포인트 갱신, 주문완료 테이블에 추가, 최근 배송지 추가/삭제가 이루어짐.

		request.setCharacterEncoding("utf-8");

		String userid = request.getParameter("userid");
		String order_name = request.getParameter("item_name");
		int amount = Integer.parseInt(request.getParameter("total_price")); // 배송비, 적립금 포함
		String deli_name = request.getParameter("deli_name");
		String deli_addr = request.getParameter("deli_addr");
		String deli_phone = request.getParameter("deli_phone");
		String deli_msg = request.getParameter("deli_msg");
		String deli_pwd = request.getParameter("deli_pwd");
		String deli_status = request.getParameter("deli_status");
		int usePoint = Integer.parseInt(request.getParameter("usePoint"));
		int point = Integer.parseInt(request.getParameter("point"));
		String item_num = request.getParameter("item_num");
		String item_cnt = request.getParameter("item_cnt");
		String deli_postcode = request.getParameter("deli_postcode");
		
		
		//4자리 숫자+대문자영문 조합 생성
		Random rnd=new Random();
	    StringBuffer buf=new StringBuffer();
	    for(int i=1;i<=8;i++) {
	        if(rnd.nextBoolean())
	            buf.append((char)(rnd.nextInt(26)+65));   // 0~25(26개) + 65 
	        else
	            buf.append(rnd.nextInt(10));
	    }
	
		String random = buf.toString();
		System.out.println(random);
		
		
		String time = new SimpleDateFormat("yyMMddHHmmss").format(System.currentTimeMillis());
		String order_num = time+random;

		UserDAO udao = UserDAO.getInstance();
		udao.minusPoint(usePoint, userid);
		udao.addPoint(point, userid);

		
		// 넘어온 상품 번호, 갯수에 대한 작업 준비
		ItemDAO idao = ItemDAO.getInstance();
		String[] item_num1 = item_num.split(",");
		String[] item_cnt1 = item_cnt.split(",");
		
		OrderDTO odto = new OrderDTO();
		OrderDAO odao = OrderDAO.getInstance();
		
		//반복 횟수 : 장바구니에 들어있던 상품의 종류 갯수
		CartDAO cdao = CartDAO.getInstance();
		int cnt = cdao.cartCnt(userid);
		

		
		// 주문한 품목에 대한 정보도 함께 db에 넣음.
		for (int i = 0; i < cnt; i++) {
			int item_num_ok = 0;
			int item_cnt_ok = 0;
			
			if(cnt==1) {
			String numOk = item_num1[i].substring(1, item_num1[i].length()-1);
			String cntOk = item_cnt1[i].substring(1, item_cnt1[i].length()-1);
			item_num_ok = Integer.parseInt(numOk);
			item_cnt_ok = Integer.parseInt(cntOk);
			}else {
				if(i==cnt-1) {
				String iok = item_num1[i].substring(1, item_num1[i].length()-1);
				String cok = item_cnt1[i].substring(1, item_cnt1[i].length()-1);
				item_num_ok = Integer.parseInt(iok);
				item_cnt_ok = Integer.parseInt(cok);
				}else {
				String iok = item_num1[i].substring(1, item_num1[i].length());
				String cok = item_cnt1[i].substring(1, item_cnt1[i].length());
				item_num_ok = Integer.parseInt(iok);
				item_cnt_ok = Integer.parseInt(cok);
				}
			}
			String item_name_ok = idao.selectItemName(item_num_ok);
			System.out.println(item_num_ok);
			int item_price_ok = idao.selectItemPrice(item_num_ok);
			String item_pictureUrl1_ok = idao.selectItemPictureUrl1(item_num_ok);
		
			odto.setOrder_num(order_num);
			odto.setUserid(userid);
			odto.setOrder_name(order_name);
			odto.setAmount(amount);
			odto.setUsePoint(usePoint);
			odto.setDeli_name(deli_name);
			odto.setDeli_addr(deli_addr);
			odto.setDeli_phone(deli_phone);
			odto.setDeli_msg(deli_msg);
			odto.setDeli_pwd(deli_pwd);
			odto.setDeli_status(deli_status);
			odto.setItem_pictureUrl1(item_pictureUrl1_ok);
			odto.setItem_num(item_num_ok);
			odto.setItem_name(item_name_ok);
			odto.setItem_price(item_price_ok);
			odto.setItem_cnt(item_cnt_ok);
			odto.setRefund_request("");
			odto.setRefund_status("미신청");
			odto.setRefund_reject("");

			odao.insertOrder(odto);
			//재고, 판매량 갱신
			idao.itemSales(item_cnt_ok, item_num_ok);
			idao.itemQuantity(item_cnt_ok, item_num_ok);
			

		}

		// 장바구니 비우기
		cdao.deleteAllCart(userid);

		
		
		// 최근 배송지 추가,삭제
		RecentAddrDAO adao = RecentAddrDAO.getInstance();
		RecentAddrDTO adto = new RecentAddrDTO();
		adto.setUserid(userid);
		adto.setDeli_name(deli_name);
		adto.setDeli_addr(deli_addr);
		adto.setDeli_phone(deli_phone);
		adto.setDeli_msg(deli_msg);
		adto.setDeli_pwd(deli_pwd);

		int addrCheckResult = adao.addrCheck(deli_postcode, deli_name, userid);
		int oldAddrSeq = adao.oldAddrFind(userid);
		if (addrCheckResult == 0) {
			adao.insertAddr(adto);
		}

		int addrCnt = adao.addrCnt(userid);
		if (addrCnt > 5) {
			adao.deleteAddr(oldAddrSeq);
		}

		HttpSession session = request.getSession();
		session.setAttribute("cartcnt", cdao.cartCnt(userid));
		session.setAttribute("addrcnt", adao.addrCnt(userid));
		session.setAttribute("point", udao.nowPoint(userid));

		response.sendRedirect("orderOk.jsp");
		// RequestDispatcher dispatcher =
		// request.getRequestDispatcher("purchaseOk.jsp");
		// dispatcher.forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

	}

}
