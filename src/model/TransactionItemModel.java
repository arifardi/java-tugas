package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class TransactionItemModel {
	
	private Integer transactionID;
	private Integer productID;
	private Integer quantity;
	private Connect con;
	private Integer index;

	public TransactionItemModel() {
	}

	public TransactionItemModel(Integer transactionID, Integer productID, Integer quantity) {
		this.transactionID = transactionID;
		this.productID = productID;
		this.quantity = quantity;
	}
	
	
	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
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
	
	//Fungsi untuk mengambil semua transactionItem dalam database
	public Vector<TransactionItemModel> getAllTransactionItem(){
		con = Connect.getConnection();
		ResultSet rs = con.executeQuery("SELECT * FROM transactionitem");
		Vector<TransactionItemModel> tim = new Vector<TransactionItemModel>();
		try {
			while(rs.next()) {
				tim.add(new TransactionItemModel(rs.getInt("transactionID"), rs.getInt("productID"), rs.getInt("quantity")));
			}
		} catch (Exception e) {
		}
		return tim;
	}
	
	//Fungsi untuk mengambil semua data transaction dalam database, tetapi dipasang disini karena di perlukan untuk menghitung index
	//Index di perlukan untuk mencari tahu ID untuk kerpluan insert
	public Vector<TransactionModel> getAllTransaction(){
		con = Connect.getConnection();
		ResultSet rs = con.executeQuery("SELECT * FROM transaction");
		Vector<TransactionModel> tm = new Vector<TransactionModel>();
		index = 1;
		try {
			while(rs.next()) {
				tm.add(new TransactionModel(rs.getInt("transactionID"), rs.getString("purchase_datetime"), rs.getInt("voucherID"), rs.getInt("employeeID"), rs.getString("paymentType")));
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tm;
	}
	
	//Fungsi untuk menambahkan transactionitem baru ketika pembayaran berhasil
	public void addTransactionItem() {
        con = Connect.getConnection();	
        getAllTransaction();
        int id = index;
        Vector<CartModel> cart = new Vector<CartModel>();
        cart = listCart();
        for(int i=0 ; i<cart.size();i++) {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO transactionitem VALUES (?,?,?)");
            try {
                ps.setInt(1, id);
                ps.setInt(2, cart.get(i).getProductID());
                ps.setInt(3, cart.get(i).getQuantity());
                ps.execute();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
	

	//Fungsi untuk mengambil semua data dalam cart
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
