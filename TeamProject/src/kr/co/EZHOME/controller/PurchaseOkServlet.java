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
import kr.co.EZHOME.dao.PurchaseDAO;
import kr.co.EZHOME.dto.PurchaseDTO;

/**
 * Servlet implementation class PurchaseOkServlet
 */
@WebServlet("/purchaseok.do")
public class PurchaseOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");

		String userid = request.getParameter("userid");
		int total_price = Integer.parseInt(request.getParameter("total_price"));
		String address = request.getParameter("address");
		String delivery_status = request.getParameter("delivery_status");


		PurchaseDTO pdto = new PurchaseDTO();
		pdto.setUserid(userid);
		pdto.setTotal_price(total_price);
		pdto.setAddress(address);
		pdto.setDelivery_status(delivery_status);
		
		PurchaseDAO pdao = PurchaseDAO.getInstance();
		pdao.insertPurchase(pdto);
		
		CartDAO cdao = CartDAO.getInstance();
		cdao.deleteAllCart(userid);
		
		HttpSession session=request.getSession();
		session.setAttribute("cartcnt",cdao.cartCnt(userid));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("purchaseOk.jsp");
		dispatcher.forward(request, response);
	}

}
