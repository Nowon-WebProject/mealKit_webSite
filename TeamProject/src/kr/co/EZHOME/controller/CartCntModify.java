package kr.co.EZHOME.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.CartDAO;

/**
 * Servlet implementation class CartCntModify
 */
@WebServlet("/cartcntmodify.do")
public class CartCntModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartCntModify() {
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
		
		// 세션 내 장바구니의 상태를 업데이트하기 위해 만듦
		int item_cnt = Integer.parseInt(request.getParameter("item_cnt"));
		int cart_seq =  Integer.parseInt(request.getParameter("cart_seq"));
		
		CartDAO cdao = CartDAO.getInstance();
		cdao.cartCntModify(item_cnt, cart_seq);
		
		response.sendRedirect("cartlist.do");
		
	}

}