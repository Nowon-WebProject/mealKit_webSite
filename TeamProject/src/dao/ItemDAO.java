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
	
	// 파일 두 개 및 기타 칼럼 추가 버전
	
	private ItemDAO() {

	}

	private static ItemDAO instance = new ItemDAO();

	public static ItemDAO getInstance() {
		
		return instance;
	}
	
	public List<ItemVO> selectAllItems(int start, int end) {
		String sql = "select * from(select A.*, Rownum Rnum from(select * from item order by item_num desc)A)" + "where Rnum >= ? and Rnum <= ?";
		List<ItemVO> list = new ArrayList<ItemVO>();
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
				ItemVO iVo = new ItemVO();
				iVo.setItem_pictureUrl1(rs.getString("item_pictureUrl1"));
				iVo.setItem_num(rs.getInt("item_num"));
				iVo.setItem_category(rs.getString("item_category"));
				iVo.setItem_name(rs.getString("item_name"));
				iVo.setItem_price(rs.getInt("item_price"));
				iVo.setItem_quantity(rs.getInt("item_quantity"));
				iVo.setItem_date(rs.getString("item_date"));
				iVo.setItem_total(rs.getString("item_total"));
				iVo.setItem_time(rs.getString("item_time"));
				iVo.setItem_main(rs.getString("item_main"));
				iVo.setItem_sales(rs.getInt("item_sales"));
				iVo.setItem_discount(rs.getDouble("item_discount"));
				iVo.setItem_starsAvg(rs.getDouble("item_starsAvg"));
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
		String sql = "insert into item values(?, ?, item_seq.nextval, ?, ?, ?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iVo.getItem_pictureUrl1());
			pstmt.setString(2, iVo.getItem_pictureUrl2());
			pstmt.setString(3, iVo.getItem_category());
			pstmt.setString(4, iVo.getItem_name());
			pstmt.setString(5, iVo.getItem_content());
			pstmt.setInt(6, iVo.getItem_price());
			pstmt.setInt(7, iVo.getItem_quantity());
			pstmt.setString(8, iVo.getItem_total());
			pstmt.setString(9, iVo.getItem_time());
			pstmt.setString(10, iVo.getItem_main());
			pstmt.setInt(11, iVo.getItem_sales());
			pstmt.setDouble(12, iVo.getItem_discount());
			pstmt.setDouble(13, iVo.getItem_starsAvg());
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
					iVo.setItem_pictureUrl1(rs.getString(1));
					iVo.setItem_pictureUrl2(rs.getString(2));
					iVo.setItem_num(rs.getInt(3));
					iVo.setItem_category(rs.getString(4));
					iVo.setItem_name(rs.getString(5));
					iVo.setItem_content(rs.getString(6));
					iVo.setItem_price(rs.getInt(7));
					iVo.setItem_quantity(rs.getInt(8));
					iVo.setItem_date(rs.getString(9));
					iVo.setItem_total(rs.getString(10));
					iVo.setItem_time(rs.getString(11));
					iVo.setItem_main(rs.getString(12));
					iVo.setItem_sales(rs.getInt(13));
					iVo.setItem_discount(rs.getDouble(14));
					iVo.setItem_starsAvg(rs.getDouble(15));
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
		String sql = "update item set item_pictureUrl1=?, item_pictureUrl2=?, item_category=?, item_name=?, item_content=?, item_price=?, item_quantity=?, item_date=?, item_total=?, item_time=?, item_main=?, item_sales=?, item_discount=?, item_starsAvg=? where item_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iVo.getItem_pictureUrl1());
			pstmt.setString(2, iVo.getItem_pictureUrl2());
			pstmt.setString(3, iVo.getItem_category());
			pstmt.setString(4, iVo.getItem_name());
			pstmt.setString(5, iVo.getItem_content());
			pstmt.setInt(6, iVo.getItem_price());
			pstmt.setInt(7, iVo.getItem_quantity());
			pstmt.setString(8, new SimpleDateFormat("yy/MM/dd").format(new Date()));
			pstmt.setString(9, iVo.getItem_total());
			pstmt.setString(10, iVo.getItem_time());
			pstmt.setString(11, iVo.getItem_main());
			pstmt.setInt(12, iVo.getItem_sales());
			pstmt.setDouble(13, iVo.getItem_discount());
			pstmt.setDouble(14, iVo.getItem_starsAvg());
			pstmt.setInt(15, iVo.getItem_num());
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
		String sql1 = "delete from item";
		String sql2 = "drop sequence item_seq";
		String sql3 = "create sequence item_seq start with 1 increment by 1";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(sql2);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(sql3);
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
