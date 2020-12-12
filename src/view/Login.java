package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

import controller.EmployeeController;
import controller.HumanResourceManagementController;
import controller.ManagerController;
import controller.PromoManagementController;
import controller.StorageController;
import controller.TransactionManagementController;

import javax.swing.JButton;



public class Login implements ActionListener{
	
	static int employeeID;

	private JFrame frame;
	private JTextField usernameLogin;
	private JTextField passwordLogin;
	private JButton btnLogin;
	


	/**
	 * Create the application.
	 */
	public Login() {
		initialize(); 
		addlistener(); //Fungsi yang berisi berbagai macam tombol agar dapat di tekan
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 533, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLoginForm = new JLabel("Login Form");
		lblLoginForm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLoginForm.setBounds(201, 65, 154, 34);
		frame.getContentPane().add(lblLoginForm);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(118, 130, 73, 14);
		frame.getContentPane().add(lblUsername);
		
		usernameLogin = new JTextField();
		usernameLogin.setBounds(201, 127, 86, 20);
		frame.getContentPane().add(usernameLogin);
		usernameLogin.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(118, 155, 73, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordLogin = new JTextField();
		passwordLogin.setBounds(201, 152, 86, 20);
		frame.getContentPane().add(passwordLogin);
		passwordLogin.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(201, 183, 89, 23);
		frame.getContentPane().add(btnLogin);
	}
	
	//Fungsi yang berisi berbagai macam tombol agar dapat di tekan
	public void addlistener() {
		btnLogin.addActionListener(this);
	}
	
	//Fungsi untuk mereset textfield
	public void reset(){
		usernameLogin.setText("");
		passwordLogin.setText("");
	}
	
	
	//Class untuk menampilkan notification box
	public static class Notification
	{
	    public static void infoBox(String infoMessage, String titleBar)
	    {
	        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	
	//Fungsi untuk mendapatkan roleID
	public Integer getRoleId(){
		String username = usernameLogin.getText();
		String password = passwordLogin.getText();
		
		Integer roleId = EmployeeController.getInstance().getRoleID(username, password);
		if(roleId == null) {
			roleId = 0;
		}else {
			employeeID = EmployeeController.getInstance().getEmployee(username, password);
		}
		return roleId;
	}
	
	//Fungsi yang akan dijalankan ketika terjadi sebuah action berupa penekana sebuah button akan di check ke dalam sini
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnLogin)) {
			if(getRoleId() == 1) {
				frame.dispose();
				TransactionManagementController.getInstance().view();
			}else if(getRoleId() == 2) {
				frame.dispose();
				StorageController.getInstance().view();
			}else if(getRoleId() == 3) {
				frame.dispose();
				PromoManagementController.getInstance().view();
			}else if(getRoleId() == 4) {
				frame.dispose();
				HumanResourceManagementController.getInstance().view();
			}else if(getRoleId() == 5) {
				frame.dispose();
				ManagerController.getInstance().view();
			}else if(getRoleId() == 0){
				Notification.infoBox("USERNAME OR PASSWORD IS WRONG", "LOGIN FAILURE");
				reset();
			}
			
		}
		
	}
	
}
