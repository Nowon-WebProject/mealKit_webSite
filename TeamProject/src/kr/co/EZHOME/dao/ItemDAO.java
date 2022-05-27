package kr.co.EZHOME.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

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
		String sql = "select * from (select * from item where item_sales > 0) order by to_number(item_sales) desc";
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
		String sql = "update item set item_sales = item_sales+? where item_num=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart_cnt);
			pstmt.setInt(2, item_num);
			pstmt.executeUpdate();
			System.out.println(item_num + "제품의 판매량 +" + cart_cnt + "개 완료");

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
		String sql = "update item set item_quantity = item_quantity-? where item_num=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart_cnt);
			pstmt.setInt(2, item_num);
			pstmt.executeUpdate();
			System.out.println(item_num + "제품의  재고 -" + cart_cnt + "개 완료");

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

	public int itemCnt(int item_num) {
		int itemCnt = 0;
		String sql = "select item_quantity from item where item_num=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itemCnt = rs.getInt(1);
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

		return itemCnt;
	}

	public String selectItemName(int item_num) {
		String itemName = null;
		String sql = "select item_name from item where item_num=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itemName = rs.getString("item_name");
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

		return itemName;
	}

	public int itemBestCount(String check) {
		int itemCount = 0;
		String sql = "";
		
		if(check.equals("all")) {
			sql = "select count(*) from item order by item_date asc";
		}else if(check.equals("new")) {
			sql = "select count(*) from item order by item_date desc";
		}else if(check.equals("best")) {
			sql = "select count(*) from item where item_sales>1";
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itemCount = rs.getInt(1);
				System.out.println("검색된 값(서치)"+itemCount);
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

		return itemCount;
	}
	
	public Vector<ItemDTO> getBestItem(int start, int end){
		Vector<ItemDTO> vec = new Vector<>();
		String sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_sales>0 order by to_number(item_sales) desc)A) item where Rnum >= ? and Rnum <= ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
				vec.add(idto);
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
		
		return vec;
	}
	
	
	public Vector<ItemDTO> getNewItem(int start, int end){
		Vector<ItemDTO> vec = new Vector<>();
		String sql = "select * from (select A.*, Rownum Rnum from(select * from item order by item_date desc)A) item where Rnum >= ? and Rnum <= ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
				vec.add(idto);
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
		
		return vec;
	}
	
	public Vector<ItemDTO> getAllItem(int start, int end){
		Vector<ItemDTO> vec = new Vector<>();
		String sql = "select * from (select A.*, Rownum Rnum from(select * from item)A) item where Rnum >= ? and Rnum <= ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
				vec.add(idto);
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
		
		return vec;
	}
	
	public Vector<ItemDTO> itemSearch(String keyword,String category, String check, String priceSort, int start, int end){
		Vector<ItemDTO> vec = new Vector<>();
		String sql = "";
		keyword = "%"+keyword+"%";
		
		if(priceSort.equals("high")) {
			if(check.equals("all")) {
				sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? order by item_price desc, item_date asc, item_price desc)A) item where Rnum >= ? and Rnum <= ?";
				if(!category.equals("empty")) {
					sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? and item_category like '"+category+"' order by item_price desc, item_date asc)A) item where Rnum >= ? and Rnum <= ?";	
				}
			}else if(check.equals("new")) {
				sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? order by item_price desc, item_date desc)A) item where Rnum >= ? and Rnum <= ?";
				if(!category.equals("empty")) {
					sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? and item_category like '"+category+"' order by item_price desc, item_date desc)A) item where Rnum >= ? and Rnum <= ?";
				}
			}else if(check.equals("best")) {
				sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? and item_sales>0 order by item_price desc, to_number(item_sales) desc)A) item where Rnum >= ? and Rnum <= ?";	
				if(!category.equals("empty")) {
					sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? and item_category like '"+category+"' and item_sales>0 order by item_price desc, to_number(item_sales) desc)A) item where Rnum >= ? and Rnum <= ?";	
				}
			}
		}else if(priceSort.equals("low")) {
			if(check.equals("all")) {
				sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? order by item_price asc, item_date asc, item_price desc)A) item where Rnum >= ? and Rnum <= ?";
				if(!category.equals("empty")) {
					sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? and item_category like '"+category+"' order by item_price asc, item_date asc)A) item where Rnum >= ? and Rnum <= ?";	
				}
			}else if(check.equals("new")) {
				sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? order by item_price asc, item_date desc)A) item where Rnum >= ? and Rnum <= ?";
				if(!category.equals("empty")) {
					sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? and item_category like '"+category+"' order by item_price asc, item_date desc)A) item where Rnum >= ? and Rnum <= ?";
				}
			}else if(check.equals("best")) {
				sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? and item_sales>0 order by item_price asc, to_number(item_sales) desc)A) item where Rnum >= ? and Rnum <= ?";	
				if(!category.equals("empty")) {
					sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? and item_category like '"+category+"' and item_sales>0 order by item_price asc, to_number(item_sales) desc)A) item where Rnum >= ? and Rnum <= ?";	
				}
			}
		}else if(priceSort.equals("default")) {
			if(check.equals("all")) {
				sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? order by item_date asc, item_price desc)A) item where Rnum >= ? and Rnum <= ?";
				if(!category.equals("empty")) {
					sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? and item_category like '"+category+"' order by item_date asc)A) item where Rnum >= ? and Rnum <= ?";	
				}
			}else if(check.equals("new")) {
				sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? order by item_date desc)A) item where Rnum >= ? and Rnum <= ?";
				if(!category.equals("empty")) {
					sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? and item_category like '"+category+"' order by item_date desc)A) item where Rnum >= ? and Rnum <= ?";
				}
			}else if(check.equals("best")) {
				sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? and item_sales>0 order by to_number(item_sales) desc)A) item where Rnum >= ? and Rnum <= ?";	
				if(!category.equals("empty")) {
					sql = "select * from (select A.*, Rownum Rnum from(select * from item where item_name like ? and item_category like '"+category+"' and item_sales>0 order by to_number(item_sales) desc)A) item where Rnum >= ? and Rnum <= ?";	
				}
			}
		}

			
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
				vec.add(idto);
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
		
		return vec;
	}
	
	public int itemSearchCnt(String keyword, String category, String check){
		keyword = "%"+keyword+"%";
		int itemSearchCnt=0;
		String sql = "";
		if(!category.equals("empty")) {
			sql = "select count(*) from item where item_name like ? and item_category like '"+category+"' ";
			if(check.equals("best")){
			sql = "select count(*) from item where item_sales>0 and item_name like ? and item_category like '"+category+"' ";
			}
		}else {
			sql = "select count(*) from item where item_name like ?";
			if(check.equals("best")){
				sql = "select count(*) from item where item_sales>0 and item_name like ?";
			}
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				itemSearchCnt = rs.getInt(1);
				System.out.println("검색된 값(서치)"+itemSearchCnt);
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
		
		return itemSearchCnt;
	}
	public Vector<ItemDTO> category(){
		Vector<ItemDTO> vec = new Vector<>();
		String sql = "select item_category from item group by item_category order by item_category";
			
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ItemDTO idto = new ItemDTO();
				idto.setItem_category(rs.getString("item_category"));
				vec.add(idto);
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
		
		return vec;
	}
	
}