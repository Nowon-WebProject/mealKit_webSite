package kr.co.EZHOME.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.EZHOME.dto.CartDTO;
import kr.co.EZHOME.dto.PurchaseDTO;

public class PurchaseDAO {
	private static PurchaseDAO instance = new PurchaseDAO();

	public static PurchaseDAO getInstance() {
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
	
	
	public void insertPurchase(PurchaseDTO pdto) {
		String sql = "insert into purchasetbl values(purchase_seq.nextVal,?,sysdate,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pdto.getUserid());
			pstmt.setString(2, pdto.getItem_name());
			pstmt.setInt(3, pdto.getTotal_price());
			pstmt.setString(4, pdto.getDeli_name());
			pstmt.setString(5, pdto.getDeli_addr());
			pstmt.setString(6, pdto.getDeli_phone());
			pstmt.setString(7, pdto.getDeli_msg());
			pstmt.setString(8, pdto.getDeli_pwd());
			pstmt.setString(9, pdto.getDeli_status());
			pstmt.setInt(10, pdto.getUsePoint());

			pstmt.executeUpdate();
			System.out.println(pdto.getUserid()+"님 결제 성공");
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
	
	public ArrayList<PurchaseDTO> selectPurchaseList(String userid) {
		String sql = "select * from purchasetbl where userid=? order by purchase_seq asc";
		ArrayList<PurchaseDTO> plist = new ArrayList<PurchaseDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PurchaseDTO pdto = new PurchaseDTO();
				pdto.setPurchase_seq(rs.getInt("purchase_seq"));
				pdto.setPurchase_date(rs.getString("purchase_date"));
				pdto.setItem_name(rs.getString("item_name"));
				pdto.setTotal_price(rs.getInt("total_price"));
				pdto.setDeli_name(rs.getString("deli_name"));
				pdto.setDeli_addr(rs.getString("deli_addr"));
				pdto.setDeli_phone(rs.getString("deli_phone"));
				pdto.setDeli_msg(rs.getString("deli_msg"));
				pdto.setDeli_pwd(rs.getString("deli_pwd"));
				pdto.setDeli_status(rs.getString("deli_status"));
				pdto.setUsePoint(rs.getInt("usePoint"));
				plist.add(pdto);
			}
			System.out.println(userid+"님의 결제목록 출력 완료");
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
		return plist;
	}

	
}
