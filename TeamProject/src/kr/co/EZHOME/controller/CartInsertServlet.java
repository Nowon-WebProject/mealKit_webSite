package kr.co.EZHOME.controller;

import java.io.IOException;

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
@WebServlet("/cartInsert.do")
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
		
		// 장바구니에 담기 위한 서블릿
		// 메인화면, 상품리스트, 상품상세정보에서
		// 장바구니에 담을경우 이 서블릿으로 이동함.
		// 비로그인 상태일시 아무것도 이루어지지 않고 왔던 페이지로 이동.
		// 로그인 상태일시 각각의 페이지에서 넘어온 파라미터 값으로
		// cartTBL에 추가함.
		// 만약, 같은 이름의 상품이 이미 장바구니에 있다면
		// 새로 추가하는 것이 아닌, 선택한 수량만큼 기존 수량에 + 됨.
		// 모든 작업이 끝난 뒤, 왔던 페이지로 이동함.
		
		
		
		
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		System.out.println(userid);
		
		
		if (userid == "") {

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
			// 장바구니에 이미 있을 시 수량만 추가.
			// 신규일 시 그대로 추가
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
