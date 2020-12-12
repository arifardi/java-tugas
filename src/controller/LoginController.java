package controller;

import view.Login;

public class LoginController {

	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	
	public static LoginController loginController;
	

	public static LoginController getInstance() {
		if(loginController == null) {
			return new LoginController();
		}else {
			return loginController;
		}
	}
	
	//Untuk memanggil view Login
	public Login view() {
		return new Login();
	}

}
