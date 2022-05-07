package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.EZHOME.dao.CartDAO;
import kr.co.EZHOME.dto.CartDTO;

/**
 * Servlet implementation class CartInsertServlet
 */
@WebServlet("/cartinsert.do")
public class CartInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartInsertServlet() {
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");

		String userid = request.getParameter("userid");
		String product_name = request.getParameter("product_name");
		String product_price = request.getParameter("product_price");
		String product_cnt = request.getParameter("product_cnt");


		CartDTO cdto = new CartDTO();
		cdto.setUserid(userid);
		cdto.setProduct_name(product_name);
		cdto.setProduct_price(product_price);
		cdto.setProduct_cnt(Integer.parseInt(product_cnt));
		

		CartDAO cdao = CartDAO.getInstance();
		cdao.insertCart(cdto);
		
		HttpSession session=request.getSession();
		
		session.setAttribute("cartcnt",cdao.cartCnt(userid));
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

}
