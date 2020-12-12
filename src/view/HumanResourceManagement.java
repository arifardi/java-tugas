package view;

import controller.EmployeeController;

public class HumanResourceManagement {

	public HumanResourceManagement() {
		EmployeeController.getInstance().view(); //Memanggil  employee, karena role HR hanya diberikan akses kedalam employeeForm
	}

}
