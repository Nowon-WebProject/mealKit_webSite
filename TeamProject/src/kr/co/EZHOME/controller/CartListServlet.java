package kr.co.EZHOME.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kr.co.EZHOME.dao.CartDAO;
import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.dto.CartDTO;
import kr.co.EZHOME.dto.UserDTO;


/**
 * Servlet implementation class MovieList
 */
@WebServlet("/cartlist.do")
public class CartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String url="cart.jsp";
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		
		CartDAO cdao=CartDAO.getInstance();
		ArrayList<CartDTO> clist=cdao.selectCartProduct(userid);
		request.setAttribute("clist", clist);
		
		System.out.println(clist.size());	
		String message = "";
		for(int i=0; i<clist.size();i++) {
			int quantity = clist.get(i).getItem_quantity();
			int cnt = clist.get(i).getItem_cnt();
			int num = clist.get(i).getItem_num();
			if(quantity < cnt) {
				cdao.cartItemCntModify(quantity, num);
				clist=cdao.selectCartProduct(userid);
				request.setAttribute("clist", clist);
				message += clist.get(i).getItem_name()+",";
				}
			
		}

		
		request.setAttribute("message", ("["+message+"] 상품이 재고량 보다 많아 최대치로 조정합니다."));
		session.setAttribute("cartcnt", cdao.cartCnt(userid));
		
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
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