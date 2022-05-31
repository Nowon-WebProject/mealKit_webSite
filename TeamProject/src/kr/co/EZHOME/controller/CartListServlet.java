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
import kr.co.EZHOME.dao.ItemDAO;
import kr.co.EZHOME.dto.CartDTO;


/**
 * Servlet implementation class MovieList
 */
@WebServlet("/cartList.do")
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
		
		// 장바구니 리스트를 출력하는 서블릿
		// 세션에 저장된 userid를 이용하여
		// 해당 유저의 장바구니 모두 출력함
		// 장바구니에 담은 뒤, 다른 사용자가 해당 품목에 대해 결제 완료가 될 경우
		// 재고에 대한 갱신이 필요한데,
		// cartItemQuantityModify가 그 역할을 수행함.
		// 장바구니 진입시 재고가 0인 상품이 있다면 품절 메세지
		// 선택해둔 수량보다 재고가 적어진다면 선택한 수량을 자동으로 재고량으로 맞추고 재고량 변동 메세지
		// 작업이 모두 끝나면 장바구니 정보를 clist에 담음.
		// cart.jsp로 이동하기 전 세션의 장바구니갯수정보를 업데이트함.
		
		
		
		String url="cart.jsp";
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		
		CartDAO cdao=CartDAO.getInstance();
		ItemDAO idao=ItemDAO.getInstance();
		ArrayList<CartDTO> clist=cdao.selectCartProduct(userid);
		String message = "";
		int check=0;
		for(int i=0; i<clist.size();i++) {
			int quantity = idao.itemCnt(clist.get(i).getItem_num());
			int cnt = clist.get(i).getItem_cnt();
			int num = clist.get(i).getItem_num();
			int check2 = 0;
			
			cdao.cartItemQuantityModify(quantity, num);
			
			if (quantity == 0) {
				cdao.cartItemCntModify(quantity, num);
				cdao.cartItemQuantityModify(quantity, num);
				message += clist.get(i).getItem_name() + "(품절) ";
				check += 1;
				cdao.deleteCart(clist.get(i).getCart_seq());
				check2 = 1;
				
			}
			
			if (quantity < cnt) {
				cdao.cartItemCntModify(quantity, num);
				check += 1;
				if(check2 == 0) {
				message += clist.get(i).getItem_name() +"(재고량 변동) ";
				}
			}
		
		}
		clist=cdao.selectCartProduct(userid);
		request.setAttribute("clist", clist);
		if(check > 0) {
		request.setAttribute("message", ("[ "+message+"] 상품의 재고 정보가 변동되어 장바구니가 수정됩니다."));
		}
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
