package controller;

import java.util.Vector;

import model.RoleModel;
import view.Role;

public class RoleController {

	public RoleController() {
		// TODO Auto-generated constructor stub
	}
	
	public static RoleController roleController;
	private RoleModel role = new RoleModel();

	
	public static RoleController getInstance() {
		if(roleController == null) {
			return new RoleController();
		}else {
			return roleController;
		}
	}
	
	//Untuk memanggil view Role
	public Role view() {
		return new Role();
	}
	
	//Untuk memanggil fungsi getAllRole
	public Vector<RoleModel> getAllRole(){
		return role.getAllRole();
	}

}
