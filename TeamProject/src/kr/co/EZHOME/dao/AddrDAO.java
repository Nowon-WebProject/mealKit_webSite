package kr.co.EZHOME.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.EZHOME.dto.AddrDTO;
import kr.co.EZHOME.dto.CartDTO;

public class AddrDAO {

	private static AddrDAO instance = new AddrDAO();
	
	public static AddrDAO getInstance() {
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
	
	public void insertAddr (AddrDTO adto) {
		String sql = "insert into addrtbl values(deli_addr_seq.nextVal,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adto.getUserid());
			pstmt.setString(2, adto.getDeli_name());
			pstmt.setString(3, adto.getDeli_addr());
			pstmt.setString(4, adto.getDeli_phone());
			pstmt.setString(5, adto.getDeli_msg());
			pstmt.setString(6, adto.getDeli_pwd());

			pstmt.executeUpdate();
			
			System.out.println(adto.getUserid()+"님의 "+adto.getDeli_addr()+"배송지 저장 완료");

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

	
	
	public ArrayList<AddrDTO> selectAddrList(String userid) {
		String sql = "select deli_addr_seq, deli_name, deli_addr, deli_phone, deli_msg, deli_pwd from addrtbl where userid=? order by deli_addr_seq asc";
		ArrayList<AddrDTO> addrList = new ArrayList<AddrDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AddrDTO adto = new AddrDTO();
				
				adto.setDeli_addr_seq(rs.getInt("deli_addr_seq"));
				adto.setDeli_name(rs.getString("deli_name"));
				adto.setDeli_addr(rs.getString("deli_addr"));
				adto.setDeli_phone(rs.getString("deli_phone"));
				adto.setDeli_msg(rs.getString("deli_msg"));
				adto.setDeli_pwd(rs.getString("deli_pwd"));
				
				addrList.add(adto);

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
		return addrList;
	}
	
	public int addrCheck(String deli_postcode, String deli_name, String userid) {
		deli_postcode = "%"+deli_postcode+"%";
		deli_name = "%"+deli_name+"%";
		int addrCheckResult = 0;
		String sql = "select count(*) from addrtbl where (deli_addr like ? and deli_name like ?) and userid=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deli_postcode);
			pstmt.setString(2, deli_name);
			pstmt.setString(3, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				addrCheckResult = rs.getInt(1);
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

		return addrCheckResult;
	}

	public void deleteAddr(int deli_addr_seq) {
		String sql = "delete from addrtbl where deli_addr_seq=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deli_addr_seq);
			pstmt.executeUpdate();
			System.out.println("배송지"+deli_addr_seq+"번 삭제 완료");

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
	
	public int addrCnt(String userid) {
		int addrCnt = 0;
		String sql = "select count(*) from addrtbl where userid=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				addrCnt = rs.getInt(1);
				System.out.println(userid+"님의 저장된 배송지 갯수"+addrCnt);
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

		return addrCnt;
	}
	
	public int oldAddrFind(String userid) {
		int oldAddrSeq = 0;
		String sql = "select deli_addr_seq from (select deli_addr_seq from addrtbl where userid=? order by deli_addr_seq asc) where rownum=1";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				oldAddrSeq = rs.getInt(1);
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

		return oldAddrSeq;
	}
	

}


