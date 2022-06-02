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
	
	// 파일 한 개 올리는 버전
	
	private ItemDAO2() {

	}

	private static ItemDAO2 instance = new ItemDAO2();

	public static ItemDAO2 getInstance() {
		
		return instance;
	}
	
	public List<ItemVO2> selectAllItems(int start, int end) {
		String sql = "select * from(select A.*, Rownum Rnum from(select * from item order by item_num desc)A)" + "where Rnum >= ? and Rnum <= ?";
		List<ItemVO2> list = new ArrayList<ItemVO2>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
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
		String sql = "insert into item values(?, item_seq.nextval, ?, ?, ?, ?, ?, sysdate, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iVo2.getItem_pictureUrl());
			pstmt.setString(2, iVo2.getItem_category());
			pstmt.setString(3, iVo2.getItem_name());
			pstmt.setString(4, iVo2.getItem_content());
			pstmt.setInt(5, iVo2.getItem_price());
			pstmt.setInt(6, iVo2.getItem_quantity());
			pstmt.setString(7, iVo2.getItem_total());
			pstmt.setString(8, iVo2.getItem_time());
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
					iVo2.setItem_pictureUrl(rs.getString(1));
					iVo2.setItem_num(rs.getInt(2));
					iVo2.setItem_category(rs.getString(3));
					iVo2.setItem_name(rs.getString(4));
					iVo2.setItem_content(rs.getString(5));
					iVo2.setItem_price(rs.getInt(6));
					iVo2.setItem_quantity(rs.getInt(7));
					iVo2.setItem_date(rs.getString(8));
					iVo2.setItem_total(rs.getString(9));
					iVo2.setItem_time(rs.getString(10));
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
		String sql = "update item set item_pictureUrl=?, item_category=?, item_name=?, item_content=?, item_price=?, item_quantity=?, item_date=?, item_total=?, item_time=? where item_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iVo2.getItem_pictureUrl());
			pstmt.setString(2, iVo2.getItem_category());
			pstmt.setString(3, iVo2.getItem_name());
			pstmt.setString(4, iVo2.getItem_content());
			pstmt.setInt(5, iVo2.getItem_price());
			pstmt.setInt(6, iVo2.getItem_quantity());
			pstmt.setString(7, new SimpleDateFormat("yy/MM/dd").format(new Date()));
			pstmt.setString(8, iVo2.getItem_total());
			pstmt.setString(9, iVo2.getItem_time());
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
	
	public void deleteAllItems() {
		String sql = "delete from item";
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
	
	public int getAllCount() {
		String sql = "select count(*) from item";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return count;
	}

}
