package kr.co.EZHOME.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import kr.co.EZHOME.domain.DataStatus;
import kr.co.EZHOME.domain.LoginStatus;
import kr.co.EZHOME.domain.User;
import kr.co.EZHOME.dto.UserDTO;
import kr.co.EZHOME.dto.UserVO;

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
				user.setAdmin(rs.getInt("admin"));
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
		int result=-1;
		String sql="insert into usertbl values(?,?,?,?,?,?)";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,udto.getName());
			pstmt.setString(2,udto.getUserid());
			pstmt.setString(3,udto.getPwd());
			pstmt.setString(4,udto.getEmail());
			pstmt.setString(5,udto.getPhone());
			pstmt.setInt(6,udto.getAdmin());
			
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
		
		String sql="update usertbl set pwd = ?, email = ?, phone = ?, admin = ?, name = ? where userid = ?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,udto.getPwd());
			pstmt.setString(2,udto.getEmail());
			pstmt.setString(3,udto.getPhone());
			pstmt.setInt(4,udto.getAdmin());
			pstmt.setString(5,udto.getName());
			pstmt.setString(6,udto.getUserid());
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
	public Vector<UserVO> allSelectMember(){
	      // 가변 길이로 데이터를 저장
	      Vector<UserVO> vec=new Vector<UserVO>();
	      
	      UserVO udto=null;
	      String sql="select * from usertbl";
	      
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      
	      try {
	         conn=getConnection();
	         pstmt=conn.prepareStatement(sql);//
	         rs=pstmt.executeQuery();//
	         
	         while(rs.next()) {
	        	 UserVO mbean=new UserVO();
	            mbean.setName(rs.getString(1));
	            mbean.setUserid(rs.getString(2));
	            mbean.setPwd(rs.getString(3));
	            mbean.setEmail(rs.getString(4));
	            mbean.setPhone(rs.getString(5));
	            mbean.setAdmin(rs.getInt(6));
	            vec.add(mbean);//
	         }
	         conn.close();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      return vec;//
	   }
	public Vector<UserVO> MemberSearch(String type,String key){
	      // 가변 길이로 데이터를 저장
	      Vector<UserVO> vec=new Vector<UserVO>();
	      
	      
	      UserDTO udto=null;
	      String sql="select * from usertbl where "+type+" like "+"'%"+key+"%'";
	      
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      
	     try {
	         conn=getConnection();
	         pstmt=conn.prepareStatement(sql);//
	         rs=pstmt.executeQuery();//
	         
	         while(rs.next()) {
	        	 UserVO mbean=new UserVO();
	            mbean.setName(rs.getString(1));
	            mbean.setUserid(rs.getString(2));
	            mbean.setPwd(rs.getString(3));
	            mbean.setEmail(rs.getString(4));
	            mbean.setPhone(rs.getString(5));
	            mbean.setAdmin(rs.getInt(6));
	            vec.add(mbean);
	         }
	         conn.close();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      
	      
	      return vec;//
	   }
	public int deleteMember(String delete) {
		int result=-1;
		String sql="delete from usertbl where userid = '"+delete+"'";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
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
	public int updateMember2(UserDTO udto) {
		int result=-1;
		
		String sql="update usertbl set pwd = ?, email = ?, phone = ?, admin = ?, name = ? where userid = ?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,udto.getPwd());
			pstmt.setString(2,udto.getEmail());
			pstmt.setString(3,udto.getPhone());
			pstmt.setInt(4,udto.getAdmin());
			pstmt.setString(5,udto.getName());
			pstmt.setString(6,udto.getUserid());
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
