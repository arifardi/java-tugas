package controller;

import java.util.Vector;

import model.VoucherModel;
import view.Voucher;

public class VoucherController {

	public VoucherController() {
		// TODO Auto-generated constructor stub
	}
	
	public static VoucherController voucherController;
	private VoucherModel voucher = new VoucherModel();

	
	public static VoucherController getInstance() {
		if(voucherController == null) {
			return new VoucherController();
		}else {
			return voucherController;
		}
	}
	
	//Untuk Memanggil view Voucher
	public Voucher view() {
		return new Voucher();
	}

	//Untuk Memanggil fungsi getAllVoucher
	public Vector<VoucherModel> getAllVoucher(){
		return voucher.getAllVoucher();
	}

	//Untuk Memanggil fungsi insertVoucher
	public void insertVoucher(float discount, String validDate) {
		voucher.insertVoucher(discount, validDate);
	}

	//Untuk Memanggil fungsi updateVoucher
	public void updateVoucher(Integer voucherId, float discount, String validDate) {
		voucher.updateVoucher(voucherId, discount, validDate);
	}

	//Untuk Memanggil fungsi deleteVocuher
	public void deleteVocuher(Integer voucherId) {
		voucher.deleteVocuher(voucherId);
	}

	//Untuk Memanggil fungsi checkVoucher
	public boolean checkVoucher(Integer voucherID) {
		return voucher.checkVoucher(voucherID);
	}

	//Untuk Memanggil fungsi getDiscount
	public float getDiscount(Integer voucherID) {
		return voucher.getDiscount(voucherID);
	}


}
