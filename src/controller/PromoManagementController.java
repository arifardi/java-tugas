package controller;

import view.PromoManagement;

public class PromoManagementController {

	public PromoManagementController() {
		
	}
	
	public static PromoManagementController promoManagementController;
	

	public static PromoManagementController getInstance() {
		if(promoManagementController == null) {
			return new PromoManagementController();
		}else {
			return promoManagementController;
		}
	}
	
	//Untuk memanggil view PromoManagement
	public PromoManagement view() {
		return new PromoManagement();
	}

}
