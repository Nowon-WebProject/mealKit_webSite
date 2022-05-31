package kr.co.EZHOME.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.EZHOME.dao.CartDAO;
import kr.co.EZHOME.dao.ItemDAO;
import kr.co.EZHOME.dto.ItemDTO;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/payment.do")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// 결제 서블릿.
		// order에서 넘어온 정보를
		// payment로 보낼 준비를 하고,
		// 또 한번 재고 체크를 진행함.
		
		String url="payment.jsp";
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		

		String item_name = null;
		int etc = (int) session.getAttribute("cartcnt")-1;
		
		userid = request.getParameter("userid");
		String deli_name = request.getParameter("deli_name");
		String deli_phone = request.getParameter("deli_phone");
		String deli_addr = "("+request.getParameter("deli_postcode")+") "+ request.getParameter("deli_addr1") +", "+ request.getParameter("deli_addr2");
		String deli_postcode = request.getParameter("deli_postcode");
		String deli_msg = request.getParameter("deli_msg");
		String deli_pwd = request.getParameter("deli_pwd");
		String deli_status = request.getParameter("deli_status");
		
		
		String[] item_num = request.getParameterValues("item_num[]");
		String[] item_cnt = request.getParameterValues("item_cnt[]");
		String item_num_toString = Arrays.toString(item_num);
		String item_cnt_toString = Arrays.toString(item_cnt);
		
		int total_price = Integer.parseInt(request.getParameter("total_price"));
		int usePoint =  Integer.parseInt(request.getParameter("usePoint"));
		int amount = total_price - usePoint;
		
		int point = Integer.parseInt(request.getParameter("point"));
		
		if(etc == 0){
			item_name = request.getParameter("item_name");
			request.setAttribute("item_name", item_name);
		}else{
			item_name = request.getParameter("item_name")+" 외 "+etc+" 건";
			request.setAttribute("item_name", item_name);
		}
		
		request.setAttribute("deli_addr", deli_addr);
		request.setAttribute("item_num", item_num_toString);
		request.setAttribute("item_cnt", item_cnt_toString);
		request.setAttribute("total_price", total_price);
		request.setAttribute("usePoint", usePoint);
		request.setAttribute("point", point);
		request.setAttribute("amount", amount);
		request.setAttribute("deli_msg", deli_msg);
		request.setAttribute("deli_pwd", deli_pwd);
		request.setAttribute("deli_postcode", deli_postcode);
		request.setAttribute("deli_status", deli_status);
		request.setAttribute("userid", userid);
		request.setAttribute("deli_name", deli_name);
		request.setAttribute("deli_phone", deli_phone);
		
		// 구매할 시점에 재고보다 구매량이 많을경우 구매실패
		String[] item_num1 = item_num_toString.split(",");
		String[] item_cnt1 = item_cnt_toString.split(",");
		CartDAO cdao = CartDAO.getInstance();
		int cnt = cdao.cartCnt(userid);
		ItemDAO idao = ItemDAO.getInstance();
		String message = "";
		int check = 0;
		for (int i = 0; i < cnt; i++) {
			String iok = null;
			String cok = null;
			int item_quantity = 0;
			// start부터 end-1위치까지 자름.
			
			if(cnt==1) {
			iok = item_num1[i].substring(1, item_num1[i].length()-1);
			cok = item_cnt1[i].substring(1, item_cnt1[i].length()-1);
			item_quantity = idao.itemCnt(Integer.parseInt(iok));
			}else {
				if(i==cnt-1) {
				iok = item_num1[i].substring(1, item_num1[i].length()-1);
				cok = item_cnt1[i].substring(1, item_cnt1[i].length()-1);
				item_quantity = idao.itemCnt(Integer.parseInt(iok));
				}else {
				iok = item_num1[i].substring(1, item_num1[i].length());
				cok = item_cnt1[i].substring(1, item_cnt1[i].length());
				item_quantity = idao.itemCnt(Integer.parseInt(iok));
				}
			}
			//재고량이 더 적은 경우 , 많은 경우
			if(Integer.parseInt(cok) > item_quantity) {
				String item_name1 = idao.selectItemName(Integer.parseInt(iok));
				message += item_name1+" ";
				check +=1;
			}

		}
		if(check > 0) {
		url = "order.do";
		}
		request.setAttribute("message", "[ "+message+"] 의 재고보다 구매하시려는 양이 많아 결제가 취소됩니다. 확인 후 다시 주문해주세요.");
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
