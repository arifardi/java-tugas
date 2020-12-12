package controller;

import java.util.Vector;

import model.TransactionItemModel;
import view.TransactionItem;

public class TransactionItemController {

	public TransactionItemController() {
		// TODO Auto-generated constructor stub
	}
	
	public static TransactionItemController transactionItemController;
	private TransactionItemModel tim  = new TransactionItemModel();
	
	public static TransactionItemController getInstance() {
		if(transactionItemController == null) {
			return new TransactionItemController();
		}else {
			return transactionItemController;
		}
	}
	
	//Untuk Memanggil view TransactionItem
	public TransactionItem view() {
		return new TransactionItem();
	}
	
	//Untuk Memanggil fungsi getAllTransactionItem
	public Vector<TransactionItemModel> getAllTransactionItem(){
		return tim.getAllTransactionItem();
	}
	
	//Untuk Memanggil fungsi addTransactionitem
	public void addTransactionItem() {
		tim.addTransactionItem();
	}
	
	

}
