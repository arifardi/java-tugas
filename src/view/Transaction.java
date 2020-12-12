package view;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.LoginController;
import controller.RoleController;
import controller.TransactionController;
import controller.TransactionItemController;
import model.RoleModel;
import model.TransactionModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction implements ActionListener{

	private JFrame frame;
	private JTable table;
	private DefaultTableModel dtm;
	private JLabel lblMonthmm;
	private JLabel lblYearyyyy;
	private JTextField monthGet;
	private JTextField yearGet;
	private JButton btnGet;
	private JLabel lblTransactionReport;
	private JButton btnGetAllReport;
	private JButton btnLogout;
	private JButton btnTransactionItem;


	public Transaction() {
		table(); //table utama
		initialize();
		addlistener(); //Fungsi yang berisi berbagai macam tombol agar dapat di tekan
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 652, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table.setBounds(10, 11, 703, 261);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 616, 182);
		frame.getContentPane().add(scrollPane);
		
		lblMonthmm = new JLabel("Month (mm) :");
		lblMonthmm.setBounds(84, 302, 88, 14);
		frame.getContentPane().add(lblMonthmm);
		
		lblYearyyyy = new JLabel("Year (yyyy) :");
		lblYearyyyy.setBounds(84, 330, 88, 14);
		frame.getContentPane().add(lblYearyyyy);
		
		monthGet = new JTextField();
		monthGet.setBounds(182, 299, 86, 20);
		frame.getContentPane().add(monthGet);
		monthGet.setColumns(10);
		
		yearGet = new JTextField();
		yearGet.setBounds(182, 327, 86, 20);
		frame.getContentPane().add(yearGet);
		yearGet.setColumns(10);
		
		btnGet = new JButton("Get");
		btnGet.setBounds(182, 358, 89, 23);
		frame.getContentPane().add(btnGet);
		
		lblTransactionReport = new JLabel("Transaction Report");
		lblTransactionReport.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTransactionReport.setBounds(201, 204, 240, 70);
		frame.getContentPane().add(lblTransactionReport);
		
		btnGetAllReport = new JButton("Get All Report");
		btnGetAllReport.setBounds(313, 358, 134, 23);
		frame.getContentPane().add(btnGetAllReport);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(290, 413, 89, 23);
		frame.getContentPane().add(btnLogout);
		
		btnTransactionItem = new JButton("Transaction Item ");
		btnTransactionItem.setBounds(313, 326, 134, 23);
		frame.getContentPane().add(btnTransactionItem);
	}
	
	//Fungsi yang berisi berbagai macam tombol agar dapat di tekan
	void addlistener() {
		btnGet.addActionListener(this);
		btnGetAllReport.addActionListener(this);
		btnLogout.addActionListener(this);
		btnTransactionItem.addActionListener(this);
	}
	
	//Fungsi yang memasukan data yang ada ke dalam row table
	public void getAll(){
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		        dtm.removeRow(i);
		    }
		}
		
		Vector<TransactionModel> transaction = new Vector<TransactionModel>();
		transaction = TransactionController.getInstance().getAllTransaction();
		
		table = new JTable(dtm);
		for(TransactionModel trans2 : transaction) {
			//dtm hanya menerima vector of object
			Vector<Object> data = new Vector<Object>();
			data.add(trans2.getTransactionID());
			data.add(trans2.getPurchase_datetime());
			data.add(trans2.getVoucherID());
			data.add(trans2.getEmployeeID());
			data.add(trans2.getPaymentType());
			dtm.addRow(data);
		}
	}
	
	//Fungsi untuk mereset textfield
	void reset() {
		monthGet.setText("");
		yearGet.setText("");
	}
	
	//Fungsi untuk mendapatkan semua data transaction berdasasr month dan year 
	void getTransactionReport(String month,String year) {
		
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		        dtm.removeRow(i);
		    }
		}
				
		Vector<TransactionModel> transaction = new Vector<TransactionModel>();
		transaction = TransactionController.getInstance().getTransactionReport(month, year);
		table = new JTable(dtm);
		
		for(TransactionModel trans2 : transaction) {
			//dtm hanya menerima vector of object
			Vector<Object> data = new Vector<Object>();
			data.add(trans2.getTransactionID());
			data.add(trans2.getPurchase_datetime());
			data.add(trans2.getVoucherID());
			data.add(trans2.getEmployeeID());
			data.add(trans2.getPaymentType());
			dtm.addRow(data);
		}
		reset();
	}
	
	//Fungsi yang membuat header dari table
	public void getHeader() {
		String header[]= {"Transaction ID", "Purchase Datetime" , "Voucher ID" , "EmployeeID" , "PaymentType"};
		dtm = new DefaultTableModel(header,0); 
	}
	
	//Fungsi untuk memasukan data kedalam table
	void table() {
		getHeader();
		getAll();
	}
	
	//Fungsi yang akan dijalankan ketika terjadi sebuah action berupa penekana sebuah button akan di check ke dalam sini
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnGet)) {
			String month = monthGet.getText();
			String year = yearGet.getText();
			getTransactionReport(month, year);
			
		}else if(e.getSource().equals(btnGetAllReport)) {
			getAll();
		}else if(e.getSource().equals(btnLogout)) {
			frame.dispose();
			LoginController.getInstance().view();
		}else if(e.getSource().equals(btnTransactionItem)) {
			frame.dispose();
			TransactionItemController.getInstance().view();
		}
	}
}
