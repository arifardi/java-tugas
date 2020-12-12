package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class VoucherModel {

	public VoucherModel() {
		// TODO Auto-generated constructor stub
	}
	
	private Integer voucherId;
	private float discount;
	private String validDate;
	private String status;
	private Connect con;
	private Integer index;
	private float tempDisc;
	private ResultSet rs;
	
	
	public VoucherModel(Integer voucherId, float discount, String validDate, String status) {
		this.voucherId = voucherId;
		this.discount = discount;
		this.validDate = validDate;
		this.status = status;
	}
	
	public Integer getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	//Fungsi untuk mengambil semua data voucher dalam database
	public Vector<VoucherModel> getAllVoucher(){
		con = Connect.getConnection();
		ResultSet rs = con.executeQuery("SELECT * FROM voucher");
		Vector<VoucherModel> vm = new Vector<VoucherModel>();
		index = 1;
		try {
			while(rs.next()) {
				vm.add(new VoucherModel(rs.getInt("voucherID"),rs.getFloat("discount"),rs.getString("validDate"), rs.getString("status")));
				index++;
			}
		} catch (Exception e) {
		}
		return vm;
	}
	
	//Fungsi untuk menginsert data voucher baru ke dalam database
	public void insertVoucher(float discount, String validDate) {
		con = Connect.getConnection(); 
		getAllVoucher(); 
		PreparedStatement ps = con.prepareStatement("INSERT INTO voucher VALUES ( ?, ?, ?, ?)");
		try {
			ps.setInt(1, index);
			ps.setFloat(2, discount);
			ps.setString(3, validDate);
			ps.setString(4, "Not Used");
			ps.execute(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Fungsi untuk mengupdate voucher 
	public void updateVoucher(Integer voucherId, float discount, String validDate) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("UPDATE voucher SET discount = ? , validDate = ? WHERE voucherID = ?");
		try {
			ps.setFloat(1, discount);
			ps.setString(2, validDate);
			ps.setInt(3, voucherId);
			ps.execute(); 
		} catch (SQLException e) {
			
		}
	}
	
	//Fungsi untuk menghapus voucher 
	public void deleteVocuher(Integer voucherId) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("DELETE FROM voucher WHERE voucherID = ?");
		try {
			ps.setInt(1, voucherId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Fungsi untuk mengecheck apakah voucher ada atau statusnya masih Not Used, di panggil dalam menu payment ketika input voucher di tekan
	public boolean checkVoucher(Integer voucherID) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT voucherID, status FROM voucher WHERE voucherID = ?");
		try {
			int temp = 0;
			ps.setInt(1, voucherID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				temp = rs.getInt(1);
				status = rs.getString(2);
			}
			if(temp > 0 && status.equals("Not Used")) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Fungsi untuk mengambil discount dari sebuah voucherID, di jalankan saat voucher sudah berhasil di check
	public float getDiscount(Integer voucherID) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT discount FROM voucher WHERE voucherID = ?");
		try {
			ps.setInt(1, voucherID);
			rs = ps.executeQuery();
			while(rs.next()) {
				tempDisc = rs.getFloat(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempDisc;
	}

}
