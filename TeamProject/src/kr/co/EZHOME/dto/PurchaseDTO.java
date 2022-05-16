package kr.co.EZHOME.dto;

public class PurchaseDTO {
	private int purchase_seq;
	private String userid;
	private String purchase_date;
	private String item_name;
	private int total_price;
	private String deli_name;
	private String deli_addr;
	private String deli_phone;
	private String deli_msg;
	private String deli_pwd;
	private String deli_status;
	private int usePoint;
	
	
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


	public String getItem_name() {
		return item_name;
	}


	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}


	public int getTotal_price() {
		return total_price;
	}


	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}


	public String getDeli_name() {
		return deli_name;
	}


	public void setDeli_name(String deli_name) {
		this.deli_name = deli_name;
	}
	

	public String getDeli_addr() {
		return deli_addr;
	}


	public void setDeli_addr(String deli_addr) {
		this.deli_addr = deli_addr;
	}

	

	public String getDeli_phone() {
		return deli_phone;
	}


	public void setDeli_phone(String deli_phone) {
		this.deli_phone = deli_phone;
	}


	public String getDeli_msg() {
		return deli_msg;
	}


	public void setDeli_msg(String deli_msg) {
		this.deli_msg = deli_msg;
	}


	public String getDeli_pwd() {
		return deli_pwd;
	}


	public void setDeli_pwd(String deli_pwd) {
		this.deli_pwd = deli_pwd;
	}


	public String getDeli_status() {
		return deli_status;
	}


	public void setDeli_status(String deli_status) {
		this.deli_status = deli_status;
	}


	public int getUsePoint() {
		return usePoint;
	}


	public void setUsePoint(int usePoint) {
		this.usePoint = usePoint;
	}

	
	

}
