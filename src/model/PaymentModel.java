package model;

import java.sql.ResultSet;
import java.util.Vector;
import connect.Connect;

public class PaymentModel {
	
	private Integer productID;
	private String productName;
	private Integer productPrice;
	private Integer quantity;
	private Connect con;

	public PaymentModel() {
	}

	public PaymentModel(Integer productID, String productName, Integer productPrice, Integer quantity) {
		this.productID = productID;	
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	//Fungsi untuk mengambil data dari cart
	public Vector<PaymentModel> getAllFromCart(){
		con = Connect.getConnection();
		ResultSet rs = con.executeQuery("SELECT cart.productID, storage.productName, storage.productPrice, cart.quantity FROM cart JOIN storage ON cart.productID = storage.productID");
		Vector<PaymentModel> pm = new Vector<PaymentModel>();
		try {
			while(rs.next()) {
				pm.add(new PaymentModel(rs.getInt("cart.productID"), rs.getString("storage.productName"), rs.getInt("storage.productPrice"), rs.getInt("cart.quantity")));
			}
		} catch (Exception e) {
		}
		return pm;
	}
	
	
	
	

}
