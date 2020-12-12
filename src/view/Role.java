package view;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.RoleController;
import controller.StorageController;
import model.RoleModel;
import model.StorageModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;

public class Role {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel dtm;


	public Role() {
		table(); //table role utama
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 626, 259);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table.setBounds(10, 11, 590, 253);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 47, 590, 103);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblRole = new JLabel("- Role -");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblRole.setBounds(263, 16, 81, 20);
		frame.getContentPane().add(lblRole);
	}
	
	//Fungsi yang memasukan data role yang ada ke dalam row table
	public void getAll(){
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		        dtm.removeRow(i);
		    }
		}
		
		Vector<RoleModel> role = new Vector<RoleModel>();
		role = RoleController.getInstance().getAllRole();
		
		table = new JTable(dtm);
		for(RoleModel role2 : role) {
			//dtm hanya menerima vector of object
			Vector<Object> data = new Vector<Object>();
			data.add(role2.getRoleId());
			data.add(role2.getRoleName());
			dtm.addRow(data);
		}
	}
	
	//Fungsi yang membuat header dari table role
	public void getHeader() {
		String header[]= {"RoleID", "RoleName"};
		dtm = new DefaultTableModel(header,0); 
	}
	
	//Fungsi untuk memasukan data kedalam table 
	void table() {
		getHeader();
		getAll();
		
	}
}
