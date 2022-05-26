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
		String sql = "select * from carttbl where userid=? order by cart_seq asc";
		ArrayList<CartDTO> itemList = new ArrayList<CartDTO>();
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
				cdto.setItem_num(rs.getInt("item_num"));
				cdto.setItem_pictureUrl1(rs.getString("item_pictureUrl1"));
				cdto.setCart_seq(rs.getInt("cart_seq"));
				cdto.setItem_name(rs.getString("item_name"));
				cdto.setItem_price(rs.getString("item_price"));
				cdto.setItem_cnt(rs.getInt("item_cnt"));
				cdto.setItem_quantity(rs.getInt("item_quantity"));
				itemList.add(cdto);

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

	public ArrayList<CartDTO> updateCartProduct(CartDTO cdto) {
		String sql = "update carttbl set item_cnt=? where userid=?";

		ArrayList<CartDTO> productList = new ArrayList<CartDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cdto.getItem_cnt());
			pstmt.setString(2, cdto.getUserid());
			pstmt.executeUpdate();
			System.out.println(cdto.getUserid()+"님의 장바구니 등록 완료");

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
		int cartCnt = 0;
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
				cartCnt = rs.getInt(1);
				System.out.println(userid+"님의 장바구니 갯수"+cartCnt);
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

		return cartCnt;
	}

	public void insertCart(CartDTO cdto) {
		String sql = "insert into carttbl values(?,?,cart_seq.nextVal,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cdto.getItem_num());
			pstmt.setString(2, cdto.getItem_pictureUrl1());
			pstmt.setString(3, cdto.getUserid());
			pstmt.setString(4, cdto.getItem_name());
			pstmt.setString(5, cdto.getItem_price());
			pstmt.setInt(6, cdto.getItem_cnt());
			pstmt.setInt(7, cdto.getItem_quantity());

			pstmt.executeUpdate();
			
			System.out.println(cdto.getUserid()+"님의 장바구니에 담기 완료");

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
			System.out.println("장바구니 번호"+cart_seq+"번 삭제 완료");

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
			System.out.println(userid+"님의 장바구니 전체삭제 완료");

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
	
	public void cartCntModify(int item_cnt, int cart_seq) {
		String sql="update carttbl set item_cnt=? where cart_seq=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item_cnt);
			pstmt.setInt(2, cart_seq);
			pstmt.executeUpdate();
			System.out.println("장바구니 번호"+cart_seq+"번 수량"+item_cnt+"으로 변경 완료");

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
	
	public int cartCheck(String item_name, String userid) {
		int cartCheckResult = 0;
		String sql = "select count(item_name) from carttbl where item_name=? and userid=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item_name);
			pstmt.setString(2, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cartCheckResult = rs.getInt(1);
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

		return cartCheckResult;
	}
	
	public void cartAdd(int item_cnt, String item_name, String userid) {
		String sql="update carttbl set item_cnt=item_cnt+? where item_name=? and userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item_cnt);
			pstmt.setString(2, item_name);
			pstmt.setString(3, userid);
			pstmt.executeUpdate();
			System.out.println(userid+"님의 "+item_name+"의 수량 "+item_cnt+"개 추가 완료");

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
	
	public void cartItemCntModify(int item_quantity, int item_num) {
		String sql="update carttbl set item_cnt=? where item_num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item_quantity);
			pstmt.setInt(2, item_num);
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
	
}
