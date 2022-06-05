package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.ItemDAO;
import kr.co.EZHOME.dao.OrderDAO;

/**
 * Servlet implementation class RefundRequestServlet
 */
@WebServlet("/refundRequest.do")
public class RefundRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RefundRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		// 유저의 취소/환불 요청 서블릿
		// 원하는 품목을 체크하고 신청을 누르면
		// 해당 값을 이용하여 작업이 이루어짐
		// 배송상태가 '결제완료'일 시 즉시 취소가 되며
		// 배송준비, 배송완료 상태일 시 취소 요청 상태로 넘어감.
	
		
		request.setCharacterEncoding("utf-8");

		String[] a = request.getParameterValues("orderInfo");
		String order_num = request.getParameter("order_num");
		
		String refund_request = request.getParameter("refund_request");
		System.out.println(refund_request);
		ItemDAO idao = ItemDAO.getInstance();
		OrderDAO odao = OrderDAO.getInstance();
		
		for (int i = 0; i < a.length; i++) {
			String a1 = a[i]; // asd,12,123,asd
			String[] a2 = a1.split("/");

			int num = Integer.parseInt(a2[0]); // item_num
			String name = a2[1]; // item_name
			int price = Integer.parseInt(a2[2]); // item_price
			int cnt = Integer.parseInt(a2[3]); // item_cnt
			String deli_status = a2[4]; // item_name

			if (deli_status.equals("결제완료")) {
				idao.itemQuantityRefund(cnt, num);
				idao.itemSalesRefund(cnt, num);
				String status = "취소 완료";
				odao.modifyRefundStatus(num, status, order_num);
			}else {
				if(refund_request.equals("empty")) {
					refund_request = request.getParameter("refund_request2");
				}
				odao.addRequest(refund_request, num, order_num);
				String status = "취소 요청 중..";
				odao.modifyRefundStatus(num, status, order_num);
			}
			
			
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("orderInfo.do");
		dispatcher.forward(request, response);
	}

}
