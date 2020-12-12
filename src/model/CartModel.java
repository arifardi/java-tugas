package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class CartModel {
	
	private Integer productID;
	private Integer quantity;
	private Connect con;

	public CartModel() {
		// TODO Auto-generated constructor stub
	}

	public CartModel(Integer productID, Integer quantity) {
		this.productID = productID;
		this.quantity = quantity;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	//Fungsi untuk mengambil semua data yang ada dalam cart
	public Vector<CartModel> getAllCart(){
		con = Connect.getConnection();
		ResultSet rs = con.executeQuery("SELECT * FROM cart"); 
		Vector<CartModel> cm = new Vector<CartModel>();
		try {
			while(rs.next()) {
				cm.add(new CartModel(rs.getInt("productID"), rs.getInt("quantity"))); 
			}
		} catch (Exception e) {
		}
		return cm;
	}
	
	//Fungsi untuk menambahkan barang ke dalam cart
	public void addToCart(Integer productID, Integer quantity) {
		con = Connect.getConnection(); //koneksi ke database
		PreparedStatement ps = con.prepareStatement("INSERT INTO cart (productID, quantity) VALUES ( ?, ?) ON DUPLICATE KEY UPDATE quantity = quantity + ?");
			
		try {
			ps.setInt(1, productID);
			ps.setInt(2, quantity);
			ps.setInt(3, quantity);
			ps.execute(); //buat jalanin query
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Fungsi untuk mengapus produk dalam cart berdasarkan productID
	public void deleteProduct(Integer productId) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("DELETE FROM cart WHERE productID = ?");
		try {
			ps.setInt(1, productId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Fungsi untuk mengembalikan cart menjadi kosong
	public void resetCart() {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("DELETE FROM cart");
		try {
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	

	
	
	
	
	

}
