package kr.co.EZHOME.dto;

public class CartDTO {
	private int cart_seq;
	private String userid;
	private String product_name;
	private String product_price;
	private int product_cnt;
	
	public CartDTO() {
		
	}
	

	public int getCart_seq() {
		return cart_seq;
	}


	public void setCart_seq(int cart_seq) {
		this.cart_seq = cart_seq;
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_price() {
		return product_price;
	}

	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}

	public int getProduct_cnt() {
		return product_cnt;
	}

	public void setProduct_cnt(int product_cnt) {
		this.product_cnt = product_cnt;
	}
	
	



}
