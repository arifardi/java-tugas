package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CartController;
import controller.EmployeeController;
import controller.LoginController;
import controller.PaymentController;
import controller.RoleController;
import controller.StorageController;
import controller.TransactionController;
import controller.TransactionItemController;
import controller.VoucherController;
import model.CartModel;
import model.PaymentModel;
import model.RoleModel;
import view.Login.Notification;

import javax.swing.JScrollPane;

public class Payment implements ActionListener{

	private JFrame frame;
	private JTable table;
	private JTextField inputVoucher;
	private DefaultTableModel dtm;
	private DefaultTableModel dtm2;
	private JButton btnInputVoucher;
	private JTable table_totalPrice;
	private JScrollPane scrollPane_totalPrice;
	private JLabel lblPaymentMethod;
	private JTextField paymentMethod;
	private JButton btnPay;
	private JLabel lblIfNo;
	

	public Payment() {
		table(); //table utama
		table_totalprice(); //table untuk menampilkan total price
		initialize();
		addlistener(); //Fungsi yang berisi berbagai macam tombol agar dapat di tekan
		frame.setVisible(true);
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 708, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table.setBounds(10, 11, 672, 190);
		frame.getContentPane().add(table);
		
		JLabel lblPaymentForm = new JLabel("Payment Form");
		lblPaymentForm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPaymentForm.setBounds(270, 102, 133, 48);
		frame.getContentPane().add(lblPaymentForm);
		
		JLabel lblInputVoucher = new JLabel("Input Voucher ID :");
		lblInputVoucher.setBounds(105, 186, 119, 14);
		frame.getContentPane().add(lblInputVoucher);
		
		inputVoucher = new JTextField();
		inputVoucher.setBounds(105, 228, 86, 20);
		frame.getContentPane().add(inputVoucher);
		inputVoucher.setColumns(10);
		
		btnInputVoucher = new JButton("Input");
		btnInputVoucher.setBounds(102, 272, 89, 23);
		frame.getContentPane().add(btnInputVoucher);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 672, 88);
		frame.getContentPane().add(scrollPane);
		
		table_totalPrice.setBounds(547, 116, 123, 87);
		frame.getContentPane().add(table_totalPrice);
		
		scrollPane_totalPrice = new JScrollPane(table_totalPrice);
		scrollPane_totalPrice.setBounds(270, 161, 123, 39);
		frame.getContentPane().add(scrollPane_totalPrice);
		
		lblPaymentMethod = new JLabel("Payment Method : (Cash / Credit Card)");
		lblPaymentMethod.setBounds(286, 203, 218, 14);
		frame.getContentPane().add(lblPaymentMethod);
		
		paymentMethod = new JTextField();
		paymentMethod.setBounds(285, 228, 86, 20);
		frame.getContentPane().add(paymentMethod);
		paymentMethod.setColumns(10);
		
		btnPay = new JButton("Pay");
		btnPay.setBounds(282, 272, 89, 23);
		frame.getContentPane().add(btnPay);
		
