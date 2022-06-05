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
@WebServlet("/orderInfo.do")
public class OrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderInfoServlet() {
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

		String order_num = request.getParameter("order_num");
		int check = 0;

		OrderDAO odao = OrderDAO.getInstance();
		ArrayList<OrderDTO> olist = odao.selectRefundList(order_num);

		request.setAttribute("olist", olist);
		request.setAttribute("check", check);
		RequestDispatcher dispatcher = request.getRequestDispatcher("orderInfo.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		// 취소/환불을 위한 출력 서블릿
		request.setCharacterEncoding("utf-8");
		String order_num = request.getParameter("order_num");
		int check= 1;
		OrderDAO odao = OrderDAO.getInstance();
		ArrayList<OrderDTO> olist = odao.selectRefundList(order_num);

		request.setAttribute("olist", olist);
		
		request.setAttribute("check", check);
		RequestDispatcher dispatcher = request.getRequestDispatcher("orderInfo.jsp");
		dispatcher.forward(request, response);

	}

}
