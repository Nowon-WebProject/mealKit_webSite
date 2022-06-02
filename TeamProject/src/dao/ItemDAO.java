package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.ItemVO;
import util.DBManager;

public class ItemDAO {
	
	// 파일 안 올리는 버전
	
	private ItemDAO() {

	}

	private static ItemDAO instance = new ItemDAO();

	public static ItemDAO getInstance() {
		
		return instance;
	}

	public List<ItemVO> selectAllItems() {
		String sql = "select * from item order by item_num desc";
		List<ItemVO> list = new ArrayList<ItemVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemVO iVo = new ItemVO();
				iVo.setItem_num(rs.getInt("item_num"));
				iVo.setItem_category(rs.getString("item_category"));
				iVo.setItem_name(rs.getString("item_name"));
				iVo.setItem_price(rs.getInt("item_price"));
				iVo.setItem_quantity(rs.getInt("item_quantity"));
				iVo.setItem_date(rs.getString("item_date"));
				iVo.setItem_total(rs.getString("item_total"));
				iVo.setItem_time(rs.getString("item_time"));
				list.add(iVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}

	public void insertItem(ItemVO iVo) {
		String sql = "insert into item values(item_seq.nextval, ?, ?, ?, ?, ?, sysdate, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iVo.getItem_category());
			pstmt.setString(2, iVo.getItem_name());
			pstmt.setString(3, iVo.getItem_content());
			pstmt.setInt(4, iVo.getItem_price());
			pstmt.setInt(5, iVo.getItem_quantity());
			pstmt.setString(6, iVo.getItem_total());
			pstmt.setString(7, iVo.getItem_time());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public ItemVO selectItemByItem_num(String item_num) {
		String sql = "select * from item where item_num=?";
		ItemVO iVo = null;

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
					iVo = new ItemVO();
					iVo.setItem_num(rs.getInt(1));
					iVo.setItem_category(rs.getString(2));
					iVo.setItem_name(rs.getString(3));
					iVo.setItem_content(rs.getString(4));
					iVo.setItem_price(rs.getInt(5));
					iVo.setItem_quantity(rs.getInt(6));
					iVo.setItem_date(rs.getString(7));
					iVo.setItem_total(rs.getString(8));
					iVo.setItem_time(rs.getString(9));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return iVo;
	}

	public void updateItem(ItemVO iVo) {
		String sql = "update item set item_category=?, item_name=?, item_content=?, item_price=?, item_quantity=?, item_date=?, item_total=?, item_time=? where item_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iVo.getItem_category());
			pstmt.setString(2, iVo.getItem_name());
			pstmt.setString(3, iVo.getItem_content());
			pstmt.setInt(4, iVo.getItem_price());
			pstmt.setInt(5, iVo.getItem_quantity());
			pstmt.setString(6, new SimpleDateFormat("yy/MM/dd").format(new Date()));
			pstmt.setString(7, iVo.getItem_total());
			pstmt.setString(8, iVo.getItem_time());
			pstmt.setInt(9, iVo.getItem_num());
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

}
