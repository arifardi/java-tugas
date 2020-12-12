package controller;

import view.HumanResourceManagement;

public class HumanResourceManagementController {

	public HumanResourceManagementController() {
		// TODO Auto-generated constructor stub
	}
	
	public static HumanResourceManagementController humanResourceManagementController;
	

	public static HumanResourceManagementController getInstance() {
		if(humanResourceManagementController == null) {
			return new HumanResourceManagementController();
		}else {
			return humanResourceManagementController;
		}
	}
	
	//Untuk memanggil view HumanResourceManagement
	public HumanResourceManagement view() {
		return new HumanResourceManagement();
	}

}
