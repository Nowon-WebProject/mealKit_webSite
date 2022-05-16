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
import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.dto.PurchaseDTO;
import kr.co.EZHOME.dto.UserDTO;

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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");

		String userid = request.getParameter("userid");
		String item_name = request.getParameter("item_name");
		int total_price = Integer.parseInt(request.getParameter("total_price")); // 배송비, 적립금 포함 
		String deli_name = request.getParameter("deli_name");
		String deli_addr = request.getParameter("deli_addr");
		String deli_phone = request.getParameter("deli_phone");
		String deli_msg = request.getParameter("deli_msg");
		String deli_pwd = request.getParameter("deli_pwd");
		String deli_status = request.getParameter("deli_status");
		int usePoint =  Integer.parseInt(request.getParameter("usePoint"));
		int point =  Integer.parseInt(request.getParameter("point"));
		

		PurchaseDTO pdto = new PurchaseDTO();
		pdto.setUserid(userid);
		pdto.setItem_name(item_name);
		pdto.setTotal_price(total_price);
		pdto.setDeli_name(deli_name);
		pdto.setDeli_addr(deli_addr);
		pdto.setDeli_phone(deli_phone);
		pdto.setDeli_msg(deli_msg);
		pdto.setDeli_pwd(deli_pwd);
		pdto.setDeli_status(deli_status);
		pdto.setUsePoint(usePoint);
		
		PurchaseDAO pdao = PurchaseDAO.getInstance();
		pdao.insertPurchase(pdto);
		
		CartDAO cdao = CartDAO.getInstance();
		cdao.deleteAllCart(userid);
		
		UserDTO udto = new UserDTO();
		udto.setUserid(userid);
		udto.setPoint(point);
		UserDAO udao = UserDAO.getInstance();
		udao.minusPoint(usePoint ,userid);
		udao.addPoint(point, userid);
	
		
		HttpSession session=request.getSession();
		session.setAttribute("cartcnt",cdao.cartCnt(userid));
		session.setAttribute("point", udao.nowPoint(userid));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("purchaseOk.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

	}

}
