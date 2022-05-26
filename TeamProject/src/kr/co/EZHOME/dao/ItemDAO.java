package kr.co.EZHOME.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.EZHOME.dto.CartDTO;
import kr.co.EZHOME.dto.ItemDTO;

public class ItemDAO {

	private ItemDAO() {

	}

	private static ItemDAO instance = new ItemDAO();

	public static ItemDAO getInstance() {
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

	public ArrayList<ItemDTO> selectItem(int item_num) {
		String sql = "select * from item where item_num=?";
		ArrayList<ItemDTO> itemList = new ArrayList<ItemDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemDTO idto = new ItemDTO();
				idto.setItem_pictureUrl1(rs.getString("item_pictureurl1"));
				idto.setItem_pictureUrl2(rs.getString("item_pictureurl2"));
				idto.setItem_num(rs.getInt("item_num"));
				idto.setItem_category(rs.getString("item_category"));
				idto.setItem_name(rs.getString("item_name"));
				idto.setItem_content(rs.getString("item_content"));
				idto.setItem_price(rs.getInt("item_price"));
				idto.setItem_quantity(rs.getInt("item_quantity"));
				idto.setItem_date(rs.getString("item_date"));
				idto.setItem_total(rs.getString("item_total"));
				idto.setItem_time(rs.getString("item_time"));
				idto.setItem_main(rs.getString("item_main"));
				idto.setItem_sales(rs.getString("item_sales"));
				
				itemList.add(idto);

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
		return itemList;
	}
	
	public ArrayList<ItemDTO> selectMainItem() {
		String sql = "select * from item where item_main=1";
		ArrayList<ItemDTO> itemList = new ArrayList<ItemDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemDTO idto = new ItemDTO();
				idto.setItem_pictureUrl1(rs.getString("item_pictureurl1"));
				idto.setItem_pictureUrl2(rs.getString("item_pictureurl2"));
				idto.setItem_num(rs.getInt("item_num"));
				idto.setItem_category(rs.getString("item_category"));
				idto.setItem_name(rs.getString("item_name"));
				idto.setItem_content(rs.getString("item_content"));
				idto.setItem_price(rs.getInt("item_price"));
				idto.setItem_quantity(rs.getInt("item_quantity"));
				idto.setItem_date(rs.getString("item_date"));
				idto.setItem_total(rs.getString("item_total"));
				idto.setItem_time(rs.getString("item_time"));
				idto.setItem_main(rs.getString("item_main"));
				idto.setItem_sales(rs.getString("item_sales"));
				
				itemList.add(idto);

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
		return itemList;
	}
	
	public ArrayList<ItemDTO> selectBestItem() {
		String sql = "select * from item order by to_number(item_sales) desc";
		ArrayList<ItemDTO> itemList = new ArrayList<ItemDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ItemDTO idto = new ItemDTO();
				idto.setItem_pictureUrl1(rs.getString("item_pictureurl1"));
				idto.setItem_pictureUrl2(rs.getString("item_pictureurl2"));
				idto.setItem_num(rs.getInt("item_num"));
				idto.setItem_category(rs.getString("item_category"));
				idto.setItem_name(rs.getString("item_name"));
				idto.setItem_content(rs.getString("item_content"));
				idto.setItem_price(rs.getInt("item_price"));
				idto.setItem_quantity(rs.getInt("item_quantity"));
				idto.setItem_date(rs.getString("item_date"));
				idto.setItem_total(rs.getString("item_total"));
				idto.setItem_time(rs.getString("item_time"));
				idto.setItem_main(rs.getString("item_main"));
				idto.setItem_sales(rs.getString("item_sales"));
				
				itemList.add(idto);
				
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
		return itemList;
	}
	
	public ArrayList<ItemDTO> selectNewItem() {
		String sql = "select * from item order by item_date desc";
		ArrayList<ItemDTO> itemList = new ArrayList<ItemDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ItemDTO idto = new ItemDTO();
				idto.setItem_pictureUrl1(rs.getString("item_pictureurl1"));
				idto.setItem_pictureUrl2(rs.getString("item_pictureurl2"));
				idto.setItem_num(rs.getInt("item_num"));
				idto.setItem_category(rs.getString("item_category"));
				idto.setItem_name(rs.getString("item_name"));
				idto.setItem_content(rs.getString("item_content"));
				idto.setItem_price(rs.getInt("item_price"));
				idto.setItem_quantity(rs.getInt("item_quantity"));
				idto.setItem_date(rs.getString("item_date"));
				idto.setItem_total(rs.getString("item_total"));
				idto.setItem_time(rs.getString("item_time"));
				idto.setItem_main(rs.getString("item_main"));
				idto.setItem_sales(rs.getString("item_sales"));
				
				itemList.add(idto);
				
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
		return itemList;
	}
	
	public ArrayList<ItemDTO> selectAllItem() {
		String sql = "select * from item order by item_date asc";
		ArrayList<ItemDTO> itemList = new ArrayList<ItemDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ItemDTO idto = new ItemDTO();
				idto.setItem_pictureUrl1(rs.getString("item_pictureurl1"));
				idto.setItem_pictureUrl2(rs.getString("item_pictureurl2"));
				idto.setItem_num(rs.getInt("item_num"));
				idto.setItem_category(rs.getString("item_category"));
				idto.setItem_name(rs.getString("item_name"));
				idto.setItem_content(rs.getString("item_content"));
				idto.setItem_price(rs.getInt("item_price"));
				idto.setItem_quantity(rs.getInt("item_quantity"));
				idto.setItem_date(rs.getString("item_date"));
				idto.setItem_total(rs.getString("item_total"));
				idto.setItem_time(rs.getString("item_time"));
				idto.setItem_main(rs.getString("item_main"));
				idto.setItem_sales(rs.getString("item_sales"));
				
				itemList.add(idto);
				
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
		return itemList;
	}
	
	public void itemSales(int cart_cnt, int item_num) {
		String sql="update item set item_sales = item_sales+? where item_num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart_cnt);
			pstmt.setInt(2, item_num);
			pstmt.executeUpdate();
			System.out.println(item_num+"제품의 판매량 +"+cart_cnt+"개 완료");

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
	
	public void itemQuantity(int cart_cnt, int item_num) {
		String sql="update item set item_quantity = item_quantity-? where item_num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart_cnt);
			pstmt.setInt(2, item_num);
			pstmt.executeUpdate();
			System.out.println(item_num+"제품의  재고 -"+cart_cnt+"개 완료");
			
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
	

		
}
