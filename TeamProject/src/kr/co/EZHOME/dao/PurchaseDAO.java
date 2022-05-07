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
		String sql = "insert into purchaseok values(purchase_seq.nextVal,?,sysdate,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pdto.getUserid());
			pstmt.setInt(2, pdto.getTotal_price());
			pstmt.setString(3, pdto.getAddress());
			pstmt.setString(4, pdto.getDelivery_status());

			pstmt.executeUpdate();
			System.out.println("결제 완료");
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
		String sql = "select purchase_seq, purchase_date, total_price, address, delivery_status from purchaseok where userid=? order by purchase_seq asc";
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
				pdto.setTotal_price(rs.getInt("total_price"));
				pdto.setAddress(rs.getString("address"));
				pdto.setDelivery_status(rs.getString("delivery_status"));
				
				plist.add(pdto);

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
		return plist;
	}
}
