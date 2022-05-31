package kr.co.EZHOME.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.ItemDAO;
import kr.co.EZHOME.dao.OrderDAO;
import kr.co.EZHOME.dto.ItemDTO;
import kr.co.EZHOME.dto.OrderDTO;

/**
 * Servlet implementation class RefundServlet
 */
@WebServlet("/refundManageOk.do")
public class RefundManageOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RefundManageOkServlet() {
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
		
		// 취소/환불 요청 관리 서블릿
		// refundManage.jsp에서 승인을 눌러 넘어온 값들을 이용하여
		// 결제를 취소하는 작업을 함.
		// 재고 + , 판매량 -

		request.setCharacterEncoding("utf-8");

		OrderDAO odao = OrderDAO.getInstance();

		String[] a = request.getParameterValues("orderInfo");

		ItemDAO idao = ItemDAO.getInstance();

		for (int i = 0; i < a.length; i++) {
			String a1 = a[i];
			String[] a2 = a1.split("/");

			int num = Integer.parseInt(a2[0]); // item_num
			int cnt = Integer.parseInt(a2[1]); // item_cnt
			String order_num = a2[2];

			idao.itemQuantityRefund(cnt, num);
			idao.itemSalesRefund(cnt, num);
			String status = "취소 완료";
			odao.modifyRefundStatus(num, status, order_num);
			odao.addRequest(0, num, order_num);

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("refundManage.do");
		dispatcher.forward(request, response);

	}

}
