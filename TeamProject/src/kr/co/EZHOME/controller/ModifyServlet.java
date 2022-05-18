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
 * Servlet implementation class ModifyServlet
 */ 
@WebServlet("/Modify.do")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyServlet() {
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

		String userid =request.getParameter("userid");

		UserDAO udao = UserDAO.getInstance();
		UserDTO udto = udao.getMember(userid);

		request.setAttribute("udto", udto);

		RequestDispatcher dispatcher = request.getRequestDispatcher("modify.jsp");
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
		
		request.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String email=request.getParameter("email")+"@"+request.getParameter("eMailSite");
		String addr= "("+request.getParameter("addr")+") "+ request.getParameter("addr1") +", "+ request.getParameter("addr2");
		UserDAO udao = UserDAO.getInstance();
		int result = udao.userCheck(userid, pwd);
		
			
		//String eMailSite = request.getParameter("eMailSite");
		//String eMailForm = request.getParameter("eMailForm");
		//String test = "직접입력";
		//	if (test.equals(request.getParameter("emailForm"))) {
		//		String email = "@" + eMailSite;
		//	} else {
		//		String email = "@" + eMailForm;
		//	}
			
			
			UserDTO udto = udao.getMember(userid);
			udto.setPwd(request.getParameter("pwd"));
			udto.setEmail(email);
			udto.setPhone(request.getParameter("phone"));
			udto.setAddr(addr);
			
			
			
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
			
			
			result=udao.updateMember(udto);
			
		/*
		 * if (result == 1) { System.out.println("회원정보수정 성공");
		 * response.sendRedirect("index.jsp"); // 성공하면 메인화면으로 } else {
		 * System.out.println("회원정보수정 실패"); response.sendRedirect("modify.jsp"); // 실패하면
		 * modify 머물러 있음. }
		 * 
		 */
					
			String url = "index.jsp";
		 
		  RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		  dispatcher.forward(request, response);
		 
		
	}
		
	}


