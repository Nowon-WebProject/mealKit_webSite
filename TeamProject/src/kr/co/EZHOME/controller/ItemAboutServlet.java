package kr.co.EZHOME.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.ItemDAO;
import kr.co.EZHOME.dto.ItemDTO;

/**
 * Servlet implementation class ItemAboutServlet
 */
@WebServlet("/itemAbout.do")
public class ItemAboutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemAboutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//상품 상세정보 서블릿
		//메인, 상품리스트, 장바구니 등에서
		//상품 상세정보에 접근할 때, item_num을 갖고 넘어옴.
		//가져온 item_num에 해당하는 정보를 ilist에 담아 넘김. 
		
		
		String url = "itemAbout.jsp";
		request.setCharacterEncoding("utf-8");
		
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		
		ItemDAO idao = ItemDAO.getInstance();
		
		
		
		ArrayList<ItemDTO> ilist = idao.selectItem(item_num); 
		request.setAttribute("ilist", ilist);
		
		
		
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
	}

}
