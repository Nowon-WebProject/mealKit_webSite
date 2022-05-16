package kr.co.EZHOME.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	
	public int userCheck(String userid,String pwd) {
		int result=-1;
		String sql="select pwd from usertbl where userid=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {
					result=1; 
				}else {
					result=0;
				}
			}else {
				result=-1; 
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
	public UserDTO getMember(String userid) {
		UserDTO udto=null;
		String sql="select * from usertbl where userid=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				udto=new UserDTO();
				udto.setName(rs.getString("name"));
				udto.setUserid(rs.getString("userid"));
				udto.setPwd(rs.getString("pwd"));
				udto.setEmail(rs.getString("email"));
				udto.setPhone(rs.getString("phone"));
				udto.setAdmin(rs.getInt("admin"));
				udto.setAddr(rs.getString("addr"));
				udto.setPoint(rs.getInt("point"));
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
		
		return udto;
	}
	
	public int confrimID(String userid) {
		int result=-1;
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
					result=1; // 아이디 패스워드 일치
			}else {
					result=-1; // 아이디 없음
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
		int result=-1;		// name1, username2, pwd3, birth4, email5, phone6, rdate7, addr8, deli9, point10, admin11
		String sql="insert into usertbl values(?, ?, ?, ?, ?, ?, default, ?, ?, ?, ?)";		
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,udto.getName());
			pstmt.setString(2,udto.getUserid());
			pstmt.setString(3,udto.getPwd());
			pstmt.setString(4,udto.getBirth());
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

	public void addPoint(int point, String userid) {
		String sql="update usertbl set point= point+? where userid=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, userid);
			
			pstmt.executeUpdate();//
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
		
	}
	
	public void minusPoint(int usePoint, String userid) {
		String sql="update usertbl set point= point-? where userid=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, usePoint);
			pstmt.setString(2, userid);
			
			pstmt.executeUpdate();//
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
		
	}
	

	public int nowPoint(String userid) {
		int point = 0;
		String sql = "select point from usertbl where userid=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				point = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return point;
	}
}
