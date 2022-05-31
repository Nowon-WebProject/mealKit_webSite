package kr.co.EZHOME.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.EZHOME.dto.CartDTO;
import kr.co.EZHOME.dto.MyAddrDTO;
import kr.co.EZHOME.dto.UserDTO;

public class MyAddrDAO {

	private static MyAddrDAO instance = new MyAddrDAO();
	
	public static MyAddrDAO getInstance() {
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
	
	public void insertMyAddr (MyAddrDTO madto) {
		String sql = "insert into myaddrtbl values(my_deli_addr_seq.nextVal,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, madto.getUserid());
			pstmt.setString(2, madto.getMy_deli_nick());
			pstmt.setString(3, madto.getMy_deli_name());
			pstmt.setString(4, madto.getMy_deli_addr());
			pstmt.setString(5, madto.getMy_deli_phone());
			pstmt.setString(6, madto.getMy_deli_msg());
			pstmt.setString(7, madto.getMy_deli_pwd());

			pstmt.executeUpdate();
			
			System.out.println(madto.getUserid()+"님의 "+madto.getMy_deli_addr()+"배송지 저장 완료");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	
	
	public ArrayList<MyAddrDTO> selectMyAddrList(String userid) {
		String sql = "select my_deli_addr_seq, my_deli_nick ,my_deli_name, my_deli_addr, my_deli_phone, my_deli_msg, my_deli_pwd from myaddrtbl where userid=? order by my_deli_addr_seq asc";
		ArrayList<MyAddrDTO> myaddrList = new ArrayList<MyAddrDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MyAddrDTO madto = new MyAddrDTO();
				
				madto.setMy_deli_addr_seq(rs.getInt("my_deli_addr_seq"));
				madto.setMy_deli_nick(rs.getString("my_deli_nick"));
				madto.setMy_deli_name(rs.getString("my_deli_name"));
				madto.setMy_deli_addr(rs.getString("my_deli_addr"));
				madto.setMy_deli_phone(rs.getString("my_deli_phone"));
				madto.setMy_deli_msg(rs.getString("my_deli_msg"));
				madto.setMy_deli_pwd(rs.getString("my_deli_pwd"));
				
				myaddrList.add(madto);

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
		return myaddrList;
	}
	
	public int addrCheck(String deli_postcode, String my_deli_name, String userid) {
		deli_postcode = "%"+deli_postcode+"%";
		my_deli_name = "%"+my_deli_name+"%";
		int myAddrCheckResult = 0;
		String sql = "select count(*) from myaddrtbl where (my_deli_addr like ? and my_deli_name like ?) and userid=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deli_postcode);
			pstmt.setString(2, my_deli_name);
			pstmt.setString(3, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				myAddrCheckResult = rs.getInt(1);
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

		return myAddrCheckResult;
	}

	public void deleteAddr(int my_deli_addr_seq) {
		String sql = "delete from myaddrtbl where my_deli_addr_seq=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, my_deli_addr_seq);
			pstmt.executeUpdate();
			System.out.println("배송지"+my_deli_addr_seq+"번 삭제 완료");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public int MyAddrCnt(String userid) {
		int MyAddrCnt = 0;
		String sql = "select count(*) from myaddrtbl where userid=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MyAddrCnt = rs.getInt(1);
				System.out.println(userid+"님의 저장된 배송지 갯수"+MyAddrCnt);
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

		return MyAddrCnt;
	}
	
	public void updateMyAddr(MyAddrDTO madto) {
		String sql="update myaddrtbl set my_deli_nick=?, my_deli_name=?, my_deli_addr=?, my_deli_phone=?, my_deli_msg=?, my_deli_pwd=? where my_deli_addr_seq=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,madto.getMy_deli_nick());
			pstmt.setString(2,madto.getMy_deli_name());
			pstmt.setString(3,madto.getMy_deli_addr());
			pstmt.setString(4,madto.getMy_deli_phone());
			pstmt.setString(5,madto.getMy_deli_msg());
			pstmt.setString(6,madto.getMy_deli_pwd());
			pstmt.setInt(7,madto.getMy_deli_addr_seq());
			
			pstmt.executeUpdate();
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

	
}


