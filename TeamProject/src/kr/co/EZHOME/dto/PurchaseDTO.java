package kr.co.EZHOME.dto;

public class PurchaseDTO {
	private int purchase_seq;
	private String userid;
	private String purchase_date;
	private int total_price;
	private String address;
	private String delivery_status;

	public PurchaseDTO() {

	}

	public int getPurchase_seq() {
		return purchase_seq;
	}

	public void setPurchase_seq(int purchase_seq) {
		this.purchase_seq = purchase_seq;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDelivery_status() {
		return delivery_status;
	}

	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}

}
