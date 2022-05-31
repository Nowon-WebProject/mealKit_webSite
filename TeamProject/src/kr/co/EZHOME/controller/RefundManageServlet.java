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
		
		// 취소/환불 관리 출력 서블릿
		
		request.setCharacterEncoding("utf-8");	
		
		OrderDAO odao = OrderDAO.getInstance();
		ArrayList<OrderDTO> olist = odao.selectRefundRequest();
		request.setAttribute("olist", olist);



		RequestDispatcher dispatcher = request.getRequestDispatcher("refundManage.jsp");
		dispatcher.forward(request, response);
		
	}

}
