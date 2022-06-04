package kr.co.EZHOME.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.EZHOME.domain.DAOResult;
import kr.co.EZHOME.domain.DataStatus;
import kr.co.EZHOME.domain.LoginStatus;
import kr.co.EZHOME.domain.User;
import kr.co.EZHOME.dto.UserDTO;

public class UserDAO {

	private UserDAO() {

	}

	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	public Connection getConnection() throws Exception {
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext=(Context)initContext.lookup("java:/comp/env");
		DataSource ds =(DataSource)envContext.lookup("jdbc/Oracle11g");
		conn=ds.getConnection();
		
		return conn;
	}

	//비밀번호 변경
	public DAOResult updatePassword(String userId, String password) {
		String sql = "update usertbl set pwd=? where userid=?";
		DAOResult result = DAOResult.Failed;
		int resultNum;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, userId);
			resultNum = pstmt.executeUpdate();

			if (resultNum > 0) {
				result = DAOResult.Success;
			}
			else {
				result = DAOResult.Failed;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	// 해당 핸드폰 번호와 이름을 가진 유저가 있는지 확인하기
	public DataStatus checkPhoneUser(String name, String phone, String userId) {
		String sql = "select userid from usertbl where name=? AND phone=? AND userid=?";
		DataStatus result = DataStatus.Invalid_InputValue;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = DataStatus.Exist;
			}
			else {
				result = DataStatus.Not_Exist;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 해당 핸드폰 번호와 이름을 가진 유저가 있는지 확인하기
	public String checkPhoneUser(String name, String phone) {
		String sql = "select userid from usertbl where name=? AND phone=?";
		String result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getString("userid");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	//해당 핸드폰 번호를 가진 유저가 있는지 확인하기
	public DataStatus checkPhoneUser(String phone) {
		DataStatus result = DataStatus.Invalid_InputValue;
		String sql = "select userid from usertbl where phone=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = DataStatus.Exist;
			}
			else {
				result = DataStatus.Not_Exist;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	//메시지 전송할 때 필요한 apikey, apiscret 가져오기
	public String[] getCoolSMS() {
		String sql="select * from smsapikey";
		String[] api = new String[2];
		
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				api[0] = rs.getString("apikey");
				api[1] = rs.getString("apisecret");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return api;
	}
	
	//유저정보 가져오기
	public User findUser (String userid) throws Exception {
		String sql="select * from usertbl where userid=?";
		User user = new User();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				user.setName(rs.getString("name"));
				user.setUserid(rs.getString("userid"));
				user.setPassword(rs.getString("pwd"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setAddr(rs.getString("addr"));
				user.setDeli(rs.getString("deli"));
				user.setPoint(rs.getInt("point"));
				user.setAdmin(rs.getInt("admin"));
				//Date값이 null 일때 toString 메서드를 사용하면 NullPointerException 에러가 발생한다
				if (rs.getDate("birth") == null) {
					user.setBirth(null);
				}
				else {
					user.setBirth(rs.getDate("birth").toString());
				}
				if (rs.getDate("RegistDate") == null) {
					user.setRegistDate(null);
				}
				else {
					user.setRegistDate(rs.getDate("RegistDate").toString());
				}
			}
			else {
				throw new IllegalArgumentException("존재하지 않는 회원입니다.");
			}
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}

	//userCheck의 pwd 비교하는 기능을 domain 패키지의 User 객체에서 수행하도록 변경
//	public LoginStatus userCheck(String userid,String pwd) {
//		LoginStatus result = null;
//		String sql="select pwd from usertbl where userid=?";
//		
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//			conn=getConnection();
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setString(1, userid);
//			rs=pstmt.executeQuery();
//			
//			if(rs.next()) {
//				if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {
//					result= LoginStatus.LOGIN_SUCCESS; 
//				}else {
//					result= LoginStatus.PASSWORD_WRONG;
//				}
//			}else {
//				result= LoginStatus.NOT_EXIST_USER;
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(rs != null)
//					rs.close();
//				if(pstmt != null)
//					pstmt.close();
//				if(conn != null)
//					conn.close();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return result;
//	}
	
	public DataStatus confrimID(String userid) {
		DataStatus result = null;
		String sql="select userid from usertbl where userid=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
					result = DataStatus.Exist; // 아이디 일치
			}else {
					result = DataStatus.Not_Exist; // 아이디 없음
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int insertMember(UserDTO udto) {
		int result=-1;				// name1, username2, pwd3, birth4, email5, phone6, rdate7, addr8, deli9, point10, admin11
		String sql="insert into usertbl values(?, ?, ?, ?, ?, ?, default, ?, ?, ?, ?)";		
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,udto.getName());
			pstmt.setString(2,udto.getUserid());
			pstmt.setString(3,udto.getPwd());
			//생년월일을 안넣었을 경우 transfromDate를 하지 않음
			if (udto.getBirth() == "") {
				pstmt.setDate(4, null);
			}
			else {
				pstmt.setDate(4,udto.transformDate(udto.getBirth()));
			}
			pstmt.setString(5,udto.getEmail());
			pstmt.setString(6,udto.getPhone());
			pstmt.setString(7,udto.getAddr());
			pstmt.setString(8,udto.getDeliAddr());
			pstmt.setInt(9,udto.getPoint());
			pstmt.setInt(10,udto.getAdmin());
			
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int updateMember(UserDTO udto) {
		int result=-1;
		String sql="update usertbl set pwd=?,email=?,phone=?,admin=? where userid=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,udto.getPwd());
			pstmt.setString(2,udto.getEmail());
			pstmt.setString(3,udto.getPhone());
			pstmt.setInt(4,udto.getAdmin());
			pstmt.setString(5,udto.getUserid());
			
			result=pstmt.executeUpdate();//
			System.out.println("result="+result);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
