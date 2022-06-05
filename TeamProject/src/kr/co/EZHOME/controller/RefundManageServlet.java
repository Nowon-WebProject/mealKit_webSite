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
@WebServlet("/refundManage.do")
public class RefundManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RefundManageServlet() {
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
		// 취소/환불 관리 출력 서블릿
		
		request.setCharacterEncoding("utf-8");
		
		
		String pageNum = request.getParameter("pageNum");
		int pageSize =Integer.parseInt(request.getParameter("pageSize"));
		int currentPage = Integer.parseInt(pageNum);
		String category = request.getParameter("category");
		String keyword = request.getParameter("keyword");
		
		// 현재 페이지에 보여 줄 시작 번호를 설정 ==> 데이터 베이스에서 불러올 시작 번호
		int startRow = (currentPage - 1) * pageSize + 1; // 1 11 21 31
		int endRow = currentPage * pageSize; // 10 20 30 40
		
		
		
		OrderDAO odao = OrderDAO.getInstance();
		ArrayList<OrderDTO> olist = odao.selectRefundRequest(startRow, endRow, category, keyword);
		request.setAttribute("olist", olist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("refundManage.jsp");
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
		// 취소/환불 관리 출력 서블릿
		
		request.setCharacterEncoding("utf-8");
		
		
		String pageNum = request.getParameter("pageNum");
		int pageSize =Integer.parseInt(request.getParameter("pageSize"));
		int currentPage = Integer.parseInt(pageNum);
		String category = request.getParameter("category");
		String keyword = request.getParameter("keyword");
		
		// 현재 페이지에 보여 줄 시작 번호를 설정 ==> 데이터 베이스에서 불러올 시작 번호
		int startRow = (currentPage - 1) * pageSize + 1; // 1 11 21 31
		int endRow = currentPage * pageSize; // 10 20 30 40
		
		
		
		OrderDAO odao = OrderDAO.getInstance();
		ArrayList<OrderDTO> olist = odao.selectRefundRequest(startRow, endRow, category, keyword);
		
		request.setAttribute("olist", olist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("refundManage.jsp");
		dispatcher.forward(request, response);
	}

}
