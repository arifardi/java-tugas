package controller;

import view.Manager;

public class ManagerController {

	public ManagerController() {
		
	}
	
	public static ManagerController managerController;
	

	public static ManagerController getInstance() {
		if(managerController == null) {
			return new ManagerController();
		}else {
			return managerController;
		}
	}
	
	//Untuk memanggil view Manager
	public Manager view() {
		return new Manager();
	}
	
}
