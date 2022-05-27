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
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		System.out.println(userid);
		
		
		if (userid == "") {
			System.out.println("비로그인 상태");

		} else {
			String item_quantity = request.getParameter("item_quantity");
			String item_num = request.getParameter("item_num");
			String item_pictureUrl1 = request.getParameter("item_pictureUrl1");
			String item_name = request.getParameter("item_name");
			String item_price = request.getParameter("item_price");
			String item_cnt = request.getParameter("item_cnt");

			CartDTO cdto = new CartDTO();
			cdto.setUserid(userid);
			cdto.setItem_quantity(Integer.parseInt(item_quantity));
			cdto.setItem_num(Integer.parseInt(item_num));
			cdto.setItem_pictureUrl1(item_pictureUrl1);
			cdto.setItem_name(item_name);
			cdto.setItem_price(item_price);
			cdto.setItem_cnt(Integer.parseInt(item_cnt));

			CartDAO cdao = CartDAO.getInstance();
			int cartCheckResult = cdao.cartCheck(item_name, userid);
			if (cartCheckResult == 0) {
				cdao.insertCart(cdto);
			} else {
				cdao.cartAdd(Integer.parseInt(item_cnt), item_name, userid);
			}

			HttpSession session = request.getSession();
			session.setAttribute("cartcnt", cdao.cartCnt(userid));
		}

		response.sendRedirect(request.getHeader("Referer"));
		/* response.sendRedirect("cartlist.do"); */
		/*
		 * RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		 * dispatcher.forward(request, response);
		 */
	}

}
