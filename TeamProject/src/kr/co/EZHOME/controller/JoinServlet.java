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
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("join.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		request.setCharacterEncoding("utf-8");
		
		String  name=request.getParameter("name");
		String  userid=request.getParameter("userid");
		String  pwd=request.getParameter("pwd");
		String  birth=request.getParameter("birth");
		String  email=request.getParameter("email");
		String  phone=request.getParameter("phone");
		String  rdate=request.getParameter("rdate");
		String  addr=request.getParameter("addr");
		String  deli=request.getParameter("deli");
		String  admin=request.getParameter("admin");
		
		System.out.println(name);
		
		UserDTO udto=new UserDTO();
		udto.setName(name);
		udto.setUserid(userid);
		udto.setPwd(pwd);
		udto.setBirth(birth);
		udto.setEmail(email);
		udto.setPhone(phone);
		udto.setRegistDate(rdate);
		udto.setAddr(addr);
		udto.setDeliAddr(deli);
		udto.setAdmin(Integer.parseInt(admin));
		
		UserDAO udao=UserDAO.getInstance();
		int result=udao.insertMember(udto);
		
		HttpSession session=request.getSession();
		
		if(result == 1) {
			session.setAttribute("userid",udto.getUserid());
			request.setAttribute("message","회원 가입에 성공했습니다.");
		}else {
			request.setAttribute("message","회원 가입에 실패했습니다.");
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
		 dispatcher.forward(request, response);
	}

}
