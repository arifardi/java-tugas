package controller;

import java.util.Vector;

import model.StorageModel;
import view.StorageManagement;

public class StorageController {
	
	public static StorageController storageController;
	private StorageModel storage = new StorageModel();

	public StorageController() {
		// TODO Auto-generated constructor stub
	}
	
	public static StorageController getInstance() {
		if(storageController == null) {
			return new StorageController();
		}else {
			return storageController;
		}
	}
	
	//Untuk memanggil view StorageManagement
	public StorageManagement view() {
		return new StorageManagement();
	}
	
	//Untuk Memanggil fungsi getAllProduct
	public Vector<StorageModel> getAllProduct(){
		return storage.getAllProduct();
	}
	
	//Untuk Memanggil fungsi updateProduct
	public void updateProduct(Integer productId, Integer productPrice, String productName, String productDesc) {
		storage.updateProduct(productId, productPrice, productName, productDesc);
	}
	
	//Untuk Memanggil fungsi deleteProduct
	public void deleteProduct(Integer productId) {
		storage.deleteProduct(productId);
	}
	
	//Untuk Memanggil fungsi insertProduct
	public void insertProduct(String productName, String productDescription, Integer productPrice, Integer stock) {
		storage.insertProduct(productName, productDescription, productPrice, stock);
	}
	
	//Untuk Memanggil fungsi restock
	public void restock(Integer stock, Integer productId) {
		storage.restock(stock, productId);
	}
	
	//Untuk Memanggil fungsi updateStock
	public void updateStock() {
		storage.updateStock();
	}
	
}
