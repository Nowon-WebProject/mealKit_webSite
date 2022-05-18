package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.ItemVO2;
import util.DBManager;

public class ItemDAO2 {
	
	private ItemDAO2() {

	}

	private static ItemDAO2 instance = new ItemDAO2();

	public static ItemDAO2 getInstance() {
		
		return instance;
	}

	public List<ItemVO2> selectAllItems() {
		String sql = "select * from item order by item_num desc";
		List<ItemVO2> list = new ArrayList<ItemVO2>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemVO2 iVo2 = new ItemVO2();
				iVo2.setItem_num(rs.getInt("item_num"));
				iVo2.setItem_category(rs.getString("item_category"));
				iVo2.setItem_name(rs.getString("item_name"));
				iVo2.setItem_price(rs.getInt("item_price"));
				iVo2.setItem_quantity(rs.getInt("item_quantity"));
				iVo2.setItem_date(rs.getString("item_date"));
				iVo2.setItem_total(rs.getString("item_total"));
				iVo2.setItem_time(rs.getString("item_time"));
				iVo2.setItem_pictureUrl(rs.getString("item_pictureUrl"));
				list.add(iVo2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}

	public void insertItem(ItemVO2 iVo2) {
		String sql = "insert into item values(item_seq.nextval, ?, ?, ?, ?, ?, sysdate, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iVo2.getItem_category());
			pstmt.setString(2, iVo2.getItem_name());
			pstmt.setString(3, iVo2.getItem_content());
			pstmt.setInt(4, iVo2.getItem_price());
			pstmt.setInt(5, iVo2.getItem_quantity());
			pstmt.setString(6, iVo2.getItem_total());
			pstmt.setString(7, iVo2.getItem_time());
			pstmt.setString(8, iVo2.getItem_pictureUrl());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public ItemVO2 selectItemByItem_num(String item_num) {
		String sql = "select * from item where item_num=?";
		ItemVO2 iVo2 = null;

		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, item_num);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					iVo2 = new ItemVO2();
					iVo2.setItem_num(rs.getInt(1));
					iVo2.setItem_category(rs.getString(2));
					iVo2.setItem_name(rs.getString(3));
					iVo2.setItem_content(rs.getString(4));
					iVo2.setItem_price(rs.getInt(5));
					iVo2.setItem_quantity(rs.getInt(6));
					iVo2.setItem_date(rs.getString(7));
					iVo2.setItem_total(rs.getString(8));
					iVo2.setItem_time(rs.getString(9));
					iVo2.setItem_pictureUrl(rs.getString(10));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return iVo2;
	}

	public void updateItem(ItemVO2 iVo2) {
		String sql = "update item set item_category=?, item_name=?, item_content=?, item_price=?, item_quantity=?, item_date=?, item_total=?, item_time=?, item_pictureUrl=? where item_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iVo2.getItem_category());
			pstmt.setString(2, iVo2.getItem_name());
			pstmt.setString(3, iVo2.getItem_content());
			pstmt.setInt(4, iVo2.getItem_price());
			pstmt.setInt(5, iVo2.getItem_quantity());
			pstmt.setString(6, new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
			pstmt.setString(7, iVo2.getItem_total());
			pstmt.setString(8, iVo2.getItem_time());
			pstmt.setString(9, iVo2.getItem_pictureUrl());
			pstmt.setInt(10, iVo2.getItem_num());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}

	public void deleteItem(String item_num) {
		String sql = "delete from item where item_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public void resetItem_num() {
		String sql = "alter table item auto_increment=1";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

}
