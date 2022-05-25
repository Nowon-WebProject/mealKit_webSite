package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.ItemVO3;
import util.DBManager;

public class ItemDAO3 {
	
	private ItemDAO3() {

	}

	private static ItemDAO3 instance = new ItemDAO3();

	public static ItemDAO3 getInstance() {
		
		return instance;
	}
	
	public List<ItemVO3> selectAllItems(int start, int end) {
		String sql = "select * from(select A.*, Rownum Rnum from(select * from item order by item_num desc)A)" + "where Rnum >= ? and Rnum <= ?";
		List<ItemVO3> list = new ArrayList<ItemVO3>();
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
				ItemVO3 iVo3 = new ItemVO3();
				iVo3.setItem_pictureUrl1(rs.getString("item_pictureUrl1"));
				iVo3.setItem_num(rs.getInt("item_num"));
				iVo3.setItem_category(rs.getString("item_category"));
				iVo3.setItem_name(rs.getString("item_name"));
				iVo3.setItem_price(rs.getInt("item_price"));
				iVo3.setItem_quantity(rs.getInt("item_quantity"));
				iVo3.setItem_date(rs.getString("item_date"));
				iVo3.setItem_total(rs.getString("item_total"));
				iVo3.setItem_time(rs.getString("item_time"));
				iVo3.setItem_main(rs.getString("item_main").charAt(0));
				iVo3.setItem_sales(rs.getString("item_sales"));
				list.add(iVo3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}
		

	public void insertItem(ItemVO3 iVo3) {
		String sql = "insert into item values(?, ?, item_seq.nextval, ?, ?, ?, ?, ?, sysdate, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iVo3.getItem_pictureUrl1());
			pstmt.setString(2, iVo3.getItem_pictureUrl2());
			pstmt.setString(3, iVo3.getItem_category());
			pstmt.setString(4, iVo3.getItem_name());
			pstmt.setString(5, iVo3.getItem_content());
			pstmt.setInt(6, iVo3.getItem_price());
			pstmt.setInt(7, iVo3.getItem_quantity());
			pstmt.setString(8, iVo3.getItem_total());
			pstmt.setString(9, iVo3.getItem_time());
			pstmt.setString(10, String.valueOf(iVo3.getItem_main()));
			pstmt.setString(11, iVo3.getItem_sales());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public ItemVO3 selectItemByItem_num(String item_num) {
		String sql = "select * from item where item_num=?";
		ItemVO3 iVo3 = null;

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
					iVo3 = new ItemVO3();
					iVo3.setItem_pictureUrl1(rs.getString(1));
					iVo3.setItem_pictureUrl2(rs.getString(2));
					iVo3.setItem_num(rs.getInt(3));
					iVo3.setItem_category(rs.getString(4));
					iVo3.setItem_name(rs.getString(5));
					iVo3.setItem_content(rs.getString(6));
					iVo3.setItem_price(rs.getInt(7));
					iVo3.setItem_quantity(rs.getInt(8));
					iVo3.setItem_date(rs.getString(9));
					iVo3.setItem_total(rs.getString(10));
					iVo3.setItem_time(rs.getString(11));
					iVo3.setItem_main(rs.getString(12).charAt(0));
					iVo3.setItem_sales(rs.getString(13));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return iVo3;
	}

	public void updateItem(ItemVO3 iVo3) {
		String sql = "update item set item_pictureUrl1=?, item_pictureUrl2=?, item_category=?, item_name=?, item_content=?, item_price=?, item_quantity=?, item_date=?, item_total=?, item_time=?, item_main=?, item_sales=? where item_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iVo3.getItem_pictureUrl1());
			pstmt.setString(2, iVo3.getItem_pictureUrl2());
			pstmt.setString(3, iVo3.getItem_category());
			pstmt.setString(4, iVo3.getItem_name());
			pstmt.setString(5, iVo3.getItem_content());
			pstmt.setInt(6, iVo3.getItem_price());
			pstmt.setInt(7, iVo3.getItem_quantity());
			pstmt.setString(8, new SimpleDateFormat("yy/MM/dd").format(new Date()));
			pstmt.setString(9, iVo3.getItem_total());
			pstmt.setString(10, iVo3.getItem_time());
			pstmt.setString(11, String.valueOf(iVo3.getItem_main()));
			pstmt.setString(12, iVo3.getItem_sales());
			pstmt.setInt(13, iVo3.getItem_num());
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
	
	/*
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
	*/
	
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
