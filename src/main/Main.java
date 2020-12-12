package main;

import controller.LoginController;

public class Main {

	public Main() {
		LoginController.getInstance().view();
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
