package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.EZHOME.domain.SendMessage;

/**
 * Servlet implementation class SendMessageServlet
 */
@WebServlet("/SendMessage.do")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String certificationNumber;
		SendMessage send = new SendMessage();
		//발송횟수
		int sendCount = sendCount(request);
		//발송횟수가 5회 이하일 경우에만 재발송을 해준다
		if (sendCount < 5) {
			certificationNumber = send.sendMessage(request.getParameter("phone"));
			request.setAttribute("certificationNumber", certificationNumber);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/join/phoneCheck.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public int sendCount(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int count = 1;
		//처음 전송했을떄 1로 초기화
		if (!(request.getParameter("first") == null)) {
			session.setAttribute("sendCount", 1);
		}
		//발송횟수가 1회 이상일경우 이전 session 값 가져와서 +1
		else {
			count = (Integer)session.getAttribute("sendCount") + 1;
			session.setAttribute("sendCount", count);
		}
		
		return count;
	}
}
