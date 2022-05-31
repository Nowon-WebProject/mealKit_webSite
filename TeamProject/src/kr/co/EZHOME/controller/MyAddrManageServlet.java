package kr.co.EZHOME.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.EZHOME.dao.MyAddrDAO;
import kr.co.EZHOME.dto.MyAddrDTO;

/**
 * Servlet implementation class AddrManageServlet
 */
@WebServlet("/myAddrManage.do")
public class MyAddrManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAddrManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 나의 배송지 메인화면 서블릿
		// 저장된 배송지를 출력하고, 배송지 추가를 담당함.
		// 나의 배송지는 5개까지 저장 가능하며, 추가를 원할 시 삭제 후 시도해야 함.
		
		String url="myAddrManage.jsp";
		
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		MyAddrDAO madao=MyAddrDAO.getInstance();
		String deli_nick =request.getParameter("deli_nick");
		if(deli_nick != null) {
		String deli_name =request.getParameter("deli_name");
		String deli_addr = "("+request.getParameter("deli_postcode")+") "+ request.getParameter("deli_addr1") +", "+ request.getParameter("deli_addr2");
		String deli_phone =request.getParameter("deli_phone");
		String deli_msg =request.getParameter("deli_msg");
		String deli_pwd =request.getParameter("deli_pwd");
		String deli_postcode = request.getParameter("deli_postcode");
		System.out.println(deli_nick);
		MyAddrDTO madto = new MyAddrDTO();
		madto.setUserid(userid);
		madto.setMy_deli_nick(deli_nick);
		madto.setMy_deli_name(deli_name);
		madto.setMy_deli_addr(deli_addr);
		madto.setMy_deli_phone(deli_phone);
		madto.setMy_deli_msg(deli_msg);
		madto.setMy_deli_pwd(deli_pwd);
		
		int myAddrCheckResult = madao.addrCheck(deli_postcode, deli_name, userid);
		
			if (myAddrCheckResult == 0) {
				int myAddrCnt = madao.MyAddrCnt(userid);
				if (myAddrCnt > 4) {
					System.out.println("나의 배송지가 5개가 초과되었습니다.");
				} else {
					madao.insertMyAddr(madto);
				}
			} else {
				System.out.println("이미 저장된 주소이므로 저장되지 않습니다.");
			}



		}
			
		
		ArrayList<MyAddrDTO> malist=madao.selectMyAddrList(userid);
		request.setAttribute("malist", malist);
		
		session.setAttribute("myaddrcnt",madao.MyAddrCnt(userid));
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		
	}

}