		lblIfNo = new JLabel("0 IF no voucher");
		lblIfNo.setBounds(105, 203, 96, 14);
		frame.getContentPane().add(lblIfNo);
	}
	
	//Fungsi yang berisi berbagai macam tombol agar dapat di tekan
	void addlistener() {
		btnInputVoucher.addActionListener(this);
		btnPay.addActionListener(this);
	}
	
	
	//Fungsi yang memasukan data cart yang ada ke dalam row table
	void getAll() {
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		        dtm.removeRow(i);
		    }
		}
		
		Vector<PaymentModel> payment = new Vector<PaymentModel>();
		
		payment = PaymentController.getInstance().getAllFromCart();
		table = new JTable(dtm);
		for(PaymentModel payment2 : payment) {
			//dtm hanya menerima vector of object
			Vector<Object> data = new Vector<Object>();
			data.add(payment2.getProductID());
			data.add(payment2.getProductName());
			data.add(payment2.getProductPrice());
			data.add(payment2.getQuantity());
			data.add(payment2.getQuantity() * payment2.getProductPrice());
			dtm.addRow(data);
		}
		
	}
	
	//Fungsi yang membuat header dari table cart
	void getHeader() {
		String header[]= {"Product ID", "Product Name" , "Product Price" , "Quantity", "SubTotal"};
		dtm = new DefaultTableModel(header,0); 
	}
	
	//Fungsi untuk memasukan data kedalam table 
	void table() {
		getHeader();
		getAll();
	}
	
	//Fungsi untuk menghitung total price
	public Integer calculateTotalPrice() {
		Integer totalPrice = 0;
		Vector<PaymentModel> payment = new Vector<PaymentModel>();
		payment = PaymentController.getInstance().getAllFromCart();
		for (int i = 0; i < payment.size() ; i++) {
			totalPrice = totalPrice + payment.get(i).getProductPrice() * payment.get(i).getQuantity();
		}
		return totalPrice;
	}
	
	//Fungsi untuk memasang header dari table totalprice
	void getHeaderTotalPrice() {
		String header[]= {"Total Price"};
		dtm2 = new DefaultTableModel(header,0); 
	}
	
	//Fungsi untuk menjalankan fungsi totalprice
	void getTotalPrice(float f) {
		if (dtm2.getRowCount() > 0) {
		    for (int i = dtm2.getRowCount() - 1; i > -1; i--) {
		        dtm2.removeRow(i);
		    }
		}
		table_totalPrice = new JTable(dtm2);
		Vector<Object> data = new Vector<Object>();
		data.add(f);
		dtm2.addRow(data);
	}
	
	//Fungsi untuk menjalankan table total price
	void table_totalprice() {
		getHeaderTotalPrice();
		getTotalPrice(calculateTotalPrice());
	}
	
	//Class yang membuat notification
	public static class Notification
	{
	    public static void infoBox(String infoMessage, String titleBar)
	    {
	        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	
	//Fungsi untuk menginput voucher
	void inputVoucher() {
		Integer voucherID = Integer.parseInt(inputVoucher.getText());
		long totalprice;
		long afterDisc = 0;
		float discount = 0;
		if(VoucherController.getInstance().checkVoucher(voucherID) == true) {
			discount =  VoucherController.getInstance().getDiscount(voucherID);
			totalprice = calculateTotalPrice();
			afterDisc = (long) (totalprice * (100 - discount)/100);
			
			getTotalPrice(afterDisc);
			Notification.infoBox(discount + "% VOUCHER IS USED"  ,"VOUCHER ALERT!");
		}else {
			Notification.infoBox("VOUCHERID NOT FOUND OR CANT BE USED", "VOUCHER ALERT!");
		}
	}
	

	//Fungsi untuk menambah data cart ke dalam transaction
	void addTransaction() {
		Integer tempVoucherID = Integer.parseInt(inputVoucher.getText());
		Integer voucherID = 0;
		if(VoucherController.getInstance().checkVoucher(tempVoucherID) == true) {
			voucherID = Integer.parseInt(inputVoucher.getText());
		}
		Integer employeeID = Login.employeeID;
		String paymentType = paymentMethod.getText();
		TransactionController.getInstance().addTransaction(voucherID, employeeID, paymentType);
	}
	
	//Fungsi yang akan berjalan ketika pembayaran berhasil
	void paySuccess() {
		TransactionItemController.getInstance().addTransactionItem(); 
		addTransaction();
		StorageController.getInstance().updateStock();
		CartController.getInstance().resetCart();
	}

	//Fungsi yang akan dijalankan ketika terjadi sebuah action berupa penekana sebuah button akan di check ke dalam sini
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnPay)) {
			paySuccess();
			Notification.infoBox("PAYMENT SUCCESS", "PAYMENT ALERT!");
			frame.dispose();
			CartController.getInstance().view();
		}else if(e.getSource().equals(btnInputVoucher)) {
			inputVoucher();
		}
		
	}

}
