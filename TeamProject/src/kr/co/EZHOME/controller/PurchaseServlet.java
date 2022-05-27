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

import kr.co.EZHOME.dao.AddrDAO;
import kr.co.EZHOME.dao.CartDAO;
import kr.co.EZHOME.dao.ItemDAO;
import kr.co.EZHOME.dao.MyAddrDAO;
import kr.co.EZHOME.dto.AddrDTO;
import kr.co.EZHOME.dto.CartDTO;
import kr.co.EZHOME.dto.UserDTO;

/**
 * Servlet implementation class PurchaseServlet
 */
@WebServlet("/purchase.do")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseServlet() {
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
		String url="purchase.jsp";
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		CartDAO cdao=CartDAO.getInstance();
		ItemDAO idao=ItemDAO.getInstance();
		ArrayList<CartDTO> clist=cdao.selectCartProduct(userid);
		request.setAttribute("clist", clist);
		String message2 = "";
		for(int i=0; i<clist.size();i++) {
			int quantity = idao.itemCnt(clist.get(i).getItem_num());
			int cnt = clist.get(i).getItem_cnt();
			if(quantity < cnt) {
				message2 += clist.get(i).getItem_name()+" ";
				}
		}

		
		request.setAttribute("message2", ("[ "+message2+"] 상품의 재고량에 문제가 생겼습니다. 다시 확인해주세요"));
		session.setAttribute("cartcnt", cdao.cartCnt(userid));
		
		
		
		AddrDAO adao=AddrDAO.getInstance();
		ArrayList<AddrDTO> alist=adao.selectAddrList(userid);
		request.setAttribute("alist", alist);
		session.setAttribute("addrcnt", adao.addrCnt(userid));
		
		MyAddrDAO madao=MyAddrDAO.getInstance();
		session.setAttribute("myaddrcnt",madao.addrCnt(userid));
		

				
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
			
		
		
		
					
	}

}
