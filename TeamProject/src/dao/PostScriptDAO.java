package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.PostScriptVO;
import util.DBManager;

public class PostScriptDAO {
	private PostScriptDAO() {

	}

	private static PostScriptDAO instance = new PostScriptDAO();

	public static PostScriptDAO getInstance() {
		
		return instance;
	}
	
	/*
	public List<PostScriptVO> selectAllItems(int start, int end) {
		String sql = "select * from(select A.*, Rownum Rnum from(select * from item order by item_num desc)A)" + "where Rnum >= ? and Rnum <= ?";
		List<PostScriptVO> list = new ArrayList<PostScriptVO>();
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
				PostScriptVO pVo = new PostScriptVO();
				pVo.setItem_pictureUrl1(rs.getString("item_pictureUrl1"));
				pVo.setItem_num(rs.getInt("item_num"));
				pVo.setItem_category(rs.getString("item_category"));
				pVo.setItem_name(rs.getString("item_name"));
				pVo.setItem_price(rs.getInt("item_price"));
				pVo.setItem_quantity(rs.getInt("item_quantity"));
				pVo.setItem_date(rs.getString("item_date"));
				pVo.setItem_total(rs.getString("item_total"));
				pVo.setItem_time(rs.getString("item_time"));
				pVo.setItem_main(rs.getString("item_main").charAt(0));
				pVo.setItem_sales(rs.getInt("item_sales"));
				list.add(pVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}
		

	public void insertItem(PostScriptVO pVo) {
		String sql = "insert into item values(?, ?, item_seq.nextval, ?, ?, ?, ?, ?, sysdate, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getItem_pictureUrl1());
			pstmt.setString(2, pVo.getItem_pictureUrl2());
			pstmt.setString(3, pVo.getItem_category());
			pstmt.setString(4, pVo.getItem_name());
			pstmt.setString(5, pVo.getItem_content());
			pstmt.setInt(6, pVo.getItem_price());
			pstmt.setInt(7, pVo.getItem_quantity());
			pstmt.setString(8, pVo.getItem_total());
			pstmt.setString(9, pVo.getItem_time());
			pstmt.setString(10, String.valueOf(pVo.getItem_main()));
			pstmt.setInt(11, pVo.getItem_sales());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	*/

	public PostScriptVO selectPostByPost_num(String post_num) {
		String sql = "select * from item where post_num=?";
		PostScriptVO pVo = null;

		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, post_num);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					pVo = new PostScriptVO();
					pVo.setPost_num(rs.getInt(1));
					pVo.setPost_subject(rs.getString(2));
					pVo.setPost_writer(rs.getString(3));
					pVo.setPost_date(rs.getString(4));
					pVo.setPost_help(rs.getInt(5));
					pVo.setPost_hits(rs.getInt(6));
					pVo.setPost_stars(rs.getInt(7));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pVo;
	}

	/*
	public void updateItem(PostScriptVO pVo) {
		String sql = "update item set item_pictureUrl1=?, item_pictureUrl2=?, item_category=?, item_name=?, item_content=?, item_price=?, item_quantity=?, item_date=?, item_total=?, item_time=?, item_main=?, item_sales=? where item_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getItem_pictureUrl1());
			pstmt.setString(2, pVo.getItem_pictureUrl2());
			pstmt.setString(3, pVo.getItem_category());
			pstmt.setString(4, pVo.getItem_name());
			pstmt.setString(5, pVo.getItem_content());
			pstmt.setInt(6, pVo.getItem_price());
			pstmt.setInt(7, pVo.getItem_quantity());
			pstmt.setString(8, new SimpleDateFormat("yy/MM/dd").format(new Date()));
			pstmt.setString(9, pVo.getItem_total());
			pstmt.setString(10, pVo.getItem_time());
			pstmt.setString(11, String.valueOf(pVo.getItem_main()));
			pstmt.setInt(12, pVo.getItem_sales());
			pstmt.setInt(13, pVo.getItem_num());
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
	*/
}
