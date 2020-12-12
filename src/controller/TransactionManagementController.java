package controller;

import view.TransactionManagement;

public class TransactionManagementController {

	public TransactionManagementController() {
		// TODO Auto-generated constructor stub
	}
	
	public static TransactionManagementController transactionManagementController;
	

	public static TransactionManagementController getInstance() {
		if(transactionManagementController == null) {
			return new TransactionManagementController();
		}else {
			return transactionManagementController;
		}
	}
	
	//Untuk Memanggil view TransactionManagement
	public TransactionManagement view() {
		return new TransactionManagement();
	}
	
	

}
