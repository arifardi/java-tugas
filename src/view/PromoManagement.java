package view;

import controller.VoucherController;

public class PromoManagement {
	
	//Memanggil  employee, karena role HR hanya diberikan akses kedalam employeeForm
	public PromoManagement() {
		VoucherController.getInstance().view();
	}

}
