package kr.co.EZHOME.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.EZHOME.dao.CartDAO;

/**
 * Servlet implementation class CartDeleteServlet
 */
@WebServlet("/cartDelete.do")
public class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartDeleteServlet() {
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
		
		// 장바구니 삭제(개별,전체) 서블릿.
		// 장바구니에서 개별 삭제를 누르면 check 값이 0이 넘어오고,
		// 전체 삭제를 누르면 1이 넘어옴.
		// check을 이용하여 0일 경우 개별삭제, 1일 경우 전체삭제를 실행함.
		
		
		HttpSession session = request.getSession();
		
		String userid = (String) session.getAttribute("userid");
		
		CartDAO cdao = CartDAO.getInstance();
		int check = Integer.parseInt(request.getParameter("check"));

		if(check == 0) {
			
		int cart_seq = Integer.parseInt(request.getParameter("cart_seq"));
		cdao.deleteCart(cart_seq);
		} else {		
		cdao.deleteAllCart(userid);
		}
		
		response.sendRedirect("cartList.do");
	}

}
