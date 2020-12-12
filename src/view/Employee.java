package view;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import controller.LoginController;
import controller.StorageController;
import model.EmployeeModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Employee implements ActionListener{

	private JFrame frame;
	private JTable table;
	private DefaultTableModel dtm;
	private JTextField nameInsert;
	private JTextField usernameInsert;
	private JTextField dobInsert;
	private JTextField salaryInsert;
	private JTextField roleInsert;
	private JButton btnInsert;
	private JTextField idFire;
	private JButton btnFire;
	private JTextField idReset;
	private JButton btnReset;
	private JTextField idUpdate;
	private JTextField nameUpdate;
	private JTextField usernameUpdate;
	private JTextField dobUpdate;
	private JTextField salaryUpdate;
	private JButton btnUpdate;
	private JButton btnLogout;
	
	

	public Employee() {
		table(); //table employee utama
		initialize(); 
		addlistener(); //Fungsi yang berisi berbagai macam tombol agar dapat di tekan
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 737, 673);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table.setBounds(10, 11, 511, 281);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(11, 70, 700, 149);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblEmployeeManagementForm = new JLabel("Employee Management Form");
		lblEmployeeManagementForm.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEmployeeManagementForm.setBounds(208, 19, 362, 40);
		frame.getContentPane().add(lblEmployeeManagementForm);
		
		JLabel lblInsertEmployee = new JLabel("Insert Employee");
		lblInsertEmployee.setBounds(114, 230, 111, 14);
		frame.getContentPane().add(lblInsertEmployee);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(66, 271, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setBounds(66, 296, 67, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblDob = new JLabel("DOB : ");
		lblDob.setBounds(66, 321, 46, 14);
		frame.getContentPane().add(lblDob);
		
		JLabel lblSalary = new JLabel("Salary :");
		lblSalary.setBounds(66, 346, 46, 14);
		frame.getContentPane().add(lblSalary);
		
		JLabel lblRole = new JLabel("Role :");
		lblRole.setBounds(66, 372, 46, 14);
		frame.getContentPane().add(lblRole);
		
		nameInsert = new JTextField();
		nameInsert.setBounds(162, 268, 86, 20);
		frame.getContentPane().add(nameInsert);
		nameInsert.setColumns(10);
		
		usernameInsert = new JTextField();
		usernameInsert.setBounds(162, 293, 86, 20);
		frame.getContentPane().add(usernameInsert);
		usernameInsert.setColumns(10);
		
		dobInsert = new JTextField();
		dobInsert.setBounds(162, 318, 86, 20);
		frame.getContentPane().add(dobInsert);
		dobInsert.setColumns(10);
		
		salaryInsert = new JTextField();
		salaryInsert.setBounds(162, 343, 86, 20);
		frame.getContentPane().add(salaryInsert);
		salaryInsert.setColumns(10);
		
		roleInsert = new JTextField();
		roleInsert.setText("");
		roleInsert.setBounds(162, 369, 86, 20);
		frame.getContentPane().add(roleInsert);
		roleInsert.setColumns(10);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(162, 400, 89, 23);
		frame.getContentPane().add(btnInsert);
		
		JLabel lblFireEmployee = new JLabel("Fire Employee");
		lblFireEmployee.setBounds(114, 456, 89, 14);
		frame.getContentPane().add(lblFireEmployee);
		
		JLabel lblEmployeeId = new JLabel("Employee ID :");
		lblEmployeeId.setBounds(66, 500, 101, 14);
		frame.getContentPane().add(lblEmployeeId);
		
		idFire = new JTextField();
		idFire.setBounds(162, 497, 86, 20);
		frame.getContentPane().add(idFire);
		idFire.setColumns(10);
		
		btnFire = new JButton("Fire");
		btnFire.setBounds(159, 536, 89, 23);
		frame.getContentPane().add(btnFire);
		
		JLabel lblResetPassword = new JLabel("Reset Password");
		lblResetPassword.setBounds(506, 456, 111, 14);
		frame.getContentPane().add(lblResetPassword);
		
		JLabel lblEmployeeId_1 = new JLabel("Employee ID :");
		lblEmployeeId_1.setBounds(443, 500, 87, 14);
		frame.getContentPane().add(lblEmployeeId_1);
		
		idReset = new JTextField();
		idReset.setBounds(556, 497, 86, 20);
		frame.getContentPane().add(idReset);
		idReset.setColumns(10);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(553, 536, 89, 23);
		frame.getContentPane().add(btnReset);
		
		JLabel lblUpdateEmployee = new JLabel("Update Employee");
		lblUpdateEmployee.setBounds(505, 230, 100, 14);
		frame.getContentPane().add(lblUpdateEmployee);
		
		JLabel lblEmployeeId_2 = new JLabel("Employee ID :");
		lblEmployeeId_2.setBounds(443, 271, 89, 14);
		frame.getContentPane().add(lblEmployeeId_2);
		
		JLabel lblName_1 = new JLabel("Name :");
		lblName_1.setBounds(443, 296, 46, 14);
		frame.getContentPane().add(lblName_1);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(443, 321, 89, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblDob_1 = new JLabel("DOB : ");
		lblDob_1.setBounds(443, 346, 46, 14);
		frame.getContentPane().add(lblDob_1);
		
		JLabel lblNewLabel_1 = new JLabel("Salary :");
		lblNewLabel_1.setBounds(443, 372, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		idUpdate = new JTextField();
		idUpdate.setBounds(556, 268, 86, 20);
		frame.getContentPane().add(idUpdate);
		idUpdate.setColumns(10);
		
		nameUpdate = new JTextField();
		nameUpdate.setBounds(556, 293, 86, 20);
		frame.getContentPane().add(nameUpdate);
		nameUpdate.setColumns(10);
		
		usernameUpdate = new JTextField();
		usernameUpdate.setBounds(556, 318, 86, 20);
		frame.getContentPane().add(usernameUpdate);
		usernameUpdate.setColumns(10);
		
		dobUpdate = new JTextField();
		dobUpdate.setBounds(556, 343, 86, 20);
		frame.getContentPane().add(dobUpdate);
		dobUpdate.setColumns(10);
		
		salaryUpdate = new JTextField();
		salaryUpdate.setBounds(556, 369, 86, 20);
		frame.getContentPane().add(salaryUpdate);
		salaryUpdate.setColumns(10);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(315, 589, 89, 23);
		frame.getContentPane().add(btnLogout);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(553, 400, 89, 23);
		frame.getContentPane().add(btnUpdate);
	}
	
	//Fungsi yang berisi untuk mereset textfield
	public void reset(){
		nameInsert.setText("");
		roleInsert.setText("");
		dobInsert.setText("");
		roleInsert.setText("");
		salaryInsert.setText("");
		usernameInsert.setText("");
		idFire.setText("");
		idReset.setText("");
		dobUpdate.setText("");
		idUpdate.setText("");
		nameUpdate.setText("");
		salaryUpdate.setText("");
		usernameUpdate.setText("");
	}
	
	//Fungsi yang berisi berbagai macam tombol agar dapat di tekan
	void addlistener() {
		btnInsert.addActionListener(this);
		btnFire.addActionListener(this);
		btnReset.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnLogout.addActionListener(this);
	}
	
	//Fungsi yang memasukan data employee yang ada ke dalam row table
	public void getAll(){
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		        dtm.removeRow(i);
		    }
		}
		
		Vector<EmployeeModel> employee = new Vector<EmployeeModel>();
		employee = EmployeeController.getInstance().getAllEmployee();
		
		table = new JTable(dtm);
		for(EmployeeModel employee2 : employee) {
			//dtm hanya menerima vector of object
			Vector<Object> data = new Vector<Object>();
			data.add(employee2.getEmployeeId());
			data.add(employee2.getRoleId());
			data.add(employee2.getEmployeeName());
			data.add(employee2.getUsername());
			data.add(employee2.getDOB());
			data.add(employee2.getSalary());
			data.add(employee2.getStatus());
			data.add(employee2.getPassword());
			dtm.addRow(data);
		}
	}
	
	//Fungsi yang membuat header dari table employee
	public void getHeader() {
		String header[]= {"EmployeeID", "RoleID", "Name", "Username", "DOB", "Salary", "Status", "Password"};
		dtm = new DefaultTableModel(header,0); 
	}
	
	//fungsi untuk menjalankan table
	void table() {
		getHeader();
		getAll();
	}
	
	//Fungsi untuk memasukan employee baru
	public void insert() {

		String employeeName = nameInsert.getText();
		String username = usernameInsert.getText();
		String DOB = dobInsert.getText();
		Integer salary = Integer.parseInt(salaryInsert.getText());
		Integer roleId = Integer.parseInt(roleInsert.getText());
		
	    int option = JOptionPane.showConfirmDialog(null, "Success Insert"); 
	    if(option == JOptionPane.YES_OPTION){
	    	EmployeeController.getInstance().insertEmployee(employeeName, username, DOB, salary, roleId);
	    }else{
	    	
	    }
	    reset();
	    getAll();
	}
	
	//Fungsi untuk memecat employee
	public void fire() {
		Integer employeeId = Integer.parseInt(idFire.getText());
		
	    int option = JOptionPane.showConfirmDialog(null, "Fire Success"); 
	    if(option == JOptionPane.YES_OPTION){
	    	EmployeeController.getInstance().fireEmployee(employeeId);
	    }else{
	    }
	    reset();
	    getAll();
	}
	
	//Fungsi untuk mereset password
	public void resetPassword() {
		Integer employeeId = Integer.parseInt(idReset.getText());
		
		
		
	    int option = JOptionPane.showConfirmDialog(null, "Success Reset"); 
	    if(option == JOptionPane.YES_OPTION){
	    	EmployeeController.getInstance().resetPassword(employeeId);
	    }else{
	    }
	    reset();
	    getAll();
	}
	
	//Fungsi untuk memanggil update
	public void update() {
		Integer employeeId = Integer.parseInt(idUpdate.getText());
		String employeeName =  nameUpdate.getText();
		String username =  usernameUpdate.getText();
		String DOB =  dobUpdate.getText();
		Integer salary =  Integer.parseInt(salaryUpdate.getText());
		
	    int option = JOptionPane.showConfirmDialog(null, "Success Reset"); 
	    if(option == JOptionPane.YES_OPTION){
	    	EmployeeController.getInstance().updateEmployee(employeeId, employeeName, username, DOB, salary);
	    }else{
	    }
	    reset();
	    getAll();
	}
	
	//Fungsi yang akan dijalankan ketika terjadi sebuah action berupa penekana sebuah button akan di check ke dalam sini
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnInsert)) {
			insert();
		}else if(e.getSource().equals(btnFire)) {
			fire();
		}else if(e.getSource().equals(btnReset)) {
			resetPassword();
		}else if(e.getSource().equals(btnUpdate)) {
			update();
		}else if(e.getSource().equals(btnLogout)) {
			frame.dispose();
			LoginController.getInstance().view();
		}
		
	}
}
