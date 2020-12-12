package controller;

import java.util.Vector;

import model.EmployeeModel;
import view.Employee;

public class EmployeeController {

	public EmployeeController() {
		// TODO Auto-generated constructor stub
	}
	
	public static EmployeeController employeeController;
	private EmployeeModel employee = new EmployeeModel();

	
	public static EmployeeController getInstance() {
		if(employeeController == null) {
			return new EmployeeController();
		}else {
			return employeeController;
		}
	}
	
	//Untuk Memanggil view dari employee
	public Employee view() {
		return new Employee();
	}
	
	//Untuk Memanggil fungsi getAllEmployee
	public Vector<EmployeeModel> getAllEmployee(){
		return employee.getAllEmployee();
	}
	
	//Untuk Memanggil fungsi insertEmployee
	public void insertEmployee(String employeeName, String username, String DOB, Integer salary, Integer roleId) {
		employee.insertEmployee(employeeName, username, DOB, salary, roleId);
	}
	
	//Untuk Memanggil fungsi fireEmployee
	public void fireEmployee(Integer employeeId) {
		employee.fireEmployee(employeeId);
	}
	
	//Untuk Memanggil fungsi resetPassword
	public void resetPassword(Integer employeeId) {
		employee.resetPassword(employeeId);
	}
	
	//Untuk Memanggil fungsi updateEmployee
	public void updateEmployee(Integer employeeId, String employeeName, String username, String DOB, Integer salary) {
		employee.updateEmployee(employeeId, employeeName, username, DOB, salary);
	}
	
	//Untuk Memanggil fungsi getRoleID
	public Integer getRoleID(String username, String password) {
		return employee.getRoleID(username, password);
	}
	
	//Untuk Memanggil fungsi getEmployee
	public Integer getEmployee(String username, String password) {
		return employee.getEmployee(username, password);
	}

}
