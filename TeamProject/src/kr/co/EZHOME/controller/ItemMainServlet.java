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
 * Servlet implementation class TestServlet
 */
@WebServlet("/itemMain.do")
public class ItemMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemMainServlet() {
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
		
		// 메인화면 서블릿
		// item_main 값을 이용하여
		// 원하는 상품들만 띄울 수 있음.
		// index.jsp 에서 출력할 양을 정할 수 있으며
		// 출력할 아이템에 item_main값만 추가해주면 됨.

		String url = "index.jsp";
		request.setCharacterEncoding("utf-8");

		ItemDAO idao = ItemDAO.getInstance();
		ArrayList<ItemDTO> ilist1 = idao.selectMainItem(1);
		request.setAttribute("ilist1", ilist1);
		
		ArrayList<ItemDTO> ilist2 = idao.selectMainItem(2);
		request.setAttribute("ilist2", ilist2);
		
		ArrayList<ItemDTO> ilist3 = idao.selectMainItem(3);
		request.setAttribute("ilist3", ilist3);
		
		ArrayList<ItemDTO> ilist4 = idao.selectMainItem(4);
		request.setAttribute("ilist4", ilist4);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

	}

}
