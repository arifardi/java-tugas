package controller;

import java.util.Vector;

import model.TransactionModel;
import view.Transaction;

public class TransactionController {

	public TransactionController() {
		// TODO Auto-generated constructor stub
	}
	
	public static TransactionController transactionController;
	private TransactionModel transaction = new TransactionModel();

	
	public static TransactionController getInstance() {
		if(transactionController == null) {
			return new TransactionController();
		}else {
			return transactionController;
		}
	}
	
	//Untuk Memanggil view Transaction
	public Transaction view() {
		return new Transaction();
	}
	
	//Untuk Memanggil fungsi getAllTransaction
	public Vector<TransactionModel> getAllTransaction(){
		return transaction.getAllTransaction();
	}
	
	//Untuk Memanggil fungsi getTransactionReport
	public Vector<TransactionModel> getTransactionReport(String month, String year){
		return transaction.getTransactionReport(month, year);
	}
	
	//Untuk Memanggil fungsi addTransaction
	public void addTransaction(Integer voucherID,Integer employeeID, String paymentType) {
		transaction.addTransaction(voucherID, employeeID, paymentType);
	}

	
}
