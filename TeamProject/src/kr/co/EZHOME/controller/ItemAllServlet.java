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
@WebServlet("/itemall.do")
public class ItemAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemAllServlet() {
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

		String url = "itemAll.jsp";
		request.setCharacterEncoding("utf-8");

		ItemDAO idao = ItemDAO.getInstance();
		ArrayList<ItemDTO> ilist = idao.selectAllItem();
		request.setAttribute("ilist", ilist);

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