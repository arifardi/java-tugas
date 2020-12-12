package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.LoginController;
import controller.RoleController;
import controller.TransactionController;
import controller.TransactionItemController;
import model.RoleModel;
import model.TransactionItemModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.security.auth.login.LoginContext;
import javax.swing.JButton;

public class TransactionItem implements ActionListener{

	private JFrame frame;
	private JTable table;
	private DefaultTableModel dtm;
	private JButton btnLogout;
	private JButton btnBack;


	public TransactionItem() {
		table(); //table utama
		initialize();
		addlistener();
		frame.setVisible(true);
	}
	
	
	void addlistener() {
		btnBack.addActionListener(this);
		btnLogout.addActionListener(this);
	}
	
	//Fungsi yang memasukan data yang ada ke dalam row table
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 583, 376);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table.setBounds(10, 11, 547, 155);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(68, 68, 434, 166);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblTransactionItem = new JLabel("Transaction Item");
		lblTransactionItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTransactionItem.setBounds(208, 21, 183, 36);
		frame.getContentPane().add(lblTransactionItem);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(413, 289, 89, 23);
		frame.getContentPane().add(btnLogout);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(70, 289, 89, 23);
		frame.getContentPane().add(btnBack);
	}
	
	public void getAll(){
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		        dtm.removeRow(i);
		    }
		}
		
		Vector<TransactionItemModel> transitem = new Vector<TransactionItemModel>();
		transitem = TransactionItemController.getInstance().getAllTransactionItem();
		
		table = new JTable(dtm);
		for(TransactionItemModel transitem2 : transitem) {
			Vector<Object> data = new Vector<Object>();
			data.add(transitem2.getTransactionID());
			data.add(transitem2.getProductID());
			data.add(transitem2.getQuantity());
			dtm.addRow(data);
		}
	}
	
	//Fungsi yang membuat header dari table
	public void getHeader() {
		String header[]= {"Transaction ID", "Product ID" , "Quantity"};
		dtm = new DefaultTableModel(header,0); 
	}
	
	//Fungsi untuk memasukan data kedalam table
	void table() {
		getHeader();
		getAll();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBack)) {
			frame.dispose();
			TransactionController.getInstance().view();
		}else if(e.getSource().equals(btnLogout)) {
			frame.dispose();
			LoginController.getInstance().view();
		}
		
	}

}
