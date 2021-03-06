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
		
		//RequestDispatcher dispatcher=request.getRequestDispatcher("join.jsp");
		//dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
 		request.setCharacterEncoding("utf-8");
		
		String name=request.getParameter("name");
		String userid=request.getParameter("userid");
		String pwd=request.getParameter("pwd");
		String phone=request.getParameter("checkedPhone");
		String admin=request.getParameter("admin");
		String birth = request.getParameter("birth"); 
		
		//email email + @ + emailSite
		String email = null;
		if (request.getParameter("email") != "" && request.getParameter("eMailSite") != "") {
			email = request.getParameter("email") + "@" + request.getParameter("eMailSite");
		}
		
		//address ??? ????????????
		String addr1 = request.getParameter("addr1");
		//????????? ?????????
		String roadAddr = request.getParameter("roadAddr");
		String addr3 = request.getParameter("addr3");
		
		String addr = "("+addr1 + ") " + roadAddr + ", " + addr3;
		
		UserDTO udto=new UserDTO(name, userid, pwd, birth, email, phone, null, addr, null, 0, Integer.parseInt(admin));

		UserDAO udao=UserDAO.getInstance();
		int result=udao.insertMember(udto);
		
		HttpSession session=request.getSession();
		
		if(result == 1) {
			session.setAttribute("userid", udto.getUserid());
			request.setAttribute("message","?????? ????????? ??????????????????.");
		}else {
			request.setAttribute("message","?????? ????????? ??????????????????.");
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/login/login.jsp");
		 dispatcher.forward(request, response);
	}

}
