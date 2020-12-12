package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class TransactionModel {
	
	private Integer transactionID;
	private String purchase_datetime;
	private Integer voucherID;
	private Integer employeeID;
	private String paymentType;
	private Connect con;
	private Integer index;
	

	public TransactionModel() {
		// TODO Auto-generated constructor stub
	}

	public TransactionModel(Integer transactionID, String purchase_datetime, Integer voucherID, Integer employeeID,
			String paymentType) {
		this.transactionID = transactionID;
		this.purchase_datetime = purchase_datetime;
		this.voucherID = voucherID;
		this.employeeID = employeeID;
		this.paymentType = paymentType;
	}

	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public String getPurchase_datetime() {
		return purchase_datetime;
	}

	public void setPurchase_datetime(String purchase_datetime) {
		this.purchase_datetime = purchase_datetime;
	}

	public Integer getVoucherID() {
		return voucherID;
	}

	public void setVoucherID(Integer voucherID) {
		this.voucherID = voucherID;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	//Fungsi untuk mengambil semua data transaction dalam database
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
	
	//Fungsi untuk mengambil semua data transaction dalam database berdasar month dan year dari purchase_date
	public  Vector<TransactionModel> getTransactionReport(String month, String year) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT * FROM transaction WHERE MONTH(purchase_datetime) = ? AND YEAR(purchase_datetime) = ? ");
		Vector<TransactionModel> tm2 = new Vector<TransactionModel>();
		try {
			ps.setString(1, month);
			ps.setString(2, year);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tm2.add(new TransactionModel(rs.getInt("transactionID"), rs.getString("purchase_datetime"), rs.getInt("voucherID"), rs.getInt("employeeID"), rs.getString("paymentType")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tm2;
	}
	
	//Fungsi untuk menambahkan transaksi baru, di panggil ketika pembayaran berhasil di lakukan oleh customer
	public void addTransaction(Integer voucherID,Integer employeeID, String paymentType) {
		con = Connect.getConnection(); //koneksi ke database
		getAllTransaction();
		PreparedStatement ps = con.prepareStatement("INSERT INTO transaction VALUES ( ?, CURDATE(), ?, ?, ?)");
		try {
			ps.setInt(1, index);
			ps.setInt(2, voucherID);
			ps.setInt(3, employeeID);
			ps.setString(4, paymentType);
			ps.execute(); //buat jalanin query
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

}
