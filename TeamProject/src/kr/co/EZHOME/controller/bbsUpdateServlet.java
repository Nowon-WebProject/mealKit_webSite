package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.BbsDAO;
import kr.co.EZHOME.dto.BbsDTO;

/**
 * Servlet implementation class bbsUpdateServlet
 */
@WebServlet("/bbsUpdate.do")
public class bbsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bbsUpdateServlet() {
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
		request.setCharacterEncoding("UTF-8");
		BbsDAO bdao=BbsDAO.getInstance();
		BbsDTO bdto=new BbsDTO();
		
		bdto.setBbsid(Integer.parseInt(request.getParameter("bbsid")));
		bdto.setBbstitle(request.getParameter("bbstitle"));
		bdto.setBbscontent(request.getParameter("bbscontent"));
		
		int result = bdao.updateMember(bdto);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("bbsList.do");
		dispatcher.forward(request, response);
		
		
	}

}
