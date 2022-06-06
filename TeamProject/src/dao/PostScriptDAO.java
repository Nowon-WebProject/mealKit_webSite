package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.PostScriptVO;
import util.DBManager;

public class PostScriptDAO {
	
	private PostScriptDAO() {

	}

	private static PostScriptDAO instance = new PostScriptDAO();

	public static PostScriptDAO getInstance() {
		return instance;
	}
	
	public int getAllCount() {
		String sql = "select count(*) from postScript";
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
	
	public List<PostScriptVO> selectAllPostScripts(int start, int end, String order) {
		String sql = null;
		
		if (order.equals("1")) // 최근등록순
			sql = "select * from(select A.*, Rownum Rnum from(select * from postScript order by post_num desc)A)" + "where Rnum >= ? and Rnum <= ?";
		else if (order.equals("2")) // 도움많은순
			sql = "select * from(select A.*, Rownum Rnum from(select * from postScript order by post_help desc)A)" + "where Rnum >= ? and Rnum <= ?";
		else if (order.equals("3")) // 조회많은순
			sql = "select * from(select A.*, Rownum Rnum from(select * from postScript order by post_hits desc)A)" + "where Rnum >= ? and Rnum <= ?";
			
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
				pVo.setPost_num(rs.getInt("post_num"));
				pVo.setPost_subject(rs.getString("post_subject"));
				pVo.setPost_writer(rs.getString("post_writer"));
				pVo.setPost_date(rs.getString("post_date"));
				pVo.setPost_help(rs.getInt("post_help"));
				pVo.setPost_hits(rs.getInt("post_hits"));
				pVo.setPost_stars(rs.getDouble("post_stars"));
				pVo.setPost_content(rs.getString("post_content"));
				pVo.setPost_image(rs.getString("post_image"));
				list.add(pVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}

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
					pVo.setPost_num(rs.getInt("post_num"));
					pVo.setPost_subject(rs.getString("post_subject"));
					pVo.setPost_writer(rs.getString("post_writer"));
					pVo.setPost_date(rs.getString("post_date"));
					pVo.setPost_help(rs.getInt("post_help"));
					pVo.setPost_hits(rs.getInt("post_hits"));
					pVo.setPost_stars(rs.getDouble("post_stars"));
					pVo.setPost_content(rs.getString("post_content"));
					pVo.setPost_image(rs.getString("post_image"));
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
	
	public void insertPostScript(PostScriptVO pVo) {
		String sql = "insert into postScript values(postScript_seq.nextval, ?, ?, sysdate, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getPost_subject());
			pstmt.setString(2, pVo.getPost_writer());
			pstmt.setInt(3, pVo.getPost_help());
			pstmt.setInt(4, pVo.getPost_hits());
			pstmt.setDouble(5, pVo.getPost_stars());
			pstmt.setString(6, pVo.getPost_content());
			pstmt.setString(7, pVo.getPost_image());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public int updateHits(int post_num) {
		String sql = "update postScript set post_hits=post_hits+1 where post_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return result;
	}

	public void deletePostScript(String post_num) {
		String sql = "delete from postScript where post_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	/*
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
	*/
}
