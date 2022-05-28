package kr.co.EZHOME.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.EZHOME.domain.User;
import kr.co.EZHOME.dto.BbsDTO;
import kr.co.EZHOME.dto.UserDTO;

public class BbsDAO {
	
	private BbsDAO() {

	}

	private static BbsDAO instance = new BbsDAO();

	public static BbsDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception {
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/Oracle11g");
		conn = ds.getConnection();

		return conn;
	}
	
	public int bbsid() {
		Connection conn=null;
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		int result=-1;
		String sql ="select bbsid from bbs order by bbsid desc";
		
		try {
			conn=getConnection();
			ptmt=conn.prepareStatement(sql);
			rs=ptmt.executeQuery();
			
			if(rs.next()) {
			result=rs.getInt(1)+1;
			}else { result = 1;}
			

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int bbsWrite(BbsDTO bdto) {
		int result =-1;
		
		Connection conn=null;
		PreparedStatement ptmt=null;
		
		String sql ="insert into bbs values(bbs_seq.nextval,?,?,?,default)"; 
		
		try {
			conn=getConnection();
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1, bdto.getUserid());
			ptmt.setString(2, bdto.getBbstitle());
			ptmt.setString(3, bdto.getBbscontent());
			result=ptmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public Vector<BbsDTO> bbsList() {
		Connection conn=null;
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		Vector<BbsDTO> vec=new Vector<BbsDTO>();
		
		String sql ="select * from bbs order by bbsid desc";
		
		try {
			conn=getConnection();
			ptmt=conn.prepareStatement(sql);
			rs=ptmt.executeQuery();
			
			while(rs.next()) {
				BbsDTO bdto=new BbsDTO();
				bdto.setBbsid(rs.getInt("bbsid"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setBbstitle(rs.getString("bbstitle"));
				bdto.setBbscontent(rs.getString("bbscontent"));
				
				if(rs.getDate("bbsdate") == null) {
					bdto.setBbsdate(null);
				}else {bdto.setBbsdate(rs.getDate("bbsdate").toString());}
				
				vec.add(bdto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return vec;
	}
	
	public BbsDTO findUser (String bbsid) {
		String sql="select * from bbs where bbsid=?";
		BbsDTO bdto=new BbsDTO();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bbsid);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()) {
				bdto.setBbsid(rs.getInt("bbsid"));
				bdto.setBbstitle(rs.getString("bbstitle"));
				bdto.setBbscontent(rs.getString("bbscontent"));
				bdto.setUserid(rs.getString("userid"));
				if(rs.getDate("bbsdate") == null) {
					bdto.setBbsdate(null);
				}else {bdto.setBbsdate(rs.getDate("bbsdate").toString());}
			}
		}catch(Exception e) {
			e.printStackTrace();
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
		
		return bdto;
	}
	
	public int updateMember(BbsDTO udto) {
		int result=-1;
		String sql="update bbs set bbstitle = ?, bbscontent = ? where bbsid = ?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, udto.getBbstitle());
			pstmt.setString(2, udto.getBbscontent());
			pstmt.setInt(3, udto.getBbsid());
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
	
	public int deleteMember(String userid) {
		int answer=-1;
		Connection conn = null;
		PreparedStatement ptmt = null;
		
		String sql="delete from bbs where bbsid = ?";
		
		try {
			conn=getConnection();
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1, userid);
			answer=ptmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if( conn != null) 
					conn.close();
				if( ptmt != null)
					ptmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return answer;
	}
}
