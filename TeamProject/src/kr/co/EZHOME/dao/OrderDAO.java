package kr.co.EZHOME.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.EZHOME.dto.OrderDTO;

public class OrderDAO {
	private static OrderDAO instance = new OrderDAO();

	public static OrderDAO getInstance() {
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

	public void insertOrder(OrderDTO odto) {
		String sql = "insert into ordertbl values(?,?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, odto.getOrder_num());
			pstmt.setString(2, odto.getUserid());
			pstmt.setString(3, odto.getOrder_name());
			pstmt.setInt(4, odto.getAmount());
			pstmt.setInt(5, odto.getUsePoint());
			pstmt.setString(6, odto.getDeli_name());
			pstmt.setString(7, odto.getDeli_addr());
			pstmt.setString(8, odto.getDeli_phone());
			pstmt.setString(9, odto.getDeli_msg());
			pstmt.setString(10, odto.getDeli_pwd());
			pstmt.setString(11, odto.getDeli_status());
			pstmt.setString(12, odto.getItem_pictureUrl1());
			pstmt.setInt(13, odto.getItem_num());
			pstmt.setString(14, odto.getItem_name());
			pstmt.setInt(15, odto.getItem_price());
			pstmt.setInt(16, odto.getItem_cnt());
			pstmt.setString(17, odto.getRefund_status());
			pstmt.setString(18, odto.getRefund_request());
			pstmt.setString(19, odto.getRefund_reject());

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

	public ArrayList<OrderDTO> selectOrderList(String userid, int start, int end) {
		String sql = "select * from (select A.*, Rownum Rnum from(select * from (select ROW_NUMBER() OVER(PARTITION BY order_num ORDER BY order_num) AS RNUM2, ordertbl.* FROM ordertbl where userid=?) where rnum2=1 order by order_num desc)A) where Rnum >= ? and Rnum <= ?";
		ArrayList<OrderDTO> olist = new ArrayList<OrderDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderDTO odto = new OrderDTO();
				odto.setOrder_num(rs.getString("order_num"));
				odto.setUserid(rs.getString("userid"));
				odto.setOrder_date(rs.getString("order_date"));
				odto.setOrder_name(rs.getString("order_name"));
				odto.setAmount(rs.getInt("amount"));
				odto.setUsePoint(rs.getInt("usePoint"));
				odto.setDeli_name(rs.getString("deli_name"));
				odto.setDeli_addr(rs.getString("deli_addr"));
				odto.setDeli_phone(rs.getString("deli_phone"));
				odto.setDeli_msg(rs.getString("deli_msg"));
				odto.setDeli_pwd(rs.getString("deli_pwd"));
				odto.setDeli_status(rs.getString("deli_status"));
				odto.setItem_pictureUrl1(rs.getString("item_pictureUrl1"));
				odto.setItem_num(rs.getInt("item_num"));
				odto.setItem_name(rs.getString("item_name"));
				odto.setItem_price(rs.getInt("item_price"));
				odto.setItem_cnt(rs.getInt("item_cnt"));
				odto.setRefund_status(rs.getString("refund_status"));
				odto.setRefund_request(rs.getString("refund_request"));
				odto.setRefund_reject(rs.getString("refund_reject"));
				olist.add(odto);
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
		return olist;
	}

	public ArrayList<OrderDTO> selectRefundList(String order_num) {
		String sql = "select * from ordertbl where order_num=?";
		ArrayList<OrderDTO> olist = new ArrayList<OrderDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderDTO odto = new OrderDTO();
				odto.setOrder_num(rs.getString("order_num"));
				odto.setUserid(rs.getString("userid"));
				odto.setOrder_date(rs.getString("order_date"));
				odto.setOrder_name(rs.getString("order_name"));
				odto.setAmount(rs.getInt("amount"));
				odto.setUsePoint(rs.getInt("usePoint"));
				odto.setDeli_name(rs.getString("deli_name"));
				odto.setDeli_addr(rs.getString("deli_addr"));
				odto.setDeli_phone(rs.getString("deli_phone"));
				odto.setDeli_msg(rs.getString("deli_msg"));
				odto.setDeli_pwd(rs.getString("deli_pwd"));
				odto.setDeli_status(rs.getString("deli_status"));
				odto.setItem_pictureUrl1(rs.getString("item_pictureUrl1"));
				odto.setItem_num(rs.getInt("item_num"));
				odto.setItem_name(rs.getString("item_name"));
				odto.setItem_price(rs.getInt("item_price"));
				odto.setItem_cnt(rs.getInt("item_cnt"));
				odto.setRefund_status(rs.getString("refund_status"));
				odto.setRefund_request(rs.getString("refund_request"));
				odto.setRefund_reject(rs.getString("refund_reject"));
				olist.add(odto);
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
		return olist;
	}

	public void modifyRefundStatus(int item_num, String refund_status, String order_num) {
		String sql = "update (select * from ordertbl where order_num=?) set refund_status=? where item_num=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order_num);
			pstmt.setString(2, refund_status);
			pstmt.setInt(3, item_num);
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

	public void addRequest(String refund_request, int item_num, String order_num) {
		String sql = "update ordertbl set refund_request=? where item_num=? and order_num=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, refund_request);
			pstmt.setInt(2, item_num);
			pstmt.setString(3, order_num);
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

	public ArrayList<OrderDTO> selectRefundRequest(int start, int end, String category, String keyword) {
		String sql ="";
		String keywordOk = "%"+keyword+"%";
		if(category.equals("userid")) {
		sql = "select * from (select A.*, Rownum Rnum from(select * from ordertbl where userid like ? and refund_status != '취소 완료' order by order_num asc)A) where Rnum >= ? and Rnum <= ?";
		}else {
			sql = "select * from (select A.*, Rownum Rnum from(select * from ordertbl where order_num like ? and refund_status != '취소 완료' order by order_num asc)A) where Rnum >= ? and Rnum <= ?";
		}

		ArrayList<OrderDTO> olist = new ArrayList<OrderDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keywordOk);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderDTO odto = new OrderDTO();
				odto.setOrder_num(rs.getString("order_num"));
				odto.setUserid(rs.getString("userid"));
				odto.setOrder_date(rs.getString("order_date"));
				odto.setOrder_name(rs.getString("order_name"));
				odto.setAmount(rs.getInt("amount"));
				odto.setUsePoint(rs.getInt("usePoint"));
				odto.setDeli_name(rs.getString("deli_name"));
				odto.setDeli_addr(rs.getString("deli_addr"));
				odto.setDeli_phone(rs.getString("deli_phone"));
				odto.setDeli_msg(rs.getString("deli_msg"));
				odto.setDeli_pwd(rs.getString("deli_pwd"));
				odto.setDeli_status(rs.getString("deli_status"));
				odto.setItem_pictureUrl1(rs.getString("item_pictureUrl1"));
				odto.setItem_num(rs.getInt("item_num"));
				odto.setItem_name(rs.getString("item_name"));
				odto.setItem_price(rs.getInt("item_price"));
				odto.setItem_cnt(rs.getInt("item_cnt"));
				odto.setRefund_status(rs.getString("refund_status"));
				odto.setRefund_request(rs.getString("refund_request"));
				odto.setRefund_reject(rs.getString("refund_reject"));
				olist.add(odto);
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
		return olist;
	}

	public ArrayList<OrderDTO> selectAllOrderList(int start, int end, String category, String keyword) {
		String keywordOk = "%"+keyword+"%";
		String sql ="";
		if(category.equals("userid")) {
		sql = "select * from (select A.*, Rownum Rnum from(select * from (select ROW_NUMBER() OVER(PARTITION BY order_num ORDER BY order_num ) AS RNUM2, ordertbl.* FROM ordertbl where userid like ?) where rnum2=1 order by order_num desc)A) where Rnum >= ? and Rnum <= ?";
		}else {
			sql = "select * from (select A.*, Rownum Rnum from(select * from (select ROW_NUMBER() OVER(PARTITION BY order_num ORDER BY order_num ) AS RNUM2, ordertbl.* FROM ordertbl where order_num like ?) where rnum2=1 order by order_num desc)A) where Rnum >= ? and Rnum <= ?";
			
		}
		ArrayList<OrderDTO> olist = new ArrayList<OrderDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keywordOk);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderDTO odto = new OrderDTO();
				odto.setOrder_num(rs.getString("order_num"));
				odto.setUserid(rs.getString("userid"));
				odto.setOrder_date(rs.getString("order_date"));
				odto.setOrder_name(rs.getString("order_name"));
				odto.setAmount(rs.getInt("amount"));
				odto.setUsePoint(rs.getInt("usePoint"));
				odto.setDeli_name(rs.getString("deli_name"));
				odto.setDeli_addr(rs.getString("deli_addr"));
				odto.setDeli_phone(rs.getString("deli_phone"));
				odto.setDeli_msg(rs.getString("deli_msg"));
				odto.setDeli_pwd(rs.getString("deli_pwd"));
				odto.setDeli_status(rs.getString("deli_status"));
				odto.setItem_pictureUrl1(rs.getString("item_pictureUrl1"));
				odto.setItem_num(rs.getInt("item_num"));
				odto.setItem_name(rs.getString("item_name"));
				odto.setItem_price(rs.getInt("item_price"));
				odto.setItem_cnt(rs.getInt("item_cnt"));
				odto.setRefund_status(rs.getString("refund_status"));
				odto.setRefund_request(rs.getString("refund_request"));
				odto.setRefund_reject(rs.getString("refund_reject"));
				olist.add(odto);
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
		return olist;
	}

	public void updateDeli_Status(String deli_status, String order_num) {
		String sql = "update ordertbl set deli_status=? where order_num=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deli_status);
			pstmt.setString(2, order_num);
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

	public void updateReject(String reject, String order_num, int item_num) {
		String sql = "update ordertbl set refund_reject=? where order_num=? and item_num=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reject);
			pstmt.setString(2, order_num);
			pstmt.setInt(3, item_num);
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
	
	
	public int orderCnt(String userid){
		String useridOk = "%"+userid+"%";
		int orderCnt=0;
		String  sql = "select count(*) from (select * from (select ROW_NUMBER() OVER(PARTITION BY order_num ORDER BY order_num ) AS RNUM, ordertbl.* FROM ordertbl where userid like ?) where rnum=1)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, useridOk);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderCnt = rs.getInt(1);
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
		
		return orderCnt;
	}
	
	
	public int refundRequestCnt(String category, String keyword){
		int orderCnt=0;
		String keywordOk = "%"+keyword+"%"; 
		String sql ="";
		if(category.equals("userid")) {
			sql = "select count(*) from ordertbl where userid like ? and refund_status != '취소 완료'";
		}else {
			sql = "select count(*) from ordertbl where order_num like ? and refund_status != '취소 완료'";
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keywordOk);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderCnt = rs.getInt(1);
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
		
		return orderCnt;
	}
	
	
	public int orderManageCnt(String category, String keyword){
		int orderCnt=0;
		String keywordOk = "%"+keyword+"%"; 
		String sql ="";
		if(category.equals("userid")) {
			sql = "select count(*) from (select * from (select ROW_NUMBER() OVER(PARTITION BY order_num ORDER BY order_num ) AS RNUM, ordertbl.* FROM ordertbl where userid like ?) where rnum=1)";
		}else {
			sql = "select count(*) from (select * from (select ROW_NUMBER() OVER(PARTITION BY order_num ORDER BY order_num ) AS RNUM, ordertbl.* FROM ordertbl where order_num like ?) where rnum=1)";
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keywordOk);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderCnt = rs.getInt(1);
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
		
		return orderCnt;
	}
	
	

}
