package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.dto.UserDTO;

/**
 * Servlet implementation class DeleteOkServlet
 */
@WebServlet("/DeleteOk.do")
public class DeleteOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOkServlet() {
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
		request.setCharacterEncoding("UTF-8");
		
		String url = "deleteOK.jsp";
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");

		UserDAO udao = UserDAO.getInstance();
		//UserDTO udto = udao.getMember(userid);
		
		int result=udao.userCheck(userid, pwd);
		if (result == 1) {
			UserDTO udto = udao.getMember(userid);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", udto);
			
			
			session.setAttribute("name", udto.getName());
			session.setAttribute("id", udto.getUserid());
			session.setAttribute("pwd",udto.getPwd());
			session.setAttribute("birth", udto.getBirth());
			session.setAttribute("email",udto.getEmail());
			session.setAttribute("phone",udto.getPhone());
			session.setAttribute("rdate", udto.getRegistDate());
			session.setAttribute("addr", udto.getAddr());
			session.setAttribute("deli", udto.getDeliAddr());
			session.setAttribute("admin", udto.getAdmin());
			
			
			session.setAttribute("result", result);
			
			request.setAttribute("message", "로그인 되었습니다.");
			url="deleteTerms.jsp";
		} else if (result == 0) {
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
		} 
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
 


}
