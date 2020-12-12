package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class StorageModel {
	
	private Integer id;
	private String productName;
	private String productDescription;
	private Integer productPrice;
	private Integer stock;
	private Connect con;
	private Integer index;
	
	
	public StorageModel() {
	}


	public StorageModel(Integer id, String productName, String productDescription, Integer productPrice,
			Integer stock) {
		this.id = id;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.stock = stock;
	}
	
	public Vector<StorageModel> getAllProduct(){
		con = Connect.getConnection(); //ambil koneksi
		ResultSet rs = con.executeQuery("SELECT * FROM storage");//penampung data
		Vector<StorageModel> sm = new Vector<StorageModel>();
		index = 1;
		try {
			while(rs.next()) {
				sm.add(new StorageModel(rs.getInt("productID"), rs.getString("productName") , rs.getString("productDescription"), rs.getInt("productPrice"), rs.getInt("stock")));
				index++;
			}
		} catch (Exception e) {
		}
		return sm;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public Integer getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
	//Fungsi untuk mengupdate product dalam storage
	public void updateProduct(Integer productId, Integer productPrice, String productName, String productDesc) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("UPDATE storage SET productPrice = ? , productName = ? , productDescription = ? WHERE productID = ?");
		try {
			ps.setInt(1, productPrice);
			ps.setString(2, productName);
			ps.setString(3, productDesc);
			ps.setInt(4, productId);
			
			ps.execute(); //buat jalanin query
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Fungsi untuk menghapus product dalam storage
	public void deleteProduct(Integer productId) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("DELETE FROM storage WHERE productID = ?");
		try {
			ps.setInt(1, productId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Fungsi untuk memasukan product baru dalam storage
	public void insertProduct(String productName, String productDescription, Integer productPrice, Integer stock) {
		con = Connect.getConnection(); //koneksi ke database
		getAllProduct(); //untuk menghitung index (id)
		PreparedStatement ps = con.prepareStatement("INSERT INTO storage VALUES ( ?, ?, ?, ?, ?)");
		try {
			ps.setInt(1, index);
			ps.setString(2, productName);
			ps.setString(3, productDescription);
			ps.setInt(4, productPrice);
			ps.setInt(5, stock);
			ps.execute(); //buat jalanin query
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Fungsi untuk merestock stock product
	public void restock(Integer stock, Integer productId) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("UPDATE storage SET stock = stock + ?  WHERE productID = ?");
		try {
			ps.setInt(1, stock);
			ps.setInt(2, productId);
			ps.execute(); //buat jalanin query
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Fungsi untuk updateStock setelah pembayaran sukses
	public void updateStock() {
		con = Connect.getConnection();
		Vector<CartModel> cart = new Vector<CartModel>();
		cart = listCart();
		for(int i = 0 ; i < cart.size(); i++) {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("UPDATE storage SET stock =  stock - ?   WHERE productID = ?");
            try {
                ps.setInt(1, cart.get(i).getQuantity());
                ps.setInt(2, cart.get(i).getProductID());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
			
		}
	}
	
	//Fungsi untuk mengambil semua barang yang ada dalam cart
	public Vector<CartModel> listCart(){
		 con = Connect.getConnection();
		 Vector<CartModel> cart = new Vector<CartModel>();
		 ResultSet rs= con.executeQuery("SELECT productID, quantity FROM cart");
	        try {
	            while (rs.next()) {
	                cart.add(new CartModel(rs.getInt(1), rs.getInt(2)));
	            }

	        } catch (SQLException e) {
	            
	        }
	        return cart;
	}
	


}
