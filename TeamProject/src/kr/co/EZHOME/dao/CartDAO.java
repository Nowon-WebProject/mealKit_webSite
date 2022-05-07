package kr.co.EZHOME.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.EZHOME.dto.CartDTO;

public class CartDAO {

	private CartDAO() {

	}

	private static CartDAO instance = new CartDAO();

	public static CartDAO getInstance() {
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

	public ArrayList<CartDTO> selectCartProduct(String userid) {
		String sql = "select cart_seq, product_price, product_name, product_cnt from carttbl where userid=? order by cart_seq asc";
		ArrayList<CartDTO> productList = new ArrayList<CartDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CartDTO cdto = new CartDTO();
				cdto.setCart_seq(rs.getInt("cart_seq"));
				cdto.setProduct_name(rs.getString("product_name"));
				cdto.setProduct_price(rs.getString("product_price"));
				cdto.setProduct_cnt(rs.getInt("product_cnt"));
				productList.add(cdto);

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
		return productList;
	}

	public ArrayList<CartDTO> updateCartProduct(CartDTO cdto) {
		String sql = "update carttbl set product_cnt=? where userid=?";

		ArrayList<CartDTO> productList = new ArrayList<CartDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cdto.getProduct_cnt());
			pstmt.setString(2, cdto.getUserid());
			pstmt.executeUpdate();

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

		return productList;
	}

	public int cartCnt(String userid) {
		int CartCnt = 0;
		String sql = "select count(*) from carttbl where userid=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CartCnt = rs.getInt(1);
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

		return CartCnt;
	}

	public void insertCart(CartDTO cdto) {
		String sql = "insert into carttbl values(cart_seq.nextVal,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cdto.getUserid());
			pstmt.setString(2, cdto.getProduct_name());
			pstmt.setString(3, cdto.getProduct_price());
			pstmt.setInt(4, cdto.getProduct_cnt());

			pstmt.executeUpdate();

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

	public void deleteCart(int cart_seq) {
		String sql = "delete from carttbl where cart_seq=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart_seq);
			pstmt.executeUpdate();
			System.out.println("삭제 성공");

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
	
	public void deleteAllCart(String userid) {
		String sql = "delete from carttbl where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
			System.out.println("전체삭제 성공");

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
	
	public void cartCntModify(int product_cnt, int cart_seq) {
		String sql="update carttbl set product_cnt=? where cart_seq=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_cnt);
			pstmt.setInt(2, cart_seq);
			pstmt.executeUpdate();
			System.out.println("수량변경 성공");

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
