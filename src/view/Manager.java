package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.EmployeeController;
import controller.LoginController;
import controller.TransactionController;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Manager implements ActionListener{

	private JFrame frame;
	private JButton btnEmployeeManagementForm;
	private JButton btnTransaction;
	private JButton btnLogout;

	public Manager() {
		initialize();
		addlistener(); //Fungsi yang berisi berbagai macam tombol agar dapat di tekan
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 411, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblManagerForm = new JLabel("Manager");
		lblManagerForm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblManagerForm.setBounds(153, 50, 109, 30);
		frame.getContentPane().add(lblManagerForm);
		
		btnEmployeeManagementForm = new JButton("Employee Management Form");
		btnEmployeeManagementForm.setBounds(64, 91, 242, 30);
		frame.getContentPane().add(btnEmployeeManagementForm);
		
		btnTransaction = new JButton("Transaction Report ");
		btnTransaction.setBounds(64, 132, 236, 30);
		frame.getContentPane().add(btnTransaction);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(141, 191, 89, 23);
		frame.getContentPane().add(btnLogout);
	}
	
	//Fungsi yang berisi berbagai macam tombol agar dapat di tekan
	void addlistener() {
		btnEmployeeManagementForm.addActionListener(this);
		btnTransaction.addActionListener(this);
		btnLogout.addActionListener(this);
	}

	//Fungsi yang akan dijalankan ketika terjadi sebuah action berupa penekana sebuah button akan di check ke dalam sini
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnEmployeeManagementForm)) {
			frame.dispose();
			EmployeeController.getInstance().view();
		}else if(e.getSource().equals(btnTransaction)) {
			frame.dispose();
			TransactionController.getInstance().view();
		}else if(e.getSource().equals(btnLogout)) {
			frame.dispose();
			LoginController.getInstance().view();
		}
		
	}

}
