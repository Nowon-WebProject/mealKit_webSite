package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.domain.DataStatus;

/**
 * Servlet implementation class CheckUserServlet
 */
@WebServlet("/checkUser.do")
public class CheckUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUserServlet() {
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
		//인증번호 성공시 오게되는 서블릿
		DataStatus result;
		String message = "";
		//1. 해당 핸드폰 번호가 있는 userid 가 있는지 DB에 접근해 확인하기
		UserDAO userDAO = UserDAO.getInstance();
		result = userDAO.checkPhoneUser(request.getParameter("phone"));
		
		//핸드폰번호가 있는 유저가 존재할때
		if (result == DataStatus.Exist) {
			message = "이미 회원가입이 완료된 휴대폰 번호입니다";
		}
		//핸드폰번호가 있는 유저가 존재하지 않을때
		else {
			message = "휴대폰 번호 인증이 완료되었습니다";
		}
		
		request.setAttribute("message", message);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/join/phoneCheckResult.jsp");
		dispatcher.forward(request, response);
	}

}
